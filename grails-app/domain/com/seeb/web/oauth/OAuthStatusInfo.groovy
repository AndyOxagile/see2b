package com.seeb.web.oauth

/**
 * Created by savitskyao on 04.06.2015.
 */
class OAuthStatusInfo {

    Date isAvailible = new Date();
    String verifyLink
    boolean verified = true

    static belongsTo = [account: OautUser]
}
