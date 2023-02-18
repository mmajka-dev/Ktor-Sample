package kmm.sample.di

import kmm.sample.utils.Encryptor
import org.koin.dsl.module

val commonModule = module {
    single { Encryptor() }
}