package com.dramakita.data

import com.dramakita.model.Drama
import com.dramakita.model.Episode

object DramaRepository {
    fun getAllDramas(): List<Drama> = listOf(
        Drama(
            id = 1,
            title = "Cinta di Ujung Senja",
            category = "Romantis",
            description = "Seorang perempuan muda yang kembali ke kota kelahirannya menemukan perselingkuhan dan cinta yang tak terduga.",
            rating = 4.8f,
            duration = "24 min",
            posterUrl = "https://images.unsplash.com/photo-1517841905240-472988babdf9?auto=format&fit=crop&w=900&q=80",
            isTrending = true,
            accentColor = "#FF4FA3",
            episodes = listOf(
                Episode(1, "Episode 1", "24:10", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(2, "Episode 2", "24:11", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(3, "Episode 3", "24:17", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4")
            )
        ),
        Drama(
            id = 2,
            title = "Malam Penuh Rahasia",
            category = "Thriller",
            description = "Kasus kecil berubah menjadi misteri besar ketika seorang reporter mulai mengungkap kebenaran yang disimpan kota.",
            rating = 4.7f,
            duration = "18 min",
            posterUrl = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?auto=format&fit=crop&w=900&q=80",
            isTrending = true,
            accentColor = "#D5A6FF",
            episodes = listOf(
                Episode(1, "Episode 1", "18:05", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(2, "Episode 2", "18:22", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(3, "Episode 3", "18:14", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4")
            )
        ),
        Drama(
            id = 3,
            title = "Jalan ke Langit",
            category = "Drama",
            description = "Dua saudara dengan mimpi berbeda harus menghentikan konflik keluarga agar bisa mengejar masa depan mereka.",
            rating = 4.6f,
            duration = "20 min",
            posterUrl = "https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?auto=format&fit=crop&w=900&q=80",
            isTrending = false,
            accentColor = "#56CCF2",
            episodes = listOf(
                Episode(1, "Episode 1", "20:11", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(2, "Episode 2", "20:30", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(3, "Episode 3", "20:07", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4")
            )
        ),
        Drama(
            id = 4,
            title = "Bunga Taman Kota",
            category = "Komedi",
            description = "Serangkaian momen lucu dan hangat di tengah kehidupan masyarakat perkotaan yang sibuk.",
            rating = 4.5f,
            duration = "15 min",
            posterUrl = "https://images.unsplash.com/photo-1521119989659-a83eee488004?auto=format&fit=crop&w=900&q=80",
            isTrending = false,
            accentColor = "#F2C94C",
            episodes = listOf(
                Episode(1, "Episode 1", "15:10", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(2, "Episode 2", "15:18", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4")
            )
        ),
        Drama(
            id = 5,
            title = "Kota Tak Pernah Tidur",
            category = "Action",
            description = "Seorang pengemudi ojek online terjebak dalam konspirasi besar yang melibatkan kota dan masa depan.",
            rating = 4.9f,
            duration = "22 min",
            posterUrl = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=900&q=80",
            isTrending = true,
            accentColor = "#FF6B6B",
            episodes = listOf(
                Episode(1, "Episode 1", "22:12", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(2, "Episode 2", "22:08", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4"),
                Episode(3, "Episode 3", "22:15", "https://interactive-examples.mdn.mozilla.net/media/cc0-videos/flower.mp4")
            )
        )
    )
}
