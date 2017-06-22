package cafe.adriel.moov.contract

sealed class BaseContract {

    interface IBaseView {
        fun showError(error: String)
    }

}