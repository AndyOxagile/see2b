import com.seeb.web.AccountInfo
import com.seeb.web.Company
import com.seeb.web.VideoMessage
import com.seeb.web.enums.VideoMessageState

class BootStrap {
    def companyService

    def init = { servletContext ->
        companyService.createStubCompany()
    }

    def destroy = {
    }
}
