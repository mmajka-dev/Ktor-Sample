package kmm.sample.plugins

import io.ktor.server.application.*
import kmm.sample.di.authModule
import kmm.sample.di.authServiceModule
import kmm.sample.di.commonModule
import org.koin.core.Koin
import org.koin.core.context.startKoin

fun Application.configureKoin() {
    startKoin {
        modules(listOf(authModule, authServiceModule, commonModule))
    }
}