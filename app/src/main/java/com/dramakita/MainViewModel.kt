package com.dramakita.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.dramakita.model.Drama
import com.dramakita.ui.theme.PinkPrimary

@Composable
fun VideoPlayerScreen(
    drama: Drama,
    isOnline: Boolean,
    isFavorite: Boolean,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val player = remember(context) { ExoPlayer.Builder(context).build() }
    val defaultEpisode = drama.episodes.firstOrNull() ?: return
    var selectedEpisode by remember { mutableStateOf(defaultEpisode) }
    var showEpisodes by remember { mutableStateOf(false) }
    var showSynopsis by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(true) }

    LaunchedEffect(selectedEpisode.videoUrl) {
        try {
            val uri = Uri.parse(selectedEpisode.videoUrl)
            val mediaItem = MediaItem.fromUri(uri)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = true
            isPlaying = true
            showError = false
        } catch (_: Exception) {
            showError = true
        }
    }

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                showError = true
                isPlaying = false
            }
        }
        player.addListener(listener)
        onDispose {
            player.removeListener(listener)
            player.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (!isOnline) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000))
            ) {
                Text(
                    text = "Koneksi internet terputus atau video belum siap. Coba lagi nanti.",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = false
                    player = this@VideoPlayerScreen.player
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (showError) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xCC111111))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "Video gagal dimuat",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Pastikan jaringan stabil dan coba lagi.",
                        color = Color(0xFFE0E0E0),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Button(
                        onClick = {
                            showError = false
                            try {
                                val uri = Uri.parse(selectedEpisode.videoUrl)
                                val mediaItem = MediaItem.fromUri(uri)
                                player.setMediaItem(mediaItem)
                                player.prepare()
                                player.playWhenReady = true
                            } catch (_: Exception) {
                                showError = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                        modifier = Modifier.padding(top = 18.dp)
                    ) {
                        Text("Coba Lagi")
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = Color.White)
            }
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorit",
                    tint = if (isFavorite) PinkPrimary else Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(
                onClick = { player.playWhenReady = !player.isPlaying },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0x99000000), RoundedCornerShape(50.dp))
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = { /* share */ },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0x99000000), RoundedCornerShape(50.dp))
            ) {
                Icon(Icons.Filled.Share, contentDescription = "Bagikan", tint = Color.White)
            }

            IconButton(
                onClick = { showEpisodes = true },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0x99000000), RoundedCornerShape(50.dp))
            ) {
                Icon(Icons.Outlined.ListAlt, contentDescription = "Episode", tint = Color.White)
            }

            IconButton(
                onClick = { showSynopsis = true },
                modifier = Modifier
                    .size(52.dp)
                    .background(Color(0x99000000), RoundedCornerShape(50.dp))
            ) {
                Icon(Icons.Outlined.Info, contentDescription = "Sinopsis", tint = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0xAA000000))
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = drama.title,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "  •  ${selectedEpisode.title}",
                    color = Color(0xFFDADFEA),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = "${drama.category} • ${drama.rating} ★ • ${selectedEpisode.duration}",
                color = Color(0xFFE7E7EE),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }

    if (showEpisodes) {
        ModalBottomSheet(onDismissRequest = { showEpisodes = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Daftar Episode",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                drama.episodes.forEach { episode ->
                    val selected = episode.id == selectedEpisode.id
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selected) PinkPrimary.copy(alpha = 0.15f) else Color(0xFF1D1D26)
                        ),
                        onClick = {
                            selectedEpisode = episode
                            showEpisodes = false
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = episode.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = episode.duration,
                                    color = Color(0xFFB0B2BD),
                                    fontSize = 12.sp
                                )
                            }
                            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = Color.White)
                        }
                    }
                }
            }
        }
    }

    if (showSynopsis) {
        ModalBottomSheet(onDismissRequest = { showSynopsis = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Sinopsis",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = drama.description,
                    color = Color(0xFFDDE0EA),
                    modifier = Modifier.padding(top = 12.dp),
                    lineHeight = 24.sp
                )
                Button(
                    onClick = { showSynopsis = false },
                    colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                ) {
                    Text("Tutup")
                }
            }
        }
    }
}
