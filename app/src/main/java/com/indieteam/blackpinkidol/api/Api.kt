package com.indieteam.blackpinkidol.api

class Api{
    val apiProfile = "http://80.211.52.162:3001/v1/profile?name=blackpink"
    val apiSong = "http://80.211.52.162:3001/v1/song?name=blackpink"
    val apiAlbum = "http://80.211.52.162:3001/v1/album?name=blackpink"
    val apiMv = "http://80.211.52.162:3001/v1/mv?name=blackpink"

    fun youtubeThumbnails(idVideo: String): String{
        return "https://img.youtube.com/vi/$idVideo/0.jpg"
    }

    fun youtubeTitle(idVideo: String): String{
        return "https://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=$idVideo&format=json"
    }
}