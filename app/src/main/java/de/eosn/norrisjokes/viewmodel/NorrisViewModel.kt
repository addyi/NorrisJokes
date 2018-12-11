package de.eosn.norrisjokes.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import arrow.core.Either
import de.eosn.norrisjokes.repo.NorrisRepository

class NorrisViewModel(private val chuckNorrisRepository: NorrisRepository) : ViewModel() {

    private val tag = NorrisViewModel::class.java.simpleName

    suspend fun getJoke() {
        val foo = chuckNorrisRepository.retrieveJoke()
        when (foo) {
            is Either.Left -> Log.d(tag, foo.a)
            is Either.Right -> TODO()
        }

    }
}