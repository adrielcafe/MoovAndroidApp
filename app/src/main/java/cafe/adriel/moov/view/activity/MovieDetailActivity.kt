package cafe.adriel.moov.view.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import cafe.adriel.moov.*
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import com.bumptech.glide.Glide
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovie() {
        movie?.run {
            vName.text = name
            vOverview.text = overview
            releaseDate?.let {
                vReleaseDate.text = "{md-access-time} ${it.toFormattedString()}"
            }
            genres?.let {
                if (it.isNotEmpty()) {
                    vGenre.text = "{md-local-offer} ${Util.getGenres(genres)}"
                }
            }
            posterImagePath?.let {
                Glide.with(this@MovieDetailActivity)
                        .load(Util.getPosterImageUrl(it))
                        .dontAnimate()
                        .into(vPoster)
            }
            backdropImagePath?.let {
                Glide.with(this@MovieDetailActivity)
                        .load(Util.getBackdropImageUrl(it))
                        .into(vBackdrop)
            }
        }
    }

    override fun showError(error: String) {
        showToast(error)
    }

}