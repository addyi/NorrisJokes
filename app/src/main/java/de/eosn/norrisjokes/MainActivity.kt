package de.eosn.norrisjokes

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import de.eosn.norrisjokes.viewmodel.NorrisViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private val norrisViewModel: NorrisViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            norrisViewModel.getJoke()
        }

        norrisViewModel.chuckNorrisJokes.observe(this, Observer {
            for (chuckNorrisJoke in it) {
                Log.d(tag, chuckNorrisJoke.toString())
            }
        })
    }
}
