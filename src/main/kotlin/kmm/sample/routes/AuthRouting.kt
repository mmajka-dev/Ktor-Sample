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
                    val user = datasource.signIn(request)
                    if (user != null) {
                        call.respond(HttpStatusCode.OK, user)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, e.message.toString())
                }
            }
            post(Routes.Auth.signUp) {
                val request = call.receive<CreateUserRequest>()
                try {
                    datasource.signUp(request)
                    call.respond(HttpStatusCode.OK)
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