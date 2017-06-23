package cafe.adriel.moov.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import cafe.adriel.moov.Constant
import cafe.adriel.moov.R
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.Movie
import cafe.adriel.moov.presenter.MovieSearchPresenter
import cafe.adriel.moov.showToast
import cafe.adriel.moov.view.adapter.SearchMovieAdapterItem
import com.mikepenz.fastadapter.adapters.FooterAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter_extensions.items.ProgressItem
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity: BaseActivity(), MovieContract.IMovieSearchView {
    lateinit var presenter: MovieContract.IMovieSearchPresenter
    lateinit var adapter: FastItemAdapter<SearchMovieAdapterItem>
    lateinit var loadingAdapter: FooterAdapter<ProgressItem>
    var currentQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter = MovieSearchPresenter(this)

        setSupportActionBar(vToolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = null
        }

        adapter = FastItemAdapter()
        loadingAdapter = FooterAdapter<ProgressItem>()
        with(adapter) {
            withSelectable(false)
            withOnClickListener { v, adapter, item, position ->
                showMovieDetails(item.movie, v.findViewById(R.id.vPoster))
                true
            }
        }

        with(vMovies){
            layoutManager = GridLayoutManager(this@SearchActivity, 3)
            itemAnimator = DefaultItemAnimator()
            adapter = loadingAdapter.wrap(this@SearchActivity.adapter)
            setHasFixedSize(true)
            addOnScrollListener(object: EndlessRecyclerOnScrollListener(loadingAdapter){
                override fun onLoadMore(currentPage: Int) {
                    loadingAdapter.clear()
                    loadingAdapter.add(ProgressItem().withEnabled(false))
                    currentQuery?.let {
                        presenter.searchMovies(it, currentPage)
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val iSearch = menu.findItem(R.id.action_search)
        val vSearch = iSearch.actionView as SearchView

        vSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                vSearch.clearFocus()
                currentQuery = query
                currentQuery?.let {
                    adapter.clear()
                    presenter.searchMovies(it, 1)
                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun showMovies(movies: List<Movie>) {
        movies.toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .forEach { movie ->
                    adapter.add(SearchMovieAdapterItem(movie))
                }
                .addTo(disposables)
        loadingAdapter.clear()
    }

    override fun showMovieDetails(movie: Movie, sharedView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, sharedView, "poster")
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constant.EXTRA_MOVIE, movie)
        startActivity(intent, options.toBundle())
    }

    override fun showError(error: String) {
        showToast(error)
    }

}