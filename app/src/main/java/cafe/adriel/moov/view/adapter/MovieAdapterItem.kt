package cafe.adriel.moov.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem

class MovieAdapterItem: AbstractItem<MovieAdapterItem, MovieAdapterItem.ViewHolder>() {

    override fun getType(): Int {
        // TODO
        return 0
    }

    override fun getLayoutRes(): Int {
        // TODO
        return 0
    }

    override fun getViewHolder(v: View?): ViewHolder {
        // TODO
        return ViewHolder(v!!)
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {

        // TODO

    }

}