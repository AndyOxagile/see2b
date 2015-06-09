// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*', '/html/*']
grails.resources.adhoc.includes = ['/images/**', '/css/**', '/js/**', '/plugins/**', '/html/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}



environments {
    development{
        def baseURL = grails.serverURL ?: "http://localhost:${System.getProperty('server.port', '8080')}/${appName}"
        grails.local.baseURL = baseURL
        oauth {
            providers {
//                twitter {
//                    api = TwitterApi
//                    key = 'BcpsmCkTb5IDvl4MVgMjdxlbk'
//                    secret = 'GIyTiZIWVPM029mr0le2r1NOzZIWzZwZ6vB6FV72fV0aIYNtGr'
//                    successUri = '/auth/success'
//                    failureUri = '/auth/failure'
//                }

                facebook {
                    api = org.scribe.builder.api.FacebookApi
                    key = '843652305715956'
                    secret = 'efaae14b00c5e92468fa57e0651b8044'
                    successUri = '/oauth/facebook/success'
                    failureUri = '/oauth/facebook/error'
                    callback = "${baseURL}/oauth/facebook/callback"
//                    callback = 'http://localhost:8080/seeb/confirm/facebook'
                }

                linkedin {
                    api = org.scribe.builder.api.LinkedInApi
                    key = '77efjctnh7f435'
                    secret = 'mYhNhtCSeICQFfEm'
                    successUri = '/oauth/linkedin/success'
                    failureUri = '/oauth/linkedin/error'
                    callback = "${baseURL}/oauth/linkedin/callback"
                }
                google {
                    api = org.scribe.builder.api.GoogleApi20
                    key = '759446119644-2d2vlm2dsr6pheh49qb1euc788tmk0fn.apps.googleusercontent.com'
                    secret = '2tapoHhVuhbvaCoKXKJY_g_e'
                    successUri = '/oauth/google/success'
                    failureUri = '/oauth/google/error'
                    callback = "${baseURL}/oauth/google/callback"
                    scope = 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
                }
            }
        }

        grails.plugin.springsecurity.oauth.active = true
        grails.plugin.springsecurity.oauth.domainClass = 'OAuthID'
        grails.plugin.springsecurity.oauth.userLookup.oAuthIdsPropertyName = 'oAuthIDs'
        grails.plugin.springsecurity.oauth.registration.askToLinkOrCreateAccountUri = '/oauth/askToLinkOrCreateAccount'
        grails.plugin.springsecurity.oauth.registration.roleNames = ['ROLE_USER']

    }

    production{
        def baseURL = "http://beta.see2b.com:8080/${appName}"
        grails.local.baseURL = baseURL

        //212.98.167.178
        oauth {
            providers {
                facebook {
                    api = org.scribe.builder.api.FacebookApi
                    key = '764752896956809'
                    secret = '30468472448e70043968fc17a3233f71'
                    successUri = '/oauth/facebook/success'
                    failureUri = '/oauth/facebook/error'
                    callback = "${baseURL}/oauth/facebook/callback"
//                    callback = 'http://localhost:8080/seeb/confirm/facebook'
                }

                linkedin {
                    api = org.scribe.builder.api.LinkedInApi
                    key = '77pgdqul7j9dj6'
                    secret = '2JWSC99geuGTm6wb'
                    successUri = '/oauth/linkedin/success'
                    failureUri = '/oauth/linkedin/error'
                    callback = "${baseURL}/oauth/linkedin/callback"
                }
                google {
                    api = org.scribe.builder.api.GoogleApi20
                    key = '759446119644-2d2vlm2dsr6pheh49qb1euc788tmk0fn.apps.googleusercontent.com'
                    secret = '2tapoHhVuhbvaCoKXKJY_g_e'
                    successUri = '/oauth/google/success'
                    failureUri = '/oauth/google/error'
                    callback = "${baseURL}/oauth/google/callback"
                    scope = 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
                }
            }
        }

        grails.plugin.springsecurity.oauth.active = true
        grails.plugin.springsecurity.oauth.domainClass = 'OAuthID'
        grails.plugin.springsecurity.oauth.userLookup.oAuthIdsPropertyName = 'oAuthIDs'
        grails.plugin.springsecurity.oauth.registration.askToLinkOrCreateAccountUri = '/oauth/askToLinkOrCreateAccount'
        grails.plugin.springsecurity.oauth.registration.roleNames = ['ROLE_USER']

    }
}
// Added by the Spring Security OAuth plugin:
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
        '/':                            ['permitAll'],
        '/index':                       ['permitAll'],
        '/index.gsp':                   ['permitAll'],
        '/**/js/**':                    ['permitAll'],
        '/**/css/**':                   ['permitAll'],
        '/**/images/**':                ['permitAll'],
        '/**/favicon.ico':              ['permitAll'],
        '/login/**':                    ['permitAll'],
        '/logout/**':                   ['permitAll'],
        '/oauth/**':                    ['permitAll'],
        '/dashboard/**':                ['permitAll'],
        '/companyDashboard/**':         ['permitAll'],
        '/localOAuth/createAndLoginExternalAccount': ['permitAll'],
        '/tokbox/**':                   ['permitAll'],
        '/admin.html':                  ['permitAll'],
        '/admin':                       ['permitAll'],
        '/index.html':                  ['permitAll'],
        '/features.html':               ['permitAll'],
        '/about.html':                  ['permitAll'],
        '/contact.html':                ['permitAll'],
        '/login.html':                  ['permitAll'],
        '/signup.html':                 ['permitAll'],
        '/signup':                      ['permitAll'],
        '/conference.html':             ['permitAll'],
        '/conference':                  ['permitAll'],
        '/try':                         ['permitAll'],

        '/**/html/**':                  ['permitAll'],

]

grails.views.gsp.encoding="UTF-8"


// Added by the Spring Security OAuth plugin:
grails.plugin.springsecurity.oauth.domainClass = 'com.seeb.web.oauth.OAuthID'


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.seeb.web.oauth.OautUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.seeb.web.oauth.OautUserOautRole'
grails.plugin.springsecurity.authority.className = 'com.seeb.web.oauth.OautRole'
grails.plugin.springsecurity.authority.groupAuthorityNameField = 'authorities'
grails.plugin.springsecurity.useRoleGroups = true


grails.plugin.tokbox.apiKey = '45252222'
grails.plugin.tokbox.secret = 'c7c1ce8da4d605f3aa727332a4562a6bdaf574ca'

grails.local.connection.timeout = 3000
grails.local.verifyLink = "/dashboard/verify/"
grails.local.logoLink = "/static/images/logo.png"

grails {
    mail {
        host = "mail.see2b.com"
        port = 587
        username = "support@see2b.com"
        password = "livevideo"
        props = ["mail.smtp.starttls.enable":"true",
                 "mail.smtp.port":"587"]
    }
}

