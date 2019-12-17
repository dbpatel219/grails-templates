package grails3_3_11_template

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class MyUser implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String email
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    void setEmail(String _email) {
        email = _email?.toLowerCase()
    }

    void setUsername(String _username) {
        username = _username?.toLowerCase()
    }

    Set<MyRole> getAuthorities() {
        (MyUserMyRole.findAllByMyUser(this) as List<MyUserMyRole>)*.myRole as Set<MyRole>
    }

    Boolean isAdmin() {
        def authorities = getAuthorities()*.authority
        return authorities.contains('ROLE_ADMIN')
    }

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
    }

    static mapping = {
        password column: '`password`'
    }

    static namedQueries = {

    }
}
