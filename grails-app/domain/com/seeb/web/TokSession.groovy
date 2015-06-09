package com.seeb.web

import com.seeb.web.enums.SessionState
import com.seeb.web.oauth.OautUser

class TokSession {
    String sessionId = ""
    String userToken
    String companyToken
    SessionState active = SessionState.INACTIVE
    String currentArchiveId

    static belongsTo = [account: OautUser, company: Company]

    static constraints = {
        sessionId(nullable: false, blank: true, maxSize: 255)
        userToken(nullable: true, blank: true, maxSize: 700)
        companyToken(nullable: true, blank: true, maxSize: 700)
        active(nullable: false)
        currentArchiveId(nullable: true, blank: true, maxSize: 255)
        account unique: 'company'
    }

//    def beforeInsert() {
//        print("bef insert")
//    }
//
//    def beforeUpdate() {
//        print("bef up")
//    }
}
