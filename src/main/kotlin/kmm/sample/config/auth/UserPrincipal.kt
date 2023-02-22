package kmm.sample.config.auth

import io.ktor.server.auth.*

data class UserPrincipal(
    val id: Int
): Principal