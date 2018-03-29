package cafe.adriel.moov.model.entity

import com.google.gson.annotations.SerializedName
import io.mironov.smuggler.AutoParcelable

data class MovieResponse(
        @SerializedName("results")
        val results: List<Movie>) : AutoParcelable