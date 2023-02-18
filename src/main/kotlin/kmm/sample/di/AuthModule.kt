package kmm.sample.di

import kmm.sample.data.AuthDatasource
import kmm.sample.data.AuthDatasourceImpl
import kmm.sample.utils.Encryptor
import org.koin.dsl.module

val authModule = module {
    single<AuthDatasource> { AuthDatasourceImpl(get()) }
}