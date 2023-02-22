package kmm.sample.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val uuid: String,
    val username: String,
    val email: String,
    val imagePath: String?
)