package kmm.sample.utils

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Encryptor {
    private val appConfig = HoconApplicationConfig(ConfigFactory.load())
    private val key = appConfig.property("secrets.encryptKey").getString()
    private val algorithm = appConfig.property("secrets.algorithm").getString()

    fun encrypt(arg: String): String {
        val hashKey = hex(key)
        val hmacKey = SecretKeySpec(hashKey, algorithm)
        val hmac = Mac.getInstance(algorithm).apply {
            init(hmacKey)
        }

        return hex(hmac.doFinal(arg.toByteArray(Charsets.UTF_8)))
    }
}