package de.eosn.norrisjokes

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import arrow.core.Either
import com.google.android.material.snackbar.Snackbar
import de.eosn.norrisjokes.viewmodel.NorrisViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.Main

    private val tag = MainActivity::class.java.simpleName

    private val norrisViewModel: NorrisViewModel by viewModel()

    private lateinit var jokeButton: Button
    private lateinit var jokeTextView: TextView
    private lateinit var layout: CoordinatorLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jokeButton = findViewById(R.id.joke_button)
        jokeTextView = findViewById(R.id.joke_text)
        layout = findViewById(R.id.main_activity)

        jokeButton.setOnClickListener { launch { loadJoke() } }

        launch { loadJoke() }

        norrisViewModel.chuckNorrisJokes.observe(this, Observer {
            for (chuckNorrisJoke in it) {
                Log.d(tag, chuckNorrisJoke.toString())
            }
        })
    }

    private suspend fun loadJoke() {
        val eitherJokeOrException = norrisViewModel.getRandomJoke()

        when (eitherJokeOrException) {
            is Either.Left -> jokeTextView.text = eitherJokeOrException.a.joke
            is Either.Right -> Snackbar.make(
                layout,
                "Something went wrong",
                Snackbar.LENGTH_LONG
            ).show()

        }
    }
}
