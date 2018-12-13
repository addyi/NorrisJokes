package de.eosn.norrisjokes.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import arrow.core.Either
import de.eosn.norrisjokes.repo.NetworkRequestException
import de.eosn.norrisjokes.repo.NorrisRepository
import de.eosn.norrisjokes.repo.ParsingException

class NorrisViewModel(private val chuckNorrisRepository: NorrisRepository) : ViewModel() {

    private val tag = NorrisViewModel::class.java.simpleName

    val chuckNorrisJokes = chuckNorrisRepository.getJokes()

    suspend fun getJoke() {
        val foo = chuckNorrisRepository.retrieveJoke()
        when (foo) {
            is Either.Left -> Log.d(tag, foo.a)
            is Either.Right -> Log.d(
                tag, when (foo.b) {
                    is ParsingException -> foo.b.message
                    is NetworkRequestException -> foo.b.message
                    else -> {
                        foo.b.message
                    }
                }
            )
        }

    }
}