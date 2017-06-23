package cafe.adriel.moov.model.entity

import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable
import java.util.*

data class Movie(
        @SerializedName("title")
        val name: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("poster_path")
        val posterImagePath: String?,
        @SerializedName("backdrop_path")
        val backdropImagePath: String?,
        @SerializedName("genre_ids")
        val genres: List<Int>?,
        @SerializedName("release_date")
        val releaseDate: Date?) : AutoParcelable