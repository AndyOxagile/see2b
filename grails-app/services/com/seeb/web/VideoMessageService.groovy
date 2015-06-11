package com.seeb.web

import com.opentok.OpenTok
import com.seeb.web.command.VideoMessageDTO
import com.seeb.web.oauth.OautUser
import grails.transaction.Transactional

@Transactional
class VideoMessageService {

    def springSecurityService
    def grailsApplication


    List<VideoMessageDTO> getMessagesListByOauth(){
        def currentUser = springSecurityService.getCurrentUser()
        List<VideoMessage> videoMessages = VideoMessage.findAllByAccount((OautUser)currentUser)
        return makeVMDTOFromVMList(videoMessages, false)
    }

    //TODO stub
    List<VideoMessageDTO> getMessagesListByCompany(){
        List<VideoMessage> videoMessages = VideoMessage.findAll()
        return makeVMDTOFromVMList(videoMessages, true)
    }

    List<VideoMessageDTO> makeVMDTOFromVMList(List<VideoMessage> videoMessages, boolean onlyActive){
        String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
        String apiSecret = grailsApplication.config.grails.plugin.tokbox.secret
        OpenTok opentok = new OpenTok(Integer.parseInt(apiKey), apiSecret);
        List<VideoMessageDTO> result = new LinkedList<VideoMessageDTO>()

        if(videoMessages != null) {
            videoMessages.each { it ->
                if(!onlyActive || !it.account.accountLocked) {
                    result.add(0, VideoMessageDTO.make(it, opentok))
                }
            }
        }
        return result
    }
}
