package com.seeb.web

/**
 * Created by savitskyao on 12.05.2015.
 */
class Company {
    String name
    String location
    String contactName

    String logo
    String website

    String fbInfo
    String twInfo
    String gInfo
    String ytInfo
    CompanyLogo companyLogo

    String hoursOfAvailability
    Date isAvailible

    static hasMany = [
            videoMessage: VideoMessage
    ]

    static mapping = {
        videoMessage cascade: 'all'
    }

    static constraints = {
        companyLogo(nullable: true)
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
        isAvailible(nullable: true)
        videoMessage(nullable: true)
    }
}
