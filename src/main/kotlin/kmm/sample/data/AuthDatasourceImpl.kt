package kmm.sample.data

import kmm.sample.model.LoginRequest
import kmm.sample.model.LoginResponse
import java.util.UUID

class AuthDatasourceImpl: AuthDatasource {
    override fun sayHello() = "Hello motherfucker"
    override fun signIn(request: LoginRequest): LoginResponse {
        return LoginResponse(UUID.randomUUID().toString(), "Karakan", "karakan@chuj.com", null)
    }
}