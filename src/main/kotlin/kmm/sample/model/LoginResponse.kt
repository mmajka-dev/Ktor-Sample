package kmm.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val uuid: String,
    val username: String,
    val email: String,
    val imagePath: String?
)