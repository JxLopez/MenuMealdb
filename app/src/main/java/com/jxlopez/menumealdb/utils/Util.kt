package com.jxlopez.menumealdb.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object Util {
    fun getVideoIdForUrl(youtubeUrl: String?): String? {
        val expression =
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
        if (youtubeUrl == null || youtubeUrl.isEmpty()) return null

        val compiledPattern = Pattern.compile(expression)
        val matcher = compiledPattern.matcher(youtubeUrl)
        try {
            if(matcher.find())
                return matcher.group()
        } catch (ex: ArrayIndexOutOfBoundsException) {
            ex.printStackTrace()
        }
        return null
    }
}