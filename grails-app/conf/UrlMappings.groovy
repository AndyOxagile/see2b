class UrlMappings {

	static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // Change plugin controller by our implementation, @see LocalOautController
        '/oauth/createaccount'(controller: 'localOAuth', action: 'createAccount')
        '/oauth/askToLinkOrCreateAccount'(controller: 'localOAuth', action: 'askToLinkOrCreateAccount')
        '/oauth/createAndLoginTempAccount'(controller: 'localOAuth', action: 'createAndLoginTempAccount')
        '/try'(controller: 'localOAuth', action: 'createAndLoginTempAccount')
        '/tryExternal'(controller: 'localOAuth', action: 'createAndLoginExternalAccount')



//        "/oauth/$provider/success"(controller: 'springSecurityOAuth', action: 'onSuccess')
//        "/oauth/$provider/failure"(controller: 'springSecurityOAuth', action: 'onFailure')
//        '/oauth/askToLinkOrCreateAccount'(controller: 'springSecurityOAuth', action: 'askToLinkOrCreateAccount')
//        '/oauth/linkaccount'(controller: 'springSecurityOAuth', action: 'linkAccount')
//        '/oauth/createaccount'(controller: 'springSecurityOAuth', action: 'createAccount')



        "/"(redirect: '/dashboard/')
        "/index"(redirect: '/html/index.html')
        "/index.html"(redirect: '/html/index.html')
        "/features.html"(redirect: '/html/features.html')
        "/about.html"(redirect: '/html/about.html')
        "/contact.html"(redirect: '/html/contact.html')

        "/login.html"(redirect: '/login/full.html')
        "/login"(redirect: '/login/full.html')
        "/logout"(redirect: '/j_spring_security_logout')

        "/signup.html"(redirect:"/oauth/createaccount")
        "/signup"(redirect:"/oauth/createaccount")

        "/admin.html"(redirect:"/companyDashboard/")
        "/admin"(redirect:"/companyDashboard/")


        "/user/header"(view:"/user/header")
        "/user/footer"(view:"/user/footer")

        "/conference"(redirect: '/tokbox/index')
        "/conference.html"(redirect: '/tokbox/index')

//        "/"(view:"/index")
        "500"(view:'/error')
	}
}
