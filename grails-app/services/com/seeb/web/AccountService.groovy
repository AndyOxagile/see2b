package com.seeb.web

import com.seeb.web.command.AccountInfoDTO
import com.seeb.web.oauth.OAuthStatusInfo
import com.seeb.web.oauth.OautUser
import grails.transaction.Transactional

@Transactional
class AccountService {
    def springSecurityService

    AccountInfoDTO currentAccountInfo() {
        OautUser currentUser = (OautUser)springSecurityService.getCurrentUser()
        AccountInfo currentInfo = AccountInfo.findByAccount(currentUser)
        OAuthStatusInfo info = OAuthStatusInfo.findByAccount(currentUser)
        AccountInfoDTO ai
        if(currentInfo != null){
            ai = AccountInfoDTO.fromAccountInfo(currentInfo)
        } else {
            ai = AccountInfoDTO.generateNew()
        }

        if(!info.verified) {
            ai.needVerify = true
        }
        ai
    }

    def updateInfo(AccountInfoDTO accountInfo) {
        def currentUser = springSecurityService.getCurrentUser()
        AccountInfo currentInfo = AccountInfo.findByAccount((OautUser)currentUser)
        if(currentInfo == null){
            currentInfo = new AccountInfo()
        }
        if(currentInfo.account == null) {
            currentInfo.account = (OautUser) currentUser
        }

        currentInfo = accountInfo.updateAccountInfo(currentInfo)
        currentInfo.save(flush: true, validate: true, failOnError: true)
    }
}
