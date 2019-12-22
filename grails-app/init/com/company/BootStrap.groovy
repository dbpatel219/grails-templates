package com.company

class BootStrap {

    def init = { servletContext ->
        if (MyUser.count()) {
            println "Existing Users in DB"
        } else {
            println "Creating admin user."

            try {
                def user = new MyUser()
                user.username = 'Admin'
                user.password = 'LetMeIn'
                user.save(flush: true)

                def role = new MyRole()
                role.authority = 'ROLE_ADMIN'
                role.save(flush: true)

                def userRole = new MyUserMyRole()
                userRole.myRole = role
                userRole.myUser = user
                if (userRole.errors) {
                    println "Errors " + userRole.errors
                } else {
                    userRole.save(flush: true)
                }
            } catch (Exception e) {
                println 'Ohh no.....'
                println e
            }

            println 'Done creating a admin user'
        }

    }
    def destroy = {
    }
}
