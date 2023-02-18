package kmm.sample.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String,
    val imagePath: String?
)
