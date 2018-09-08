package br.com.gabrielmarcos.catspictures.util

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class PicassoServiceHelper(val context: Context?) {

    fun loadImage(url: String?, view: ImageView) {
        Picasso.with(context)
                .load(url)
                .fit()
                .centerCrop()
                .into(view)
    }
}