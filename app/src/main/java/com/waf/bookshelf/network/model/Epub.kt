package com.waf.bookshelf

import com.google.gson.annotations.SerializedName


data class Epub (

  @SerializedName("isAvailable" ) var isAvailable : Boolean? = null

)