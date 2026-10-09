package com.dramakita.model

import androidx.compose.ui.graphics.Color

data class Episode(
    val id: Int,
    val title: String,
    val duration: String,
    val videoUrl: String
)

data class Drama(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val rating: Float,
    val duration: String,
    val posterUrl: String,
    val isTrending: Boolean,
    val accentColor: String,
    val episodes: List<Episode>
) {
    val episodeCount: Int
        get() = episodes.size
}
