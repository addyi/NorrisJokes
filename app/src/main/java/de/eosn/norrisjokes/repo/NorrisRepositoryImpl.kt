package de.eosn.norrisjokes.repo

import androidx.lifecycle.LiveData
import arrow.core.Either
import de.eosn.norrisjokes.model.ChuckNorrisJoke
import de.eosn.norrisjokes.repo.local.ChuckNorrisJokeDAO
import de.eosn.norrisjokes.repo.remote.ChuckNorrisJokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NorrisRepositoryImpl(
    private val chuckNorrisJokeService: ChuckNorrisJokeService,
    private val chuckNorrisJokeDAO: ChuckNorrisJokeDAO
) : NorrisRepository {

    private val chuckNorrisJokes: LiveData<List<ChuckNorrisJoke>> = chuckNorrisJokeDAO.getAllJokes()

    override suspend fun retrieveRandomJoke(): Either<ChuckNorrisJoke, Exception> {
        // TODO  if request fails retrieve joke from db and only return joke
        try {
            val response = withContext(Dispatchers.IO) { chuckNorrisJokeService.getRandomJoke().await() }

            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    withContext(Dispatchers.IO) { chuckNorrisJokeDAO.insertJokes(body) }
                    Either.left(body)
                } else {
                    Either.right(EmptyJokeException("Somehow joke is null. Joke: $body"))
                }
            } else {
                Either.right(NetworkException("Something went wrong. Response Code: ${response.code()}"))
            }
        } catch (e: Exception) {
            return Either.right(e)
        }
    }

    override fun getJokes(): LiveData<List<ChuckNorrisJoke>> {
        return chuckNorrisJokes
    }
}

class NetworkException(msg: String = "") : Exception(msg)
class EmptyJokeException(msg: String = "") : Exception(msg)
