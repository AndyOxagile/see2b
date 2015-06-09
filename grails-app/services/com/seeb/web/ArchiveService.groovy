package com.seeb.web
import com.opentok.Archive
import com.opentok.OpenTok
import com.opentok.exception.OpenTokException
import com.seeb.web.enums.SessionState
import com.seeb.web.enums.VideoMessageState
import grails.transaction.Transactional

@Transactional
class ArchiveService {

    def grailsApplication
    def springSecurityService
    def tokService
    def companyService

    def registerCall(TokSession tokSession){
//        def currentUser = springSecurityService.getCurrentUser()
        def company = companyService.getFirstCompany()

        VideoMessageState state = tokSession.currentArchiveId==null?VideoMessageState.MISSED:
                SessionState.IN_CALL.equals(tokSession.active)?VideoMessageState.TALKED:VideoMessageState.LEFT

        VideoMessage videoMessage = new VideoMessage(
                date: new Date(),
                state: state,
                archiveId: tokSession.currentArchiveId,
                account: tokSession.account,
                company: company)
        videoMessage.save(flush: true, validate: true, failOnError: true)
    }

    def startRecordMessage(TokSession tokSession){
        String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
        String apiSecret = grailsApplication.config.grails.plugin.tokbox.secret
        OpenTok opentok = new OpenTok(Integer.parseInt(apiKey), apiSecret)

        Archive archive = null;
        try {
            archive = opentok.startArchive(tokSession.sessionId, "rec"+ new Date().getTime());
        } catch (OpenTokException e) {
            e.printStackTrace();
            return null;
        }

        tokSession.currentArchiveId = archive.id
        tokSession.save(flush: true)
    }

    def stopRecordAndLeaveMessage(TokSession tokSession){
        String apiKey = grailsApplication.config.grails.plugin.tokbox.apiKey
        String apiSecret = grailsApplication.config.grails.plugin.tokbox.secret
        OpenTok opentok = new OpenTok(Integer.parseInt(apiKey), apiSecret);

        Archive archive = null;
        try {
            archive = opentok.stopArchive(tokSession.currentArchiveId);
        } catch (OpenTokException e) {
            e.printStackTrace();
            return null;
        }
        registerCall(tokSession)
        tokSession.currentArchiveId = null
    }
}
