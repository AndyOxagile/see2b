package com.seeb.web

import com.seeb.web.oauth.OautUser

class AccountLogo {

    byte[] logo

    static belongsTo = [account: OautUser]

    static constraints = {
        logo(maxSize: 20000000)
        account(unique: true)
    }
}
