package cafe.adriel.moov.view.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import cafe.adriel.moov.Constant
import cafe.adriel.moov.R
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import cafe.adriel.moov.showToast
import cafe.adriel.moov.toFormattedString
import com.bumptech.glide.Glide
import com.joanzapata.iconify.fonts.MaterialIcons
import com.tinsuke.icekick.extension.parcelLateState
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity: BaseActivity(), MovieContract.IMovieView {

    private var movie: Movie by parcelLateState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movie = intent.getParcelableExtra(Constant.EXTRA_MOVIE)

        supportActionBar?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setDisplayHomeAsUpEnabled(true)
            title = null
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        showMovie()
    }

    override fun showMovie() {
        movie?.run {
            vName.text = name
            vOverview.text = overview
            vGenre.text = "{${MaterialIcons.md_local_offer.key()}} ${Constant.TMDB_GENRES[genre!![0]]}"
            vReleaseDate.text = "{${MaterialIcons.md_access_time.key()}} ${releaseDate?.toFormattedString()}"
            Glide.with(this@MovieDetailActivity)
                    .load(posterImageUrl)
                    .into(vPoster)
            Glide.with(this@MovieDetailActivity)
                    .load(backdropImageUrl)
                    .into(vBackdrop)
        }
    }

    override fun showError(error: String) {
        showToast(error)
    }

}