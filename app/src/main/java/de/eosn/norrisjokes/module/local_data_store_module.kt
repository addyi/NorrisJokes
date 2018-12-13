package de.eosn.norrisjokes.module

import androidx.room.Room
import de.eosn.norrisjokes.repo.local.JokeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val localDataStore = module {

    single { Room.databaseBuilder(androidApplication(), JokeDatabase::class.java, "joke-db").build() }

    single { get<JokeDatabase>().getChuckNorrisJokeDAO() }

}