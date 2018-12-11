package de.eosn.norrisjokes.repo

import arrow.core.Either

interface NorrisRepository {
    suspend fun retrieveJoke(): Either<String?, Exception>
}