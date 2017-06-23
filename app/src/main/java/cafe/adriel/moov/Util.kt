package cafe.adriel.moov

object Util {

    fun getPosterImageUrl(posterPath : String) = Constant.TMDB_POSTER_BASE_URL + posterPath

    fun getBackdropImageUrl(backdropPath : String) = Constant.TMDB_BACKDROP_BASE_URL + backdropPath

    fun getGenres(idGenres: List<Int>): String {
        val genres = mutableListOf<String>()
        idGenres.forEach {
            val nameGenre = Constant.TMDB_GENRES[it]
            nameGenre?.let {
                genres.add(nameGenre)
            }
        }
        return genres.joinToString(" | ")
    }

}