package cafe.adriel.moov.contract

import cafe.adriel.moov.model.entity.Movie

sealed class MovieContract {

    interface IMovieView {
        fun showMovie(movie: Movie)
    }

    interface IMovieListView {
        fun showMovies(movies: List<Movie>)
        fun showMovie(movie: Movie)
        fun showMovieDetails()
    }

    interface IMovieListPresenter {
        fun loadMovies(page: Int)
        fun searchMovies(query: String, page: Int)
    }

    interface IMovieRepository {
        fun getMovies(page: Int): List<Movie>
        fun getSearchMovies(query: String, page: Int): List<Movie>
    }

}