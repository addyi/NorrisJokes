package de.eosn.norrisjokes.repo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.eosn.norrisjokes.repo.ChuckNorrisJoke

@Dao
interface ChuckNorrisJokeDAO {

    @Insert
    fun insertJokes(vararg arrayOfChuckNorrisJokes: ChuckNorrisJoke)

    @Delete
    fun deleteJoke(chuckNorrisJoke: ChuckNorrisJoke)

    @Query("SELECT * FROM chuck_norris_joke")
    fun getAllJokes(): LiveData<List<ChuckNorrisJoke>>

    @Query("SELECT * FROM chuck_norris_joke WHERE id=:id LIMIT 1")
    fun getJokeForID(id: String): ChuckNorrisJoke
}

