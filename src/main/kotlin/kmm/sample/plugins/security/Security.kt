package kmm.sample.plugins.security

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*

fun Application.configureSecurity() {
    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val key = appConfig.property("secrets.tokenSecret").getString()

    JWTConfig.init(key)

    authentication {
        jwt {
            verifier(JWTConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfig.CLAIM).asInt()
                if (claim != null) {
                    UserPrincipal(claim)
                } else {
                    null
                }
            }
        }
    }
}
