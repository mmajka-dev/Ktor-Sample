package kmm.sample.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val authToken: String
)
