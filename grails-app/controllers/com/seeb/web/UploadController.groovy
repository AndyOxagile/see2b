package com.seeb.web

import com.seeb.web.oauth.OautUser
import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import uk.co.desirableobjects.ajaxuploader.exception.FileUploadException

import javax.servlet.http.HttpServletRequest

class UploadController {

    def companyService
    def springSecurityService

    def getCompanyLogo() {
        Company c = companyService.getFirstCompany()
        if (c?.companyLogo?.logo != null) {
            InputStream inputStreamData = new ByteArrayInputStream(c?.companyLogo?.logo);
            render file: inputStreamData, contentType: 'image/png'
        }
    }

    def uploadCompanyLogo = {
        try {
            Company c = companyService.getFirstCompany()
            InputStream inputStream = selectInputStream(request)
            byte[] b = inputStream.getBytes();
            CompanyLogo cl = c.companyLogo != null ? c.companyLogo : new CompanyLogo(company: c);
            cl.logo = b
            cl.save()
            return render(text: [success: true] as JSON, contentType: 'text/json')
        } catch (FileUploadException e) {
            log.error("Failed to upload file.", e)
            return render(text: [success: false] as JSON, contentType: 'text/json')
        }
    }

    private InputStream selectInputStream(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile uploadedFile = ((MultipartHttpServletRequest) request).getFile('qqfile')
            return uploadedFile.inputStream
        }
        return request.inputStream
    }

    def getAccountLogo() {
        OautUser user = null
        if (params?.id != null) {
            user = OautUser.findById(params.id)
        } else if (springSecurityService.isLoggedIn()) {
            user = (OautUser) (springSecurityService.getCurrentUser())
        }

        if (user != null) {
            AccountLogo accountLogo = AccountLogo.findByAccount(user)
            if (accountLogo != null) {
                InputStream inputStreamData = new ByteArrayInputStream(accountLogo?.logo);
                render file: inputStreamData, contentType: 'image/png'
            }
        }
    }

    def uploadAccountLogo = {
        try {
            if (springSecurityService.isLoggedIn()) {
                OautUser user = (OautUser) (springSecurityService.getCurrentUser())
                AccountLogo accountLogo = AccountLogo.findByAccount(user)
                InputStream inputStream = selectInputStream(request)
                byte[] b = inputStream.getBytes();
                if(accountLogo== null){
                    accountLogo = new AccountLogo(account: user)
                }
                accountLogo.logo = b
                accountLogo.save()
                return render(text: [success: true] as JSON, contentType: 'text/json')
            }
        }catch (FileUploadException e) {
            log.error("Failed to upload file.", e)
            return render(text: [success:false] as JSON, contentType:'text/json')
        }
    }
}
