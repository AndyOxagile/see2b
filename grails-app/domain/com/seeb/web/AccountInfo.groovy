package com.seeb.web

import com.seeb.web.oauth.OautUser

/**
 * Created by savitskyao on 12.05.2015.
 */
class AccountInfo {
    String companyName
    String firstName
    String lastName

    String location

    String profilePhotoUrl

    String website

    String fbInfo
    String twInfo
    String gInfo
    String ytInfo

    static belongsTo = [account: OautUser]

    static constraints = {
        companyName(nullable: true, blank: true, maxSize: 255)
        firstName(nullable: false, blank: false, maxSize: 255)
        lastName(nullable: false, blank: false, maxSize: 255)
        location(nullable: true, blank: true, maxSize: 255)
        profilePhotoUrl(nullable: true, blank: true, maxSize: 255)
        website(nullable: true, blank: true, maxSize: 255)
        fbInfo(nullable: true, blank: true, maxSize: 255)
        twInfo(nullable: true, blank: true, maxSize: 255)
        gInfo(nullable: true, blank: true, maxSize: 255)
        ytInfo(nullable: true, blank: true, maxSize: 255)
    }

}
