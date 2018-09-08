package br.com.gabrielmarcos.catspictures.view

import android.app.Activity
import android.widget.Toast

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
open class BaseActivity: Activity() {
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}