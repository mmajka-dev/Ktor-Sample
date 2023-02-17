package kmm.sample.di

import kmm.sample.data.AuthDatasource
import kmm.sample.data.AuthDatasourceImpl
import org.koin.dsl.module

val authModule = module {
    single<AuthDatasource> { AuthDatasourceImpl() }
}