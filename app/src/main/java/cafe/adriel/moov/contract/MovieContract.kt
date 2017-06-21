package cafe.adriel.moov.contract

import cafe.adriel.moov.model.entity.Movie

sealed class MovieContract {

    interface IMovieView {
        fun showMovie(movie: Movie)
    }

    interface IMovieListView {
        fun showMovies(movies: List<Movie>)
        fun openMovie(movie: Movie)
    }

    interface IMovieListPresenter {
        fun loadMovies(page: Int): List<Movie>
        fun searchMovies(query: String, page: Int): List<Movie>
    }

    interface IMovieRepository {
        fun getMovies(page: Int): List<Movie>
        fun getSearchMovies(query: String, page: Int): List<Movie>
    }

}