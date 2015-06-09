package com.seeb.web

import com.seeb.web.oauth.TempOAuthToken
import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils
import org.scribe.model.Token

@Transactional
class TempSpringSecurityOAuthService {

    def oauthService

    def createAuthToken(Token accessToken) {
        TempOAuthToken token = new TempOAuthToken(accessToken, RandomStringUtils.randomNumeric(15));

        return token
    }
}
