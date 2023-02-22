package kmm.sample.ui

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.util.pipeline.*
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.title

suspend fun PipelineContext<*, ApplicationCall>.signIn() {
    call.respondHtml(HttpStatusCode.OK) {
        head {
            title { "Sign In" }
        }
        body {
            div() {

            }
        }
    }
}