package br.com.gabrielmarcos.catspictures.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
@Parcelize
class PicturesModel(val id: String,
                    val url: String,
                    val breeds: ArrayList<CatsBreedModel>): Parcelable
