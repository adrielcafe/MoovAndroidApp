package cafe.adriel.moov.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity(), MovieContract.IMovieListView {
    lateinit var presenter: MovieContract.IMovieListPresenter
    lateinit var adapter: FastItemAdapter<BackdropMovieAdapterItem>
    lateinit var footerAdapter: FooterAdapter<ProgressItem>
    var currentMovie: Movie? = null

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
        footerAdapter = FooterAdapter<ProgressItem>()
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
            adapter = footerAdapter.wrap(this@MainActivity.adapter)
            setHasFixedSize(true)
            addOnScrollListener(object: EndlessRecyclerOnScrollListener(footerAdapter){
                override fun onLoadMore(currentPage: Int) {
                    footerAdapter.clear()
                    footerAdapter.add(ProgressItem().withEnabled(false))
                    presenter.loadMovies(currentPage)
                }
            })
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.loadMovies(1)
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
        currentMovie?.let {
            val i = Intent(this@MainActivity, MovieDetailActivity::class.java)
            i.putExtra(Constant.EXTRA_MOVIE, it)
            startActivity(i)
        }
    }

    override fun showError(error: String) {
        showToast(error)
    }

}