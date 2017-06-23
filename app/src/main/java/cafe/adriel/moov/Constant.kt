package cafe.adriel.moov

import android.graphics.drawable.ColorDrawable
import android.support.v4.content.res.ResourcesCompat

object Constant {

    val imagePlaceholder = ColorDrawable(ResourcesCompat.getColor(
            App.context.resources, R.color.colorAccent, null))

    const val EXTRA_MOVIE = "movie"

    const val TMDB_KEY = "1f54bd990f1cdfb230adb312546d765d"
    const val TMDB_API_BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/"
    const val TMDB_BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w300"
    const val TMDB_LANGUAGE = "en-US"
    const val TMDB_REGION = "US"
    val TMDB_GENRES = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western")

}