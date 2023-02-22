package kmm.sample.config.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JWTConfig private constructor(secret: String){

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun createAccessToken(id: Int) = JWT
        .create()
        .withAudience(AUDIENCE)
        .withIssuer(ISSUER)
        .withClaim(CLAIM, id)
        .withExpiresAt(Date(System.currentTimeMillis() + DEFAULT_EXPIRATION_TIME))
        .sign(algorithm)

    companion object {
        private const val DEFAULT_EXPIRATION_TIME = 60000
        private const val ISSUER = "ktor-sample"
        private const val AUDIENCE = "ktor-sample"
        const val CLAIM = "id"

        lateinit var instance: JWTConfig
            private set

        fun init(secret: String) {
            synchronized(this) {
                if(!this::instance.isInitialized) {
                    instance = JWTConfig(secret)
                }
            }
        }
    }
}