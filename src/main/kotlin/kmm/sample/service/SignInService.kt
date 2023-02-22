package kmm.sample.service

import kmm.sample.config.auth.JWTConfig
import kmm.sample.datasource.AuthDatasource
import kmm.sample.model.request.LoginRequest
import kmm.sample.model.response.LoginResponse
import kmm.sample.utils.exceptions.UserNotFoundException
import kmm.sample.utils.wrapper.ServiceResponse

class SignInService(
    private val authDatasource: AuthDatasource
) {
    suspend operator fun invoke(request: LoginRequest): ServiceResponse<LoginResponse, Exception> {
        return try {
            val user =  authDatasource.getUserByCredentials(request)
            if(user == null) {
                ServiceResponse.Failure(UserNotFoundException())
            } else {
                val token = JWTConfig.instance.createAccessToken(user.id)
                ServiceResponse.Success(LoginResponse(token))
            }
        } catch (e: Exception) {
            ServiceResponse.Failure(e)
        }

    }
}