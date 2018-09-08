package br.com.gabrielmarcos.catspictures.view.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.gabrielmarcos.catspictures.R
import br.com.gabrielmarcos.catspictures.model.PicturesModel
import br.com.gabrielmarcos.catspictures.service.PicturesService
import br.com.gabrielmarcos.catspictures.view.PictureDetailActivity
import br.com.gabrielmarcos.catspictures.view.adapters.CatsListAdapter
import kotlinx.android.synthetic.main.fragment_hats.*

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class HatsFragment: Fragment(), CatsListAdapter.CatsListAdapterListener {

    private var picturesService: PicturesService? = null
    private val LIMIT_PHOTOS = "50"
    private val CATEGORY_ID = "1"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hats, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestPictures()
    }

    private fun setupCatsList(hats: ArrayList<PicturesModel>) {
        hatsListImage.adapter = CatsListAdapter(context!!, this, hats)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        hatsListImage.layoutManager = layoutManager
    }

    private fun requestPictures() {
        picturesService = PicturesService(context!!)

        picturesService!!.subscribe(CATEGORY_ID,
                LIMIT_PHOTOS, {response ->
            setupCatsList(response)
        },{})
    }

    override fun onCardClicked(picture: PicturesModel) {
        val intent = Intent(context, PictureDetailActivity::class.java)
        intent.putExtra(PictureDetailActivity.PICTURE_MODEL, picture)
        startActivity(intent)
    }
}