package cafe.adriel.moov.presenter

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MovieSearchPresenter(val view : MovieContract.IMovieSearchView): MovieContract.IMovieSearchPresenter {

    private val disposables = CompositeDisposable()

    override fun searchMovies(query: String, page: Int) {
        MovieRepository.getSearchMovies(query, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.showMovies(it.results)
                }
                .addTo(disposables)
    }

    override fun onDestroy() {
        disposables.clear()
    }

}