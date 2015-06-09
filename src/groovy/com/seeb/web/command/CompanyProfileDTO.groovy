package com.seeb.web.command

import com.seeb.web.Company
import grails.validation.Validateable

/**
 * Created by savitskyao on 26.05.2015.
 */
@Validateable
class CompanyProfileDTO {
    String name
    String contactName
    String logo
    String hoursOfAvailability

    String location

    String website

    String fbInfo
    String twInfo
    String gInfo
    String ytInfo

    static constraints = {
        name(nullable: false, blank: false, maxSize: 255)
        location(nullable: true, blank: true, maxSize: 255)
        contactName(nullable: true, blank: true, maxSize: 255)
        logo(nullable: true, blank: true, maxSize: 255)
        website(nullable: true, blank: true, maxSize: 255)
        fbInfo(nullable: true, blank: true, maxSize: 255)
        twInfo(nullable: true, blank: true, maxSize: 255)
        gInfo(nullable: true, blank: true, maxSize: 255)
        ytInfo(nullable: true, blank: true, maxSize: 255)
        hoursOfAvailability(nullable: true, blank: true, maxSize: 255)
    }

    Company updateCompanyInfo(Company c) {
        c.name = this.name
        c.contactName = this.contactName
        c.logo = this.logo
        c.location = this.location
        c.hoursOfAvailability = this.hoursOfAvailability
        c.website = this.website
        c.fbInfo = this.fbInfo
        c.twInfo = this.twInfo
        c.gInfo = this.gInfo
        c.ytInfo = this.ytInfo

        return c
    }

    static CompanyProfileDTO fromCompany(Company c) {
        return new CompanyProfileDTO(
                name: c.name,
                contactName: c.contactName,
                logo: c.logo,
                location: c.location,
                hoursOfAvailability: c.hoursOfAvailability,
                website: c.website,

                fbInfo: c.fbInfo,
                twInfo: c.twInfo,
                gInfo: c.gInfo,
                ytInfo: c.ytInfo)
    }
}
