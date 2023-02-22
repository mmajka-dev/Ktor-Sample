package kmm.sample.datasource

import kmm.sample.model.request.CreateUserRequest
import kmm.sample.model.request.LoginRequest
import kmm.sample.model.response.UserResponse

interface AuthDatasource {

    suspend fun getUserByCredentials(request: LoginRequest): UserResponse?
    suspend fun createUser(request: CreateUserRequest)
    suspend fun getUserByEmail(email: String): UserResponse?
}