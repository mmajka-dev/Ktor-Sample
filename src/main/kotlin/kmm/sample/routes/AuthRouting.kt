package kmm.sample.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kmm.sample.model.request.CreateUserRequest
import kmm.sample.model.request.LoginRequest
import kmm.sample.service.SignInService
import kmm.sample.service.SignUpService
import kmm.sample.utils.exceptions.UserAlreadyExistsException
import kmm.sample.utils.exceptions.UserNotFoundException
import kmm.sample.utils.extensions.getMessage
import org.koin.ktor.ext.inject

fun Application.authRouting() {

    val signInService by inject<SignInService>()
    val signUpService by inject<SignUpService>()

    routing {
        route(Routes.Auth.base) {
            post(Routes.Auth.signIn) {
                val request = call.receive<LoginRequest>()
                signInService(request)
                    .onSuccess { response ->
                        call.respond(HttpStatusCode.OK, response)
                    }
                    .onFailure { exception ->
                        when (exception) {
                            is UserNotFoundException -> call.respond(HttpStatusCode.NotFound)
                            else -> call.respond(HttpStatusCode.InternalServerError, exception.getMessage())
                        }

                    }
            }

            post(Routes.Auth.signUp) {
                val request = call.receive<CreateUserRequest>()
                signUpService(request)
                    .onSuccess {
                        call.respond(HttpStatusCode.OK)
                    }
                    .onFailure { exception ->
                        when (exception) {
                            is UserAlreadyExistsException -> call.respond(HttpStatusCode.Conflict)
                            else -> call.respond(HttpStatusCode.InternalServerError, exception.getMessage())
                        }
                    }
            }
            get(Routes.Auth.forgotPassword) {
                call.respond(HttpStatusCode.OK, "Forgot password")
            }
            get(Routes.Auth.resetPassword) {
                call.respond(HttpStatusCode.OK, "Reset password")
            }
        }
    }
}