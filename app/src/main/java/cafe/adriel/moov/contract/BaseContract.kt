package cafe.adriel.moov.contract

sealed class BaseContract {

    interface IBaseView {
        fun showError(error: String)
    }

    interface IPresenterView {
        fun onDestroy()
    }

}