package com.seeb.web

import com.seeb.web.oauth.OAuthStatusInfo
import com.seeb.web.oauth.OautUser
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.oauth.OAuthCreateAccountCommand
import grails.plugin.springsecurity.oauth.OAuthLoginException
import grails.plugin.springsecurity.oauth.OAuthToken
import grails.plugin.springsecurity.oauth.SpringSecurityOAuthController
import org.apache.commons.lang.RandomStringUtils
import org.scribe.model.Token
import org.springframework.security.core.context.SecurityContextHolder

class LocalOAuthController extends  SpringSecurityOAuthController {

    def localSpringSecurityOAuthService
    def tempSpringSecurityOAuthService
    def externalSpringSecurityOAuthService
    def springSecurityOAuthService
    def springSecurityService
    def tokService
    def mailService

    OAuthToken makeTokenByOAutType(String type) {
        Token t = tokService.randomToken()

        def sessionKey = oauthService.findSessionKeyForAccessToken(type)
        session[sessionKey] = t
        def oAuthToken =
                "external".equals(type)? externalSpringSecurityOAuthService.createAuthToken(t):
                "temp".equals(type)? tempSpringSecurityOAuthService.createAuthToken(t):
                "local".equals(type)? localSpringSecurityOAuthService.createAuthToken(t):null
        session[SPRING_SECURITY_OAUTH_TOKEN] = oAuthToken
        return oAuthToken
    }

    def createAndLoginExternalAccount(){
        makeTokenByOAutType("external")
        OAuthCreateAccountCommand command = tokService.makeAccountComandWithRandomPassword()
        createAccount(command)
        String resp = params.callback + "(" + ([result: "ok2"] as JSON) + ")"
        render (contentType: "application/json", text: resp)
    }

    def createAndLoginTempAccount(){
        makeTokenByOAutType("temp")
        OAuthCreateAccountCommand command = tokService.makeAccountComandWithRandomPassword()
        createAccount(command)
    }

    def setUserStatusAndSendEmail(OautUser user){
        boolean isLocal =
                session[oauthService.findSessionKeyForAccessToken("local")]!=null &&
                session[oauthService.findSessionKeyForAccessToken("external")]==null &&
                session[oauthService.findSessionKeyForAccessToken("temp")]==null &&
                session[oauthService.findSessionKeyForAccessToken("facebook")]==null &&
                session[oauthService.findSessionKeyForAccessToken("linkedin")]==null &&
                session[oauthService.findSessionKeyForAccessToken("google")]==null

        tokService.createTokSessionByUser(user)
        OAuthStatusInfo info = new OAuthStatusInfo(account:user, verified: !isLocal, verifyLink: RandomStringUtils.randomAlphanumeric(15))
        info.save(flush: true)

        String logoUrl = grailsApplication.config.grails.local.baseURL + grailsApplication.config.grails.local.logoLink
        String verifyUrl =  grailsApplication.config.grails.local.baseURL + grailsApplication.config.grails.local.verifyLink + info.verifyLink

        mailService.sendMail {
            to "alanawoofwoof@gmail.com"
            from "support@see2b.com"

            subject "Please Verify Your See2B Account"
            html g.render(template:"/template/mail/registered", model: [verifyUrl: verifyUrl, logoUrl: logoUrl])
        }

        if (isLocal) {
            print "email>" + ( user).username + "<"
            //TODO mb need try catch and verify email
            mailService.sendMail {
                to(((user).username))
                from "support@see2b.com"

                subject "Please Verify Your See2B Account"
                html g.render(template: "/template/mail/registered", model: [verifyUrl: verifyUrl, logoUrl: logoUrl])
            }
        }
    }

    @Override
    def createAccount(OAuthCreateAccountCommand command) {
        OAuthToken oAuthToken = session[SPRING_SECURITY_OAUTH_TOKEN]
        if (!oAuthToken) {
            oAuthToken = makeTokenByOAutType("local")
        }
        def user

        if (!springSecurityService.loggedIn) {
            def config = SpringSecurityUtils.securityConfig
            def commandValid = command.validate()
            def User = springSecurityOAuthService.lookupUserClass()
            boolean created = commandValid && User.withTransaction { status ->
                user = springSecurityOAuthService.lookupUserClass().newInstance()
                //User user = new User(username: command.username, password: command.password1, enabled: true)
                String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName
                user."${usernameFieldName}" = command.username
                user.password = command.password1
                user.enabled = true
                user.addToOAuthIDs(provider: oAuthToken.providerName, accessToken: oAuthToken.socialId, user: user)
                // updateUser(user, oAuthToken)
                if (!user.validate() || !user.save()) {
                    status.setRollbackOnly()
                    false
                }
                def UserRole = springSecurityOAuthService.lookupUserRoleClass()
                def Role = springSecurityOAuthService.lookupRoleClass()
                def roles = springSecurityOAuthService.getRoleNames()
                for (roleName in roles) {
                    UserRole.create user, Role.findByAuthority(roleName)
                }
                oAuthToken = springSecurityOAuthService.updateOAuthToken(oAuthToken, user)
                setUserStatusAndSendEmail((OautUser)user)
                true
            }
            if (created) {
                authenticateAndRedirect(oAuthToken, getDefaultTargetUrl())
                return "created"
            }
        }

        render view: 'signup', model: [createAccountCommand: command]
    }

    protected void authenticateAndRedirect(OAuthToken oAuthToken, redirectUrl) {
        session.removeAttribute SPRING_SECURITY_OAUTH_TOKEN
        SecurityContextHolder.context.authentication = oAuthToken
        String rememberMeParameterName = getRememberMeParameterName()
        if ((oAuthToken) && rememberMeParameterName && params[rememberMeParameterName]) {
            rememberMeServices.loginSuccess(request, response, SecurityContextHolder.context.authentication)
        }

        if (session[oauthService.findSessionKeyForAccessToken("external")]==null) {
            redirect(redirectUrl instanceof Map ? redirectUrl : [uri: redirectUrl])
        }
    }



    def askToLinkOrCreateAccount() {
        if (springSecurityService.isLoggedIn()) {
            def currentUser = springSecurityService.getCurrentUser()
            OAuthToken oAuthToken = session[SPRING_SECURITY_OAUTH_TOKEN]
            if (!oAuthToken) {
                log.warn "askToLinkOrCreateAccount: OAuthToken not found in session"
                throw new OAuthLoginException('Authentication error')
            }
            currentUser.addToOAuthIDs(provider: oAuthToken.providerName, accessToken: oAuthToken.socialId, user: currentUser)
            if (currentUser.validate() && currentUser.save()) {
                oAuthToken = springSecurityOAuthService.updateOAuthToken(oAuthToken, currentUser)
                authenticateAndRedirect(oAuthToken, getDefaultTargetUrl())
                return
            }
        }

        String pass = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomNumeric(3) + RandomStringUtils.randomAlphabetic(5)
        OAuthCreateAccountCommand command = new OAuthCreateAccountCommand(
                username: RandomStringUtils.randomAlphabetic(10)+"@"+RandomStringUtils.randomAlphabetic(10)+"random.com",
                password1: pass,
                password2: pass,
                springSecurityOAuthService: springSecurityOAuthService)
        createAccount(command)

//        return new ModelAndView("/springSecurityOAuth/askToLinkOrCreateAccount", [rememberMeParameter:getRememberMeParameterName()])
    }
}
