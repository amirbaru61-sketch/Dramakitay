# DramaKita - Aplikasi Streaming Drama Pendek Vertikal

Aplikasi Android native yang menawarkan pengalaman streaming drama pendek vertikal dengan desain mewah dan modern, terinspirasi dari DramaBox.

## 📱 Fitur Utama

1. **Halaman Beranda** - Tab navigasi dengan "Untuk Anda", "Trending", dan "Kategori"
2. **Pemutar Video Vertikal** - Pemutaran fullscreen dengan swipe navigation
3. **Interaksi Sosial** - Tombol Like, Bagikan, Daftar Episode, dan Sinopsis
4. **Pencarian Drama** - Cari judul drama dengan mudah
5. **Daftar Favorit Lokal** - Simpan drama favorit di perangkat

## 🛠️ Stack Teknologi

- **Bahasa**: Kotlin
- **UI Framework**: Jetpack Compose
- **Arsitektur**: Clean Architecture (MVVM)
- **Video Playback**: ExoPlayer
- **Local Storage**: Room Database
- **Async**: Coroutines

## 📁 Struktur Proyek

```
app/src/main/
├── java/com/dramakita/
│   ├── ui/
│   │   ├── screens/          # Layar/halaman aplikasi
│   │   ├── components/       # Komponen UI yang dapat digunakan kembali
│   │   └── theme/            # Tema dan styling
│   ├── model/                # Data class dan model
│   ├── data/                 # Data source dan repository
│   ├── viewmodel/            # ViewModel untuk setiap screen
│   └── MainActivity.kt       # Entry point aplikasi
└── res/
    ├── drawable/             # Asset gambar
    └── values/               # String dan color resources
```

## 🚀 Cara Menjalankan

1. Buka proyek di Android Studio
2. Sinkronisasi Gradle
3. Jalankan aplikasi di emulator atau device fisik

## ⚠️ Catatan

- Menggunakan dummy data lokal (tanpa backend)
- Video placeholder menggunakan sumber legal yang dapat diakses publik
- Tanpa sistem login, iklan, atau pembayaran di fase awal

---

**Dibuat untuk pembelajaran Android Development dengan Kotlin & Jetpack Compose**
