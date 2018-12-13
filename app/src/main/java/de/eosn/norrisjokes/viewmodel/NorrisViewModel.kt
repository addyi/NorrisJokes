package de.eosn.norrisjokes.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import arrow.core.Either
import de.eosn.norrisjokes.repo.ChuckNorrisJoke
import de.eosn.norrisjokes.repo.NorrisRepository

class NorrisViewModel(private val chuckNorrisRepository: NorrisRepository) : ViewModel() {

    private val tag = NorrisViewModel::class.java.simpleName

    val chuckNorrisJokes = chuckNorrisRepository.getJokes()

    suspend fun getRandomJoke(): Either<ChuckNorrisJoke, Exception> {
        val eitherJokeOrException = chuckNorrisRepository.retrieveRandomJoke()

        Log.i(tag, eitherJokeOrException.toString())

        return eitherJokeOrException
    }
}