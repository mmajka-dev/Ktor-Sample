package kmm.sample.data

import kmm.sample.model.request.CreateUserRequest
import kmm.sample.model.request.LoginRequest
import kmm.sample.model.response.LoginResponse
import kmm.sample.model.response.UserResponse

interface AuthDatasource {

    suspend fun signIn(request: LoginRequest): LoginResponse?
    suspend fun signUp(request: CreateUserRequest)
    suspend fun getUserByEmail(email: String): UserResponse?
}