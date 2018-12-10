package de.eosn.norrisjokes.repo

interface NorrisRepository {
    fun retrieveJoke(): String
}