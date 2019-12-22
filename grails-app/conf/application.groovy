def property(String name) {
    return System.env[name] ?: System.getProperty(name)
}

environments {
    development {
        grails.serverURL = 'http://localhost:8081'
        server.port = 8081
    }
    production {
        grails.serverURL = property('SERVER_URL') ?: "https://company.com"
    }
}

grails.plugin.springsecurity.ui.gsp.layoutRegister = "main"

grails.plugin.springsecurity.rest.token.storage.jwt.secret='qrD6h8K6S9503Q06Y5Rfk19TErImPYqa'

grails.plugin.springsecurity.password.algorithm='bcrypt'

grails.plugin.springsecurity.rememberMe.cookieName = 'comapny_portal'
grails.plugin.springsecurity.rememberMe.key = 'CompanyAPI'

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.company.MyUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.company.MyUserMyRole'
grails.plugin.springsecurity.authority.className = 'com.company.MyRole'

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.successHandler.defaultTargetUrl = "/"

grails.plugin.springsecurity.ui.register.emailBody = 'Welcome to Company $user.username!<br><br>  Please activate your registration via $url'
grails.plugin.springsecurity.ui.register.emailFrom = "noreply@company.com"
grails.plugin.springsecurity.ui.register.emailSubject = 'Activate your Company account'

grails.plugin.springsecurity.ui.forgotPassword.emailBody = 'Hi $user.username,<br><br> Looks like you need some help with your password.  If you didn\'t make this request then ignore the email; no changes have been made.<br><br>  If you did request a password reset, then click $url to reset your password'
grails.plugin.springsecurity.ui.forgotPassword.emailFrom = "noreply@company.com"
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = 'Reset your Company password'


grails.plugin.springsecurity.useSwitchUserFilter = true
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/signUp',         access: ['permitAll']],
        [pattern: '/register/forgotPassword', access: ['permitAll']],
        [pattern: '/reset/**',          access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/login/**',       access: ['permitAll', 'IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/user/**',        access: ['ROLE_ADMIN', 'IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/console/**',     access: ['ROLE_ADMIN', 'IS_AUTHENTICATED_REMEMBERED']],
        [pattern: '/static/console/**', access: ['ROLE_ADMIN', 'IS_AUTHENTICATED_REMEMBERED']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/reset/**',       filters: 'none'],
        [pattern: '/**',             filters: 'JOINED_FILTERS']
]

environments {
    development {
        grails.plugin.fields.disableLookupCache = true

        dataSource {
            if (property('DATABASE_URL')) {
                dbCreate = "update"
                driverClassName = "org.postgresql.Driver"
                dialect = org.hibernate.dialect.PostgreSQLDialect

                uri = new URI(property('DATABASE_URL'))
                url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ApplicationName=CC${appName}"

                credentials = uri.userInfo.split(":")
                username = uri.userInfo.split(":")[0]
                password = ""

                if (credentials.length == 2) {
                    password = uri.userInfo.split(":")[1]
                }

                logSql = true
            } else {
                dbCreate = "update"
                url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            }
        }

        grails.plugin.sentry.active = false
    }
    production {

        grails.plugin.springsecurity.secureChannel.definition = [
                [pattern: '/**', access: 'REQUIRES_SECURE_CHANNEL']
        ]

        grails.plugin.springsecurity.rememberMe.useSecureCookie = true

        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQLDialect

            uri = new URI(property('DATABASE_URL'))
            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?ApplicationName=${appName}"
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]

            logSql = false

            log.info
            // Documentation
            // - https://docs.grails.org/3.3.8/guide/single.html#dataSource
            // - http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html

            pooled = true
            properties {
                initialSize = 10
                maxActive = 40
                minIdle = 10
                maxWait = 10000
                maxAge = 2 * 60000
                timeBetweenEvictionRunsMillis = 5000
                minEvictableIdleTimeMillis = 60000
                validationQuery = "SELECT 1"
                validationQueryTimeout = 3
                validationInterval = 15000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
            }
        }
    }
}
