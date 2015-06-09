package com.seeb.web

import com.seeb.web.enums.VideoMessageState
import com.seeb.web.oauth.OautUser

/**
 * Created by savitskyao on 12.05.2015.
 */
class VideoMessage {

    Date date
    VideoMessageState state
    String url
    String archiveId
    static belongsTo = [account: OautUser, company: Company]

    static constraints = {
        date(nullable: false)
        state(nullable: false)
        url(nullable: true, maxSize: 255)
        archiveId(nullable: true, maxSize: 255)
    }

}
