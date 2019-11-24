package com.go.it.planetapp.utils

import android.net.Uri

class UriUtils {
    companion object{
        fun getQueryFromUri(url:String?,query:String): String {
            if (!url.isNullOrEmpty()){
                val uri: Uri =
                    Uri.parse(url)
                return uri.getQueryParameter(query)!!
            }
            return ""

        }
    }
}