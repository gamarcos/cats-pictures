package br.com.gabrielmarcos.catspictures.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabrielmarcos.catspictures.R
import br.com.gabrielmarcos.catspictures.model.PicturesModel
import br.com.gabrielmarcos.catspictures.util.PicassoServiceHelper
import kotlinx.android.synthetic.main.adapter_cards.view.*
import java.util.ArrayList

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class CatsListAdapter(private val context: Context,
                      private val listener: CatsListAdapterListener,
                      private val photos: ArrayList<PicturesModel>): RecyclerView.Adapter<CatsListAdapter.ViewHolder>() {

    interface CatsListAdapterListener {
        fun onCardClicked(picture: PicturesModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_cards, parent, false)
        return ViewHolder(view, listener, context)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(photos[position])
    }

    class ViewHolder(itemView: View, val listener: CatsListAdapterListener, val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val picassoService = PicassoServiceHelper(context)

        fun bindView(photo: PicturesModel){
            picassoService.loadImage(photo.url, itemView.pictureImage)
            itemView.pictureContainer.setOnClickListener {
                listener.onCardClicked(photo)
            }
        }
    }
}