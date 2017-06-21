package cafe.adriel.moov.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import cafe.adriel.moov.App
import cafe.adriel.moov.R
import cafe.adriel.moov.model.entity.Movie
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieAdapterItem(val movie : Movie): AbstractItem<MovieAdapterItem, MovieAdapterItem.ViewHolder>() {

    override fun getType() = layoutRes

    override fun getLayoutRes() = R.layout.list_item_movie

    override fun getViewHolder(v: View?) = ViewHolder(v!!)

    override fun bindView(holder: ViewHolder?, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)
        with(holder?.itemView!!){
            Glide.with(App.context)
                    .load(movie.backdropImageUrl)
                    .into(vBackdrop)
        }
    }

    override fun unbindView(holder: ViewHolder?) {
        super.unbindView(holder)

    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v)

}