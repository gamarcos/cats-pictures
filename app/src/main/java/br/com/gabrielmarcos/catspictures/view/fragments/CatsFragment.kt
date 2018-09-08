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
import kotlinx.android.synthetic.main.fragment_cats.*

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class CatsFragment: Fragment(), CatsListAdapter.CatsListAdapterListener {

    private var picturesService: PicturesService? = null
    private var LIMIT_PHOTOS = "75"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestPictures()
    }

    private fun setupCatsList(cats: ArrayList<PicturesModel>) {
        catsListImage.adapter = CatsListAdapter(context!!, this, cats)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        catsListImage.layoutManager = layoutManager
    }

    private fun requestPictures() {
        picturesService = PicturesService(context!!)

        picturesService!!.subscribe(null,
                LIMIT_PHOTOS, {response ->
            setupCatsList(response)
        },{})
    }

    override fun onCardClicked(picture: PicturesModel) {
        val intent = Intent(context!!, PictureDetailActivity::class.java)
        intent.putExtra(PictureDetailActivity.PICTURE_MODEL, picture)
        startActivity(intent)
    }
}