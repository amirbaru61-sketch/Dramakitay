package com.dramakita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dramakita.model.Drama
import com.dramakita.ui.components.PosterCard

@Composable
fun FavoriteScreen(
    dramas: List<Drama>,
    favoriteIds: Set<Int>,
    onBack: () -> Unit,
    onOpenDrama: (Drama) -> Unit,
    onToggleFavorite: (Int) -> Unit
) {
    val favoriteDramas = dramas.filter { favoriteIds.contains(it.id) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D0D12))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = Color.White)
            }
            Text(
                text = "Favorit Saya",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        if (favoriteDramas.isEmpty()) {
            Text(
                text = "Belum ada drama favorit. Tambahkan dari layar beranda.",
                color = Color(0xFFBFC1CC),
                modifier = Modifier.padding(top = 24.dp)
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                items(favoriteDramas) { drama ->
                    PosterCard(
                        drama = drama,
                        isFavorite = true,
                        onClick = { onOpenDrama(drama) },
                        onToggleFavorite = { onToggleFavorite(drama.id) }
                    )
                }
            }
        }
    }
}
