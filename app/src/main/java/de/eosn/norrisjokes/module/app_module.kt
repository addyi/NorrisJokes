package de.eosn.norrisjokes.module

import de.eosn.norrisjokes.repo.NorrisRepository
import de.eosn.norrisjokes.repo.NorrisRepositoryImpl
import de.eosn.norrisjokes.viewmodel.NorrisViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single<NorrisRepository> { NorrisRepositoryImpl(get(), get()) }

    viewModel { NorrisViewModel(get()) }
}