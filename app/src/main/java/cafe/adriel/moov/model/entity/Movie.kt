package cafe.adriel.moov.model.entity

import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

data class Movie(
        @SerializedName("title")
        val name: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("poster_path")
        val posterImagePath: String?,
        @SerializedName("backdrop_path")
        val backdropImagePath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        @SerializedName("genre_ids")
        val genres: List<Int>?) : AutoParcelable