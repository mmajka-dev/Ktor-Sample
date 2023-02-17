package kmm.sample.data

import kmm.sample.model.LoginRequest
import kmm.sample.model.LoginResponse

interface AuthDatasource {

    fun sayHello(): String

    fun signIn(request: LoginRequest): LoginResponse
}