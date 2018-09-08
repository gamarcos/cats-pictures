package br.com.gabrielmarcos.catspictures.view

import android.app.Activity
import android.os.Bundle
import br.com.gabrielmarcos.catspictures.R
import br.com.gabrielmarcos.catspictures.model.PicturesModel
import br.com.gabrielmarcos.catspictures.util.PicassoServiceHelper
import kotlinx.android.synthetic.main.activity_picture_detail.*

/**
 * Created by Gabriel Marcos on 08/09/2018
 */
class PictureDetailActivity: Activity() {

    companion object {
        var PICTURE_MODEL = "pictureModel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_detail)

        val picturesModel = intent.getParcelableExtra<PicturesModel>(PICTURE_MODEL)

        setupView(picturesModel)

        pictureDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupView(picture: PicturesModel) {
        val picassoService = PicassoServiceHelper(this)

        picassoService.loadImage(picture.url, pictureDetailImage)

        if (picture.breeds.isEmpty()) {
            pictureDetailBreed.text = getString(R.string.picture_detail_not_found)
        } else {
            pictureDetailBreed.text = picture.breeds.first().name
        }
    }
}