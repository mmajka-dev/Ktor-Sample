package kmm.sample.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kmm.sample.data.AuthDatasource
import kmm.sample.model.request.CreateUserRequest
import kmm.sample.model.request.LoginRequest
import org.koin.ktor.ext.inject

fun Application.authRouting() {

    val datasource by inject<AuthDatasource>()

    routing {
        route(Routes.Auth.base) {
            post(Routes.Auth.signIn) {
                val request = call.receive<LoginRequest>()
                try {
                    val token = datasource.signIn(request)
                    if (token != null) {
                        call.respond(HttpStatusCode.OK, token)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "User not found")
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, e.message.toString())
                }
            }
            post(Routes.Auth.signUp) {
                val request = call.receive<CreateUserRequest>()
                try {
                    val isUserExisting = datasource.getUserByEmail(request.email) != null
                    if (!isUserExisting) {
                        datasource.signUp(request)
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.Conflict, "Email already used")
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, e.message.toString())
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