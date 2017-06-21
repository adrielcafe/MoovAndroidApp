package cafe.adriel.moov.presenter

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import java.util.*

class MovieListPresenter(val view : MovieContract.IMovieListView): MovieContract.IMovieListPresenter {

    override fun loadMovies(page: Int) {
        view.showMovies(listOf(
                Movie("Miss Peregrine's Home for Peculiar Children", "https://image.tmdb.org/t/p/original/AvekzUdI8HZnImdQulmTTmAZXrC.jpg", "https://image.tmdb.org/t/p/original/cMcPIr5PV5bXIt7FrG6qcPfaKj2.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Split", "https://image.tmdb.org/t/p/original/rXMWOZiCt6eMX22jWuTOSdQ98bY.jpg", "https://image.tmdb.org/t/p/original/3na3Y4PM4uB4vKrrzp5nhzxaOn9.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Deadpool", "https://image.tmdb.org/t/p/original/inVq3FRqcYIRl2la8iZikYYxFNR.jpg", "https://image.tmdb.org/t/p/original/laLIVlJpqNirs95R3eHrSh0oz5X.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Captain America: Civil War", "https://image.tmdb.org/t/p/original/kSBXou5Ac7vEqKd97wotJumyJvU.jpg", "https://image.tmdb.org/t/p/original/kliBz01JG89tsj1om0Z6T1ZOCV.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Fantastic Beasts and Where to Find Them", "https://image.tmdb.org/t/p/original/gri0DDxsERr6B2sOR1fGLxLpSLx.jpg", "https://image.tmdb.org/t/p/original/bzfiQ0sYsLo6kzeP1DntXxZ4quD.jpg", "Lorem ipsum", "Action", Date())))
    }

    override fun searchMovies(query: String, page: Int) {
        // TODO
    }

}