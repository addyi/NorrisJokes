package de.eosn.norrisjokes.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import de.eosn.norrisjokes.repo.ChuckNorrisJoke

@Database(entities = [ChuckNorrisJoke::class], version = 1)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun getChuckNorrisJokeDAO(): ChuckNorrisJokeDAO
}