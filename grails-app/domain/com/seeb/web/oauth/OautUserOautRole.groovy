package com.seeb.web.oauth

import org.apache.commons.lang.builder.HashCodeBuilder

class OautUserOautRole implements Serializable {

    private static final long serialVersionUID = 1

    OautUser oautUser
    OautRole oautRole

    boolean equals(other) {
        if (!(other instanceof OautUserOautRole)) {
            return false
        }

        other.oautUser?.id == oautUser?.id &&
                other.oautRole?.id == oautRole?.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
        if (oautUser) builder.append(oautUser.id)
        if (oautRole) builder.append(oautRole.id)
        builder.toHashCode()
    }

    static OautUserOautRole get(long oautUserId, long oautRoleId) {
        OautUserOautRole.where {
            oautUser == OautUser.load(oautUserId) &&
                    oautRole == OautRole.load(oautRoleId)
        }.get()
    }

    static boolean exists(long oautUserId, long oautRoleId) {
        OautUserOautRole.where {
            oautUser == OautUser.load(oautUserId) &&
                    oautRole == OautRole.load(oautRoleId)
        }.count() > 0
    }

    static OautUserOautRole create(OautUser oautUser, OautRole oautRole, boolean flush = false) {
        def instance = new OautUserOautRole(oautUser: oautUser, oautRole: oautRole)
        instance.save(flush: flush, insert: true)
        instance
    }

    static boolean remove(OautUser u, OautRole r, boolean flush = false) {
        if (u == null || r == null) return false

        int rowCount = OautUserOautRole.where {
            oautUser == OautUser.load(u.id) &&
                    oautRole == OautRole.load(r.id)
        }.deleteAll()

        if (flush) {
            OautUserOautRole.withSession { it.flush() }
        }

        rowCount > 0
    }

    static void removeAll(OautUser u, boolean flush = false) {
        if (u == null) return

        OautUserOautRole.where {
            oautUser == OautUser.load(u.id)
        }.deleteAll()

        if (flush) {
            OautUserOautRole.withSession { it.flush() }
        }
    }

    static void removeAll(OautRole r, boolean flush = false) {
        if (r == null) return

        OautUserOautRole.where {
            oautRole == OautRole.load(r.id)
        }.deleteAll()

        if (flush) {
            OautUserOautRole.withSession { it.flush() }
        }
    }

    static constraints = {
        oautRole validator: { OautRole r, OautUserOautRole ur ->
            if (ur.oautUser == null) return
            boolean existing = false
            OautUserOautRole.withNewSession {
                existing = OautUserOautRole.exists(ur.oautUser.id, r.id)
            }
            if (existing) {
                return 'userRole.exists'
            }
        }
    }

    static mapping = {
        id composite: ['oautRole', 'oautUser']
        version false
    }
}
