package kmm.sample.service

import kmm.sample.datasource.AuthDatasource
import kmm.sample.model.request.CreateUserRequest
import kmm.sample.utils.exceptions.UserAlreadyExistsException
import kmm.sample.utils.wrapper.ServiceResponse

class SignUpService(
    private val datasource: AuthDatasource
) {
    suspend operator fun invoke(request: CreateUserRequest): ServiceResponse<Unit, Exception> {
        return try {
            val isUserExists = datasource.getUserByEmail(request.email) != null
            if(isUserExists) {
                ServiceResponse.Failure(UserAlreadyExistsException())
            } else {
                datasource.createUser(request)
                ServiceResponse.Success(Unit)
            }
        } catch (e: Exception) {
            ServiceResponse.Failure(e)
        }
    }
}