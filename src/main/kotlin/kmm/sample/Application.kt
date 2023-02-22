package kmm.sample

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kmm.sample.plugins.*
import kmm.sample.plugins.security.configureSecurity

fun main() {
    embeddedServer(CIO, port = System.getenv("PORT")?.toInt() ?: 8080, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureKoin()
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    //configureHTTP()
    configureSecurity()
    configureRouting()
}
