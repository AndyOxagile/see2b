package com.seeb.web.command

import com.seeb.web.AccountInfo
import grails.validation.Validateable

/**
 * Created by savitskyao on 26.05.2015.
 */
@Validateable
class AccountInfoDTO {
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

    Boolean needVerify = false
    Boolean alreadyVerify = false

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

    AccountInfo updateAccountInfo(AccountInfo ai) {
        ai.companyName = this.companyName
        ai.firstName = this.firstName
        ai.lastName = this.lastName
        ai.location = this.location
        ai.profilePhotoUrl = this.profilePhotoUrl
        ai.website = this.website
        ai.fbInfo = this.fbInfo
        ai.twInfo = this.twInfo
        ai.gInfo = this.gInfo
        ai.ytInfo = this.ytInfo

        return ai
    }

    static AccountInfoDTO fromAccountInfo(AccountInfo ai) {
        return new AccountInfoDTO(
                companyName: ai.companyName,
                firstName: ai.firstName,
                lastName: ai.lastName,
                location: ai.location,
                profilePhotoUrl: ai.profilePhotoUrl,
                website: ai.website,

                fbInfo: ai.fbInfo,
                twInfo: ai.twInfo,
                gInfo: ai.gInfo,
                ytInfo: ai.ytInfo)
    }

    static AccountInfoDTO generateNew() {
        return new AccountInfoDTO(
                companyName: "New company",
                firstName: "",
                lastName: "",
                location: "",
                profilePhotoUrl: "",

                website: "",

                fbInfo: "",
                twInfo: "",
                gInfo: "",
                ytInfo: "")

    }
}
