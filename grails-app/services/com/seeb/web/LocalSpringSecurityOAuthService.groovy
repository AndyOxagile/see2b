package com.seeb.web
import com.seeb.web.oauth.LocalOAuthToken
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import org.scribe.model.Token

@Transactional
class LocalSpringSecurityOAuthService {

    def oauthService

    def createAuthToken(Token accessToken) {
        LocalOAuthToken token = new LocalOAuthToken(accessToken, RandomStringUtils.randomNumeric(15));

        return token
    }
}
