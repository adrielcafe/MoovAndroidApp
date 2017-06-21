package cafe.adriel.moov.model.repository

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie

class MovieRepository: MovieContract.IMovieRepository {

    override fun getMovies(page: Int): List<Movie> {
        // TODO
        return listOf()
    }

    override fun getSearchMovies(query: String, page: Int): List<Movie> {
        // TODO
        return listOf()
    }

}