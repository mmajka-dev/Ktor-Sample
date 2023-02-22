package kmm.sample.plugins

import io.ktor.server.application.*
import kmm.sample.config.db.DatabaseFactory

fun Application.configureDatabase() {
    DatabaseFactory.init()
}