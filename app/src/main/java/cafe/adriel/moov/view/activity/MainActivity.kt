package cafe.adriel.moov.view.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cafe.adriel.moov.R
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import cafe.adriel.moov.presenter.MovieListPresenter
import cafe.adriel.moov.toFormattedString
import cafe.adriel.moov.view.adapter.MovieAdapterItem
import com.bumptech.glide.Glide
import com.joanzapata.iconify.fonts.MaterialIcons
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MovieContract.IMovieListView {
    lateinit var presenter : MovieContract.IMovieListPresenter
    lateinit var adapter : FastItemAdapter<MovieAdapterItem>
    var currentMovie : Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MovieListPresenter(this)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        vMore.setOnClickListener {
            showMovieDetails()
        }

        adapter = FastItemAdapter()
        with(adapter) {
            withSavedInstanceState(savedInstanceState)
            withSelectable(false)
            withOnClickListener { v, adapter, item, position ->
                showMovie(item.movie)
                true
            }
        }

        with(vMovies){
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.loadMovies(0)
    }

    override fun showMovies(movies: List<Movie>) {
        movies.toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .forEach { movie ->
                    adapter.add(MovieAdapterItem(movie))
                    if(adapter.adapterItemCount == 1){
                        showMovie(movie)
                    }
                }
                .addTo(disposables)
    }

    override fun showMovie(movie: Movie) {
        currentMovie = movie
        currentMovie?.run {
            vName.text = name
            vGenre.text = genre
            vReleaseDate.text = "{${MaterialIcons.md_access_time.key()}} ${releaseDate.toFormattedString()}"
            vMore.visibility = View.VISIBLE
            Glide.with(this@MainActivity)
                    .load(posterImageUrl)
                    .into(vPoster)
        }
    }

    override fun showMovieDetails() {
        currentMovie?.run {

        }
    }

}