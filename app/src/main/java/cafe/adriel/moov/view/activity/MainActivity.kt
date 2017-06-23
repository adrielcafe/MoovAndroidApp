package cafe.adriel.moov.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import cafe.adriel.moov.*
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import cafe.adriel.moov.presenter.MovieListPresenter
import cafe.adriel.moov.view.adapter.BackdropMovieAdapterItem
import com.bumptech.glide.Glide
import com.joanzapata.iconify.fonts.MaterialIcons
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import com.tinsuke.icekick.extension.parcelState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity(), MovieContract.IMovieListView {
    private lateinit var presenter: MovieContract.IMovieListPresenter
    private lateinit var adapter: FastItemAdapter<BackdropMovieAdapterItem>
    private lateinit var loadingAdapter: FooterAdapter<ProgressItem>

    private var currentMovie: Movie? by parcelState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MovieListPresenter(this)

        setSupportActionBar(vToolbar)
        supportActionBar?.title = null
        vAppbar.setBackgroundColor(Color.TRANSPARENT)
        vAppbar.bringToFront()

        vMore.setOnClickListener {
            showMovieDetails()
        }

        adapter = FastItemAdapter()
        loadingAdapter = FooterAdapter<ProgressItem>()
        with(adapter) {
            withSelectable(false)
            withOnClickListener { v, adapter, item, position ->
                showMovie(item.movie)
                true
            }
        }

        with(vMovies){
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = loadingAdapter.wrap(this@MainActivity.adapter)
            setHasFixedSize(true)
            addOnScrollListener(object: EndlessRecyclerOnScrollListener(loadingAdapter){
                override fun onLoadMore(currentPage: Int) {
                    loadingAdapter.clear()
                    loadingAdapter.add(ProgressItem().withEnabled(false))
                    presenter.loadMovies(currentPage)
                }
            })
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.loadMovies(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val iSearch = menu.findItem(R.id.action_search)
        iSearch.setFontIcon(MaterialIcons.md_search)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_search -> {
                searchMovies()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun searchMovies() {
        startActivity(Intent(this@MainActivity, SearchActivity::class.java))
    }

    override fun showMovies(movies: List<Movie>) {
        movies.toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .forEach { movie ->
                    adapter.add(BackdropMovieAdapterItem(movie))
                    if(adapter.adapterItemCount == 1){
                        showMovie(movie)
                    }
                }
                .addTo(disposables)
        loadingAdapter.clear()
    }

    override fun showMovie(movie: Movie) {
        currentMovie = movie
        currentMovie?.run {
            vName.text = name
            vMore.visibility = View.VISIBLE
            genres?.let {
                if (it.isNotEmpty()) {
                    vGenre.text = "{md-local-offer} ${Util.getGenres(genres)}"
                }
            }
            releaseDate?.toDate()?.let {
                vReleaseDate.text = "{md-access-time} ${it.toFormattedString()}"
            }
            posterImagePath?.let {
                Glide.with(this@MainActivity)
                        .load(Util.getPosterImageUrl(it))
                        .into(vPoster)
            }
        }
    }

    override fun showMovieDetails() {
        currentMovie?.let {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, vPoster, "poster")
            val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
            intent.putExtra(Constant.EXTRA_MOVIE, it)
            startActivity(intent, options.toBundle())
        }
    }

    override fun showError(error: String) {
        showToast(error)
    }

}