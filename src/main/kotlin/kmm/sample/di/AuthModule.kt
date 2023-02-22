package kmm.sample.di

import kmm.sample.datasource.AuthDatasource
import kmm.sample.datasource.AuthDatasourceImpl
import kmm.sample.service.SignInService
import kmm.sample.service.SignUpService
import org.koin.dsl.module

val authModule = module {
    single<AuthDatasource> { AuthDatasourceImpl(get()) }
}

val authServiceModule = module {
    single { SignInService(get()) }
    single { SignUpService(get()) }
}