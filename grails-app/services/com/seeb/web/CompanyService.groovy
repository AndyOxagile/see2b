package com.seeb.web

import grails.transaction.Transactional

@Transactional
class CompanyService {

    Company getFirstCompany() {
        List<Company> companies = Company.findAll()
        Company company = companies.get(0)
        return company
    }

    def createStubCompany() {
        Company c = new Company(name: "Jim's Cheesesteaks", location: "somewhere", isAvailible: new Date(), contactName: "BootStrap")
        c.save(flush: true, validate: true, failOnError: true)
    }

}
