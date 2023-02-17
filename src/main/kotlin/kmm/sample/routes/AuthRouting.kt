package kmm.sample.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kmm.sample.data.AuthDatasource
import org.koin.ktor.ext.inject

fun Application.authRouting() {

    val datasource by inject<AuthDatasource>()

    routing {
        route(Routes.Auth.base) {
            get(Routes.Auth.signIn) {
                call.respond(HttpStatusCode.OK, "Sign In ${datasource.sayHello()}")
            }
            get(Routes.Auth.signUp) {
                call.respond(HttpStatusCode.OK, "Sign Up")
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