package br.com.gabrielmarcos.catspictures.service

import android.content.Context
import br.com.gabrielmarcos.catspictures.model.PicturesModel
import br.com.gabrielmarcos.catspictures.util.RetrofitHelper
import br.com.gabrielmarcos.catspictures.service.`interface`.CatsApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class PicturesService(context: Context): BaseService(context) {
    private val picturesService by lazy {
        RetrofitHelper.getRetrofit(context).create(CatsApiServiceInterface::class.java)
    }

    fun subscribe(category: String?,
                  limit: String,
                  successCallback: (response: ArrayList<PicturesModel>) -> Unit,
                  errorCallback: () -> Unit){

        observable = picturesService.getPictures(category, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Response<ArrayList<PicturesModel>>>() {
                    override fun onNext(response: Response<ArrayList<PicturesModel>>) {
                        if (response.raw().cacheResponse() != null) {
                            successCallback(response.body()!!)
                        }

                        if (response.raw().networkResponse() != null) {
                            successCallback(response.body()!!)
                        }
                    }
                    override fun onComplete() {}
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }
}