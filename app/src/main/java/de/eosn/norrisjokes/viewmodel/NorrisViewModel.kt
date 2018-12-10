package de.eosn.norrisjokes.viewmodel

import androidx.lifecycle.ViewModel
import de.eosn.norrisjokes.repo.NorrisRepository

class NorrisViewModel(private val chuckNorrisRepository: NorrisRepository) : ViewModel() {

    fun getJoke() = chuckNorrisRepository.retrieveJoke()
}