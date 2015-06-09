package com.seeb.web
import com.seeb.web.command.CompanyProfileDTO
import com.seeb.web.command.CustomerDTO
import com.seeb.web.command.VideoMessageDTO
import com.seeb.web.enums.SessionState
import com.seeb.web.oauth.OautUser
import grails.converters.JSON

class CompanyDashboardController {

    def springSecurityService
    def companyService
    def tokService
    def videoMessageService
    def archiveService


    def index() {
        Company company = companyService.getFirstCompany()
        render (view: "dashboard", model: [company: CompanyProfileDTO.fromCompany(company)])
    }

    def dashboard() {
        index()
    }

    def editprofile() {
        Company company = companyService.getFirstCompany()
        render (view: "editprofile", model: [company: CompanyProfileDTO.fromCompany(company)])
    }

    def saveprofile(CompanyProfileDTO company) {
        String status = ""
        if (company.validate()){
            Company bdCompany = companyService.getFirstCompany()
            company.updateCompanyInfo(bdCompany)
            status = "Updated"
            bdCompany.save(flush: true, validate: true, failOnError: true)
        }
        render (view: "editprofile", model: [company: company, status: status])
    }

    def messages(){
        Company company = companyService.getFirstCompany()
        List<VideoMessageDTO> videoMessages = videoMessageService.getMessagesListByCompany()
        render (view: "messages", model: [company: company, videoMessages: videoMessages ])
    }

    def customers(){
        Company company = companyService.getFirstCompany()
        List<CustomerDTO> customers = tokService.getCustomerList()
        render (view: "customers", model: [company: company, customers: customers ])
    }

    def getCustomersStateList(){
        String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
        render ([map: tokService.getCustomersStateList(), apiKey: apiKey ] as JSON)
    }

    def acceptCall(){
        if(params?.id != null) {
            OautUser account = OautUser.findById(params?.id as Long)
            tokService.changeStatusByAccount(account, SessionState.CLIENT_CALL, SessionState.IN_CALL)
            render "ok"
        }
        render "error"
    }

    def declineCall(){
        if(params?.id != null) {
            OautUser account = OautUser.findById(params?.id as Long)
            tokService.changeStatusByAccount(account, SessionState.CLIENT_CALL, SessionState.INACTIVE)
            render "ok"
        }
        render "error"
    }

    def initCall(){
        if(params?.id != null) {
            OautUser account = OautUser.findById(params?.id as Long)
            tokService.changeStatusByAccount(account, SessionState.INACTIVE, SessionState.COMPANY_CALL)
            render "ok"
        }
        render "error"
    }

    def cancelCall(){
        if(params?.id != null) {
            OautUser account = OautUser.findById(params?.id as Long)
            tokService.changeStatusByAccount(account, SessionState.COMPANY_CALL, SessionState.INACTIVE)
            render "ok"
        }
        render "error"
    }

    def endCall(){
        if(params?.id != null) {
            OautUser account = OautUser.findById(params?.id as Long)
            tokService.changeStatusByAccount(account, SessionState.IN_CALL, SessionState.INACTIVE)
            render "ok"
        }
        render "error"
    }

    def stopActiveCall(){
        print "stop active1";
        //TODO and company, but now only 1 company
        List<TokSession> tokSessions = TokSession.findAllByActiveNotEqual(SessionState.INACTIVE);
        tokSessions.each { it->
            tokService.changeStatusByAccount(it.account, it.active, SessionState.INACTIVE)
        }
        print "stop active2";
    }

}

