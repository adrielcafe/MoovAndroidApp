package cafe.adriel.moov.presenter

import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MovieListPresenter(val view : MovieContract.IMovieListView): MovieContract.IMovieListPresenter {

    private val disposables = CompositeDisposable()

    override fun loadMovies(page: Int) {
        MovieRepository.getUpcomingMovies(page)
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