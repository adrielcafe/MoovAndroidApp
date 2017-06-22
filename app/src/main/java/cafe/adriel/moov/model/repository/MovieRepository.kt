package cafe.adriel.moov.model.repository

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import java.util.*

object MovieRepository: MovieContract.IMovieRepository {

    override fun getMovies(page: Int): List<Movie> {
        // TODO
        return listOf(
                Movie("Miss Peregrine's Home for Peculiar Children", "https://image.tmdb.org/t/p/original/AvekzUdI8HZnImdQulmTTmAZXrC.jpg", "https://image.tmdb.org/t/p/original/cMcPIr5PV5bXIt7FrG6qcPfaKj2.jpg", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi.", "Action", Date()),
                Movie("Split", "https://image.tmdb.org/t/p/original/rXMWOZiCt6eMX22jWuTOSdQ98bY.jpg", "https://image.tmdb.org/t/p/original/3na3Y4PM4uB4vKrrzp5nhzxaOn9.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Deadpool", "https://image.tmdb.org/t/p/original/inVq3FRqcYIRl2la8iZikYYxFNR.jpg", "https://image.tmdb.org/t/p/original/laLIVlJpqNirs95R3eHrSh0oz5X.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Captain America: Civil War", "https://image.tmdb.org/t/p/original/kSBXou5Ac7vEqKd97wotJumyJvU.jpg", "https://image.tmdb.org/t/p/original/kliBz01JG89tsj1om0Z6T1ZOCV.jpg", "Lorem ipsum", "Action", Date()),
                Movie("Fantastic Beasts and Where to Find Them", "https://image.tmdb.org/t/p/original/gri0DDxsERr6B2sOR1fGLxLpSLx.jpg", "https://image.tmdb.org/t/p/original/bzfiQ0sYsLo6kzeP1DntXxZ4quD.jpg", "Lorem ipsum", "Action", Date()))
    }

    override fun getSearchMovies(query: String, page: Int): List<Movie> {
        // TODO
        return listOf()
    }

}