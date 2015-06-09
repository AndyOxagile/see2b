package com.seeb.web.command
import com.seeb.web.AccountInfo
import com.seeb.web.TokSession
import com.seeb.web.oauth.OAuthStatusInfo

/**
 * Created by savitskyao on 26.05.2015.
 */

class CustomerStateDTO {
    Long id
    String sessionId
    String companyToken
    String state
    Boolean isAvailible = false
    String name


    static CustomerStateDTO make(TokSession tokSession, Long timeout){
        CustomerStateDTO result = new CustomerStateDTO()
        if(tokSession != null){

            result.sessionId = tokSession.sessionId
            result.companyToken = tokSession.companyToken
            result.state = tokSession.active.toString()

            if(tokSession.account!=null) {
                result.id = tokSession.account.id
                AccountInfo ai = AccountInfo.findByAccount(tokSession.account)
                OAuthStatusInfo info = OAuthStatusInfo.findByAccount(tokSession.account)
                result.name = ((ai && ai.firstName)?ai.firstName:"-") + " " + ((ai && ai.lastName)?ai.lastName:"-")
                result.isAvailible = info?.isAvailible != null && (new Date().getTime() - info.isAvailible.getTime() < timeout)
            }
        }
        result
    }
}
