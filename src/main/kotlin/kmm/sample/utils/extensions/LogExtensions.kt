package kmm.sample.utils.extensions

import io.ktor.server.application.*
import io.ktor.util.pipeline.*

fun PipelineContext<*, ApplicationCall>.log(text: String) {
    call.application.environment.log.info(text)
}
