package cafe.adriel.moov.view.activity

import android.os.Bundle
import cafe.adriel.moov.R
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import cafe.adriel.moov.presenter.MovieListPresenter
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity: BaseActivity(), MovieContract.IMovieListView {

    lateinit var presenter : MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        presenter = MovieListPresenter(this)

        vHello.text = "Hello World!"
    }

    override fun showMovies(movies: List<Movie>) {

    }

    override fun openMovie(movie: Movie) {

    }

}