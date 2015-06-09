package com.seeb.web.command

import com.opentok.Archive
import com.opentok.OpenTok
import com.seeb.web.AccountInfo
import com.seeb.web.VideoMessage
import com.seeb.web.enums.VideoMessageState

/**
 * Created by savitskyao on 26.05.2015.
 */

class VideoMessageDTO {
    String date
    String state
    String user
    String company
    String url
    String urlName
    Boolean isProcessing = false
    Boolean isAnavailible = false

    static VideoMessageDTO make(VideoMessage message, OpenTok opentok){
        VideoMessageDTO result = new VideoMessageDTO()
        if(message != null){
            result.date = message.date.format('MM/dd/yyyy HH:dd')
            result.state = message.state.toString()

            AccountInfo ai = AccountInfo.findByAccount(message.account)
            result.user = ((ai && ai.firstName)?ai.firstName:"-") + " " + ((ai && ai.lastName)?ai.lastName:"-")

            result.company = message.company.name
            if(!VideoMessageState.MISSED.equals(message.state)){
                if(opentok == null) {
                    result.isAnavailible = false
                } else {
                    Archive arch = opentok.getArchive(message.archiveId)
                    if(arch.url == null || arch.url.isEmpty()) {
                        result.isProcessing = true
                    } else {
                        result.url = arch.url.substring(0, arch.url.indexOf('?'))
                        result.urlName = arch.name
                    }
                }
            }
        }
        result
    }


}
