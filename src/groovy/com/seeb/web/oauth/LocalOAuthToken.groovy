package com.seeb.web.oauth

import grails.plugin.springsecurity.oauth.OAuthToken
import org.scribe.model.Token

/**
 * Created by savitskyao on 19.05.2015.
 */
class LocalOAuthToken  extends OAuthToken{
    public static final String PROVIDER_NAME = "local"

    String profileId

    LocalOAuthToken(Token accessToken, String profileId) {
        super(accessToken)
        this.profileId = profileId
        this.principal = profileId
    }

    String getSocialId() {
        return profileId
    }

    String getScreenName() {
        return profileId
    }

    String getProviderName() {
        return PROVIDER_NAME
    }
}
