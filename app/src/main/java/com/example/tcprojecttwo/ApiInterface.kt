package com.example.tcprojecttwo

import com.example.tcprojecttwo.DataClasses.GalleryShowItem
import com.example.tcprojecttwo.DataClasses.PlayersItem
import com.example.tcprojecttwo.DataClasses.PlayersDataItem
import com.example.tcprojecttwo.DataClasses.PuneriTv
import com.example.tcprojecttwo.DataClasses.PuneriTvVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v3/puneri_paltan/player_list")
    fun getPlayers(@Query("cat_id") cat_id: String?): Call<List<PlayersItem>>

    @GET("v3/puneri_paltan/single_player")
    fun getSinglePlayerData(@Query("id") id: String?): Call<List<PlayersDataItem>>

    @GET("v3/puneri_paltan/single_puneri_tv")
    fun getPuneriTvData(@Query("id") id: String?): Call<PuneriTvVideo>

    @GET("v3/puneri_paltan/gallary_list")
    fun getGalleryData(@Query("cat_id") cat_id: String?): Call<List<GalleryShowItem>>
}