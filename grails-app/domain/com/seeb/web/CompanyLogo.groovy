package com.seeb.web

class CompanyLogo {

    byte[] logo

    static belongsTo = [company: Company]

    static constraints = {
        logo(maxSize: 20000000)
    }
}
