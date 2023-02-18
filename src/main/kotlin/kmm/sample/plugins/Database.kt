package kmm.sample.plugins

import io.ktor.server.application.*
import kmm.sample.db.DatabaseFactory

fun Application.configureDatabase() {
    DatabaseFactory.init()
}