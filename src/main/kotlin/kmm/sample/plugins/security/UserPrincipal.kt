package kmm.sample.plugins.security

import io.ktor.server.auth.*

data class UserPrincipal(
    val id: Int
): Principal