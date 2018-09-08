package br.com.gabrielmarcos.catspictures.service

import android.content.Context
import io.reactivex.disposables.Disposable

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
open class BaseService(val context: Context?) {
    var observable: Disposable? = null

    fun unsubscrible() {
        observable?.dispose()
    }

}