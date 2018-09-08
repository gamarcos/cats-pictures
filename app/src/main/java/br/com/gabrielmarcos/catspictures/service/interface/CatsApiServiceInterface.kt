package br.com.gabrielmarcos.catspictures.service.`interface`

import br.com.gabrielmarcos.catspictures.BuildConfig
import br.com.gabrielmarcos.catspictures.model.PicturesModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
interface CatsApiServiceInterface {
    @GET(BuildConfig.URL_SEARCH_IMAGE)
    fun getPictures(@Query("category_ids") category:String?,
                    @Query("limit") limit: String): Observable<Response<ArrayList<PicturesModel>>>
}