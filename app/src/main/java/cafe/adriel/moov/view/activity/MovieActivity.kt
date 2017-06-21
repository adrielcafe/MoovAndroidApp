package cafe.adriel.moov.view.activity

import android.os.Bundle
import cafe.adriel.moov.Constant
import cafe.adriel.moov.R
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import com.tinsuke.icekick.extension.parcelLateState
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity: BaseActivity(), MovieContract.IMovieView {

    private var movie: Movie by parcelLateState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movie = intent.getParcelableExtra(Constant.EXTRA_MOVIE)

        vHello.text = "Hello World!"
    }

    override fun showMovie(movie: Movie) {

    }

}