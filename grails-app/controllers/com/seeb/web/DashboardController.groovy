package com.seeb.web
import com.opentok.OpenTok
import com.seeb.web.command.AccountInfoDTO
import com.seeb.web.command.VideoMessageDTO
import com.seeb.web.enums.SessionState
import com.seeb.web.oauth.OAuthStatusInfo
import com.seeb.web.oauth.OautUser
import grails.converters.JSON

class DashboardController {

    public static final String SPRING_SECURITY_OAUTH_TOKEN = 'springSecurityOAuthToken'

    def springSecurityService
    def tokService
    def accountService
    def videoMessageService
    def oauthService

    def external() {
        if (springSecurityService.isLoggedIn()) {
//            def sessionKey = oauthService.findSessionKeyForAccessToken("external")
//            if(session[sessionKey] != null) {
            def currentUser = springSecurityService.getCurrentUser()

            AccountInfo aiDB = AccountInfo.findByAccount( (OautUser)currentUser);
            if(aiDB == null) {
                aiDB = new AccountInfo(firstName: "externalUser", lastName: "No." + currentUser.id, account: (OautUser) currentUser)
                aiDB.save(flash: true)
                }
//            }

            AccountInfoDTO ai = accountService.currentAccountInfo()

            if("verified".equals(params.id) ) {
                ai.alreadyVerify = true
            }

            String resp = params.callback + "(" + ([tokData: tokService.makeTokDataForUser()] as JSON) + ")"
            render (contentType: "application/json", text: resp)
        } else {
            String resp = params.callback + "(" + ([error: true] as JSON) + ")"
            render (contentType: "application/json", text: resp)
        }
    }

    def index() {
        if (springSecurityService.isLoggedIn()) {
            def sessionKey = oauthService.findSessionKeyForAccessToken("temp")
            if(session[sessionKey] != null) {
                def currentUser = springSecurityService.getCurrentUser()
                AccountInfo aiDB = AccountInfo.findByAccount( (OautUser)currentUser);
                if(aiDB == null) {
                    aiDB = new AccountInfo(firstName: "tempUser", lastName: "No." + currentUser.id, account: (OautUser) currentUser)
                    aiDB.save(flash: true)
                }
            }

            AccountInfoDTO ai = accountService.currentAccountInfo()

            if("verified".equals(params.id) ) {
                ai.alreadyVerify = true
            }
            render (view: "dashboard", model: [accountInfo: ai, tokData: tokService.makeTokDataForUser()])
        } else {
            redirect(uri: "/index")
        }
    }

    def dashboard() {
        index()
    }

    def editprofile() {
        if (springSecurityService.isLoggedIn()) {
            AccountInfoDTO ai = accountService.currentAccountInfo()
            render (view: "editprofile", model: [accountInfo: ai, tokData: tokService.makeTokDataForUser()])
        } else {
            redirect(uri: "/index")
        }
    }

    def saveprofile(AccountInfoDTO accountInfo) {
        if (springSecurityService.isLoggedIn()) {
            String status = ""
            if (accountInfo.validate()){
                status = "Updated"
                accountService.updateInfo(accountInfo)
            }

            OautUser currentUser = (OautUser)springSecurityService.getCurrentUser()
            AccountLogo al = AccountLogo.findByAccount(currentUser)
            accountInfo.hasLogo = al != null

            render (view: "editprofile", model: [accountInfo: accountInfo, status: status, tokData: tokService.makeTokDataForUser()])
        } else {
            redirect(uri: "/index")
        }
    }

    def conference() {
        if (springSecurityService.isLoggedIn()) {
            render (view: "conference", model: tokService.prepareInitDate())
        } else {
            redirect(uri: "/index")
        }
    }

    def preCall() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.INACTIVE, SessionState.CLIENT_CALL)
        }
        generateAnswer(params.callback != null, [result: "ok"] as JSON);
    }

    def approveCall() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.COMPANY_CALL, SessionState.IN_CALL)

        }
        generateAnswer(params.callback != null, [result: "ok"] as JSON);
    }

    def declineCall() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.COMPANY_CALL, SessionState.INACTIVE)
        }
        generateAnswer(params.callback != null, [result: "ok"] as JSON);
    }

    def callTimeoutOver() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.CLIENT_CALL, SessionState.INACTIVE)
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.IN_CALL, SessionState.INACTIVE)
        }
        generateAnswer(params.callback != null, [result: "ok"] as JSON);
    }

    def recordMessage() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.INACTIVE, SessionState.RECORD_MESSAGE)
        }
        generateAnswer(params.callback != null, [result: "ok"] as JSON);
    }

    def stopRecordAndLeaveMessage() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            tokService.changeStatusByAccount((OautUser)currentUser, SessionState.RECORD_MESSAGE, SessionState.INACTIVE)
        }
        generateAnswer(params.callback != null, [result: "ok"] as JSON);
    }
//
    def reclist(){
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()


            String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
            String apiSecret = grailsApplication.config.grails.plugin.tokbox.secret
            OpenTok opentok = new OpenTok(Integer.parseInt(apiKey), apiSecret);
            opentok.listArchives().each { it->
                print "-----"
                print "sid:" + it.sessionId
                print "dur:" + it.duration
                print "url:" + it.url
            }

//            opentok.getArchive()
        }
        return "ok"

    }

    def messages(){
        if (springSecurityService.isLoggedIn()) {
            AccountInfoDTO ai = accountService.currentAccountInfo()
            List<VideoMessageDTO> videoMessages = videoMessageService.getMessagesListByOauth()

            render (view: "messages", model: [accountInfo: ai, videoMessages: videoMessages, tokData: tokService.makeTokDataForUser()])
        } else {
            redirect(uri: "/index")
        }
    }

    //TODO need end all incall action for offline users, need hook
    def getSessionState(){
        if (springSecurityService.isLoggedIn()) {
            Long timeout = grailsApplication.config.grails.local.connection.timeout as Long

            OautUser currentUser = (OautUser)(springSecurityService.getCurrentUser())
            OAuthStatusInfo info = OAuthStatusInfo.findByAccount(currentUser)
            if(info != null) {
                info.isAvailible = new Date()
            }

            info.save()

            TokSession tokSession = TokSession.findByAccount((OautUser)currentUser)

            boolean companyCall = false
            boolean inCall = false
            boolean stop = false
            boolean isAvailible = (tokSession.company.isAvailible != null && (new Date().getTime() - tokSession.company.isAvailible.getTime() < timeout) )

            if(tokSession != null){
                if(SessionState.COMPANY_CALL.equals(tokSession.active)) {
                    companyCall = true
                } else if(SessionState.IN_CALL.equals(tokSession.active)) {
                    inCall = true
                } else if(!SessionState.IN_CALL.equals(tokSession.active) && !SessionState.CLIENT_CALL.equals(tokSession.active)) {
                    stop = true
                }
            }
            generateAnswer(params.callback != null, [companyCall: companyCall, inCall: inCall, stop: stop, isAvailible: isAvailible] as JSON);
        }
    }

    def generateAnswer(boolean isJSONP, def resp){
        if(isJSONP){
            render(contentType: "application/json", text: params.callback + "(" + resp + ")")
        }else {
            render(resp)
        }

    }

    def verify() {
        if (springSecurityService.isLoggedIn()) {
            OautUser currentUser = (OautUser)springSecurityService.getCurrentUser()
            OAuthStatusInfo info = OAuthStatusInfo.findByAccount(currentUser)

            if(info?.verifyLink != null && info.verifyLink.equals(params?.id)){
                info.verified = true;
                info.save(flush: true)
            }
            redirect (url: "/dashboard/")
        } else {
            redirect(uri: "/index")
        }
    }

    def stopActiveCall(){
        if (springSecurityService.isLoggedIn()) {
            OautUser currentUser = (OautUser)springSecurityService.getCurrentUser()
            List<TokSession> tokSessions = TokSession.findAllByAccountAndActiveNotEqual(currentUser, SessionState.INACTIVE);
            tokSessions.each { it ->
                tokService.changeStatusByAccount(currentUser, it.active, SessionState.INACTIVE)
            }
            print "stop active by user";
        }
        generateAnswer(params.callback != null, [status: "ok"] as JSON);
    }
}

