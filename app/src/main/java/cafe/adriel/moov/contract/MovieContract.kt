package cafe.adriel.moov.contract

import cafe.adriel.moov.model.entity.Movie

sealed class MovieContract {

    interface IMovieView: BaseContract.IBaseView {
        fun showMovie()
    }

    interface IMovieListView: BaseContract.IBaseView {
        fun searchMovies()
        fun showMovies(movies: List<Movie>)
        fun showMovie(movie: Movie)
        fun showMovieDetails()
    }

    interface IMovieSearchView: BaseContract.IBaseView {
        fun showMovies(movies: List<Movie>)
        fun showMovie(movie: Movie)
    }

    interface IMovieListPresenter {
        fun loadMovies(page: Int)
    }

    interface IMovieSearchPresenter {
        fun searchMovies(query: String, page: Int)
    }

    interface IMovieRepository {
        fun getMovies(page: Int): List<Movie>
        fun getSearchMovies(query: String, page: Int): List<Movie>
    }

}