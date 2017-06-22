package cafe.adriel.moov.presenter

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.repository.MovieRepository

class MovieListPresenter(val view : MovieContract.IMovieListView): MovieContract.IMovieListPresenter {

    override fun loadMovies(page: Int) {
        view.showMovies(MovieRepository.getMovies(page))
    }

    override fun searchMovies(query: String, page: Int) {
        // TODO
    }

}