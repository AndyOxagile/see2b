package com.seeb.web
import com.opentok.*
import com.opentok.exception.OpenTokException
import com.seeb.web.command.AccountInfoDTO
import com.seeb.web.command.CustomerDTO
import com.seeb.web.command.CustomerStateDTO
import com.seeb.web.enums.SessionState
import com.seeb.web.oauth.OautUser
import grails.plugin.springsecurity.oauth.OAuthCreateAccountCommand
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import org.scribe.model.Token

@Transactional
class TokService {
    def springSecurityService
    def springSecurityOAuthService
    def grailsApplication
    def companyService
    def accountService
    def archiveService

    Object prepareInitDate() {
        String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
        def currentUser = springSecurityService.getCurrentUser()
        def company = companyService.getFirstCompany()
        TokSession tokSession = TokSession.findByAccountAndCompany((OautUser)currentUser, company)
        AccountInfoDTO ai = accountService.currentAccountInfo()
        return [apiKey: apiKey, token: tokSession.userToken, sessionId: tokSession.sessionId, accountInfo: ai]

    }

    List<CustomerDTO> getCustomerList(){
        List<OautUser> accounts = OautUser.getAll();
        List<CustomerDTO> result = new LinkedList<CustomerDTO>()
        if(accounts != null) {
            accounts.each { it ->
                result.push(CustomerDTO.make(it))
            }
        }
        return result
    }

    TokSession createTokSessionByUser(OautUser currentUser) {
        def company = companyService.getFirstCompany()

        TokSession tokSession = getOrCreateTokSession(currentUser, company)
        if(tokSession?.id != null) {
            tokSession = getOrMakeSessionIdAndTokens(tokSession)
        }

        tokSession
    }

    @Transactional
    TokSession getOrCreateTokSession(OautUser currentUser, Company company){
        TokSession tokSession = TokSession.findByAccountAndCompany(currentUser, company)
        if(tokSession?.id == null) {
            tokSession = new TokSession(account: (OautUser) currentUser, company: company)
            tokSession.save(validate: true)
        }
        return tokSession
    }

    TokSession getOrMakeSessionIdAndTokens(TokSession tokSession){
        if(tokSession.sessionId.isEmpty()) {
            String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
            String apiSecret = grailsApplication.config.grails.plugin.tokbox.secret
            OpenTok opentok = new OpenTok(Integer.parseInt(apiKey), apiSecret)

            tokSession.sessionId = opentok.createSession(new SessionProperties.Builder()
                    .mediaMode(MediaMode.ROUTED)
                    .build())
                    .getSessionId();
            try {
                tokSession.userToken = opentok.generateToken(tokSession.sessionId,
                        new TokenOptions.Builder()
                                .role(Role.MODERATOR)
                                .data("name=user")
                                .build());
            } catch (OpenTokException e) {
                e.printStackTrace();
            }
            try {
                tokSession.companyToken = opentok.generateToken(tokSession.sessionId,
                        new TokenOptions.Builder()
                                .role(Role.MODERATOR)
                                .data("name=company")
                                .build());
            } catch (OpenTokException e) {
                e.printStackTrace();
            }
            tokSession.save(flush: true, validate: true, failOnError: true)
        }
        tokSession
    }

    //TODO need end all incall action for offline users, need hook
    Map<Long, CustomerStateDTO> getCustomersStateList() {
        List<OautUser> accounts = OautUser.findAllByAccountLocked(false);
        Map<Long, CustomerStateDTO> result = new HashMap<Long, CustomerStateDTO>()
        def company = companyService.getFirstCompany()
        company.isAvailible = new Date()
        company.save()

        Long timeout = grailsApplication.config.grails.local.connection.timeout as Long

        if (accounts != null) {
            accounts.each { it ->
                TokSession tokSession = TokSession.findByAccountAndCompany(it, company)
                if(tokSession?.id != null) {
                    CustomerStateDTO customerState = CustomerStateDTO.make(tokSession, timeout)
                    if (customerState?.id) {
                        result.put(customerState.id, customerState)
                    }
                }

            }
        }
        result
    }

    def changeStatusByAccount(OautUser account, SessionState statusForCheck, SessionState newStatus){
        if (account != null && !statusForCheck.equals(newStatus)) {
            TokSession tokSession = TokSession.findByAccount(account)
            if(tokSession != null) {
                if (statusForCheck.equals(tokSession.active)) {
                    if(SessionState.IN_CALL.equals(newStatus) || SessionState.RECORD_MESSAGE.equals(newStatus)){
                        archiveService.startRecordMessage(tokSession);
                    }else
                    if(SessionState.IN_CALL.equals(tokSession.active) || SessionState.RECORD_MESSAGE.equals(tokSession.active)){
                        archiveService.stopRecordAndLeaveMessage(tokSession);
//                        account, statusForCheck
                    }
                    if(SessionState.CLIENT_CALL.equals(tokSession.active) || SessionState.INACTIVE.equals(newStatus)){
                        archiveService.registerCall(tokSession);
//                        account, statusForCheck
                    }

                    tokSession.active = newStatus;
                    tokSession.save(flush: true)
                }
            }
        }
    }

    def makeTokDataForUser(){
        String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
        def currentUser = springSecurityService.getCurrentUser()
        def company = companyService.getFirstCompany()
        TokSession tokSession = TokSession.findByAccountAndCompany((OautUser)currentUser, company)
        return [apiKey: apiKey, sessionId: tokSession.sessionId, token: tokSession.userToken]
    }

//    TODO need move to utils
    Token randomToken() {
        String secret = RandomStringUtils.random(15)
        String token = RandomStringUtils.random(15)
        new Token(token, secret, "{\"token\": \"" + token + "\", \"secret\":\"" + secret + "\"}" )
    }
//    TODO need move to utils

    OAuthCreateAccountCommand makeAccountComandWithRandomPassword(){
        String pass = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(3) + RandomStringUtils.randomAlphabetic(5)
        return new OAuthCreateAccountCommand(
                username: RandomStringUtils.randomAlphabetic(10)+"@"+RandomStringUtils.randomAlphabetic(10)+"random.com",
                password1: pass,
                password2: pass,
                springSecurityOAuthService: springSecurityOAuthService)
    }

}
