package cafe.adriel.moov.contract

import android.view.View
import cafe.adriel.moov.model.entity.Movie
import cafe.adriel.moov.model.entity.MovieResponse
import io.reactivex.Flowable

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
        fun showMovieDetails(movie: Movie, sharedView: View)
    }

    interface IMovieListPresenter: BaseContract.IPresenterView {
        fun loadMovies(page: Int)
    }

    interface IMovieSearchPresenter: BaseContract.IPresenterView {
        fun searchMovies(query: String, page: Int)
    }

    interface IMovieRepository {
        fun getUpcomingMovies(page: Int): Flowable<MovieResponse>
        fun getSearchMovies(query: String, page: Int): Flowable<MovieResponse>
    }

}