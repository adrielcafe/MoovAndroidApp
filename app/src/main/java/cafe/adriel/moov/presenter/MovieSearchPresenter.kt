package cafe.adriel.moov.presenter

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.repository.MovieRepository

class MovieSearchPresenter(val view : MovieContract.IMovieSearchView): MovieContract.IMovieSearchPresenter {

    override fun searchMovies(query: String, page: Int) {
        view.showMovies(MovieRepository.getSearchMovies(query, page))
    }

}