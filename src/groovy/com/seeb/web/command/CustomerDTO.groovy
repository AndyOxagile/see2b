package com.seeb.web.command

import com.seeb.web.AccountInfo
import com.seeb.web.oauth.OautUser
/**
 * Created by savitskyao on 26.05.2015.
 */

class CustomerDTO {
    Long id
    String companyName
    String name

//    String token
//    String sessionId
//    boolean isCall = false
//    boolean isOnline = false

    static CustomerDTO make(OautUser account){
        CustomerDTO result = new CustomerDTO()

        if(account != null){
            result.id = account.id
            AccountInfo ai = AccountInfo.findByAccount(account)
            if(ai && (ai.firstName || ai.lastName)) {
                result.name = ((ai && ai.firstName) ? ai.firstName : " ") + " " + ((ai && ai.lastName) ? ai.lastName : " ")
            } else {
                result.name = "User No "+account.id;
            }

            result.companyName = ((ai && ai.companyName)?ai.firstName:"No company")
        }
        result
    }


}
