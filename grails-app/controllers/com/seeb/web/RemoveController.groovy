package com.seeb.web

import com.seeb.web.oauth.OautUser

class RemoveController {

    def index() {}

    def removeVideoMessage() {
        if(params?.id != null) {
            VideoMessage vm = VideoMessage.findById(params.id)
            if(vm != null){
                vm.delete()
            }
        }

        render "ok"
    }

    def removeUser() {
        if(params?.id != null) {
            OautUser oautUser = OautUser.findById(params.id)
            oautUser.accountLocked = true
            oautUser.save()
        }
        render "ok"
    }
}
