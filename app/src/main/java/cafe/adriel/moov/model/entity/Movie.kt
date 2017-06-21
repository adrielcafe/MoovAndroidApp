package cafe.adriel.moov.model.entity

import io.mironov.smuggler.AutoParcelable
import java.util.*

data class Movie(
        val name: String,
        val posterImageUrl: String,
        val backdropImageUrl: String,
        val overview: String,
        val genre: String,
        val releaseDate: Date) : AutoParcelable