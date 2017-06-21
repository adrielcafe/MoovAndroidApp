package cafe.adriel.moov.presenter

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie

class MovieListPresenter(val view : MovieContract.IMovieListView): MovieContract.IMovieListPresenter {

    override fun loadMovies(page: Int): List<Movie> {
        // TODO
        return listOf()
    }

    override fun searchMovies(query: String, page: Int): List<Movie> {
        // TODO
        return listOf()
    }

}