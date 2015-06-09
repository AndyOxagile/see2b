package com.seeb.web
import com.seeb.web.oauth.ExternalOAuthToken
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import org.scribe.model.Token

@Transactional
class ExternalSpringSecurityOAuthService {

    def oauthService

    def createAuthToken(Token accessToken) {
        ExternalOAuthToken token = new ExternalOAuthToken(accessToken, RandomStringUtils.randomNumeric(15));

        return token
    }
}
