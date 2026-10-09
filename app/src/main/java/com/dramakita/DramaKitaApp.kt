package com.dramakita

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.dramakita.data.NetworkMonitor
import com.dramakita.model.Drama
import com.dramakita.ui.screens.FavoriteScreen
import com.dramakita.ui.screens.HomeScreen
import com.dramakita.ui.screens.SearchScreen
import com.dramakita.ui.screens.VideoPlayerScreen

@Composable
fun AppContent(
    dramas: List<Drama>,
    favoriteIds: Set<Int>,
    onToggleFavorite: (Int) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val networkMonitor = remember { NetworkMonitor(context) }
    val isOnline = networkMonitor.isNetworkAvailable()

    var selectedDrama by remember { mutableStateOf<Drama?>(null) }
    var showSearch by remember { mutableStateOf(false) }
    var showFavorites by remember { mutableStateOf(false) }

    when {
        selectedDrama != null -> {
            VideoPlayerScreen(
                drama = selectedDrama!!,
                isOnline = isOnline,
                isFavorite = favoriteIds.contains(selectedDrama!!.id),
                onBack = { selectedDrama = null },
                onToggleFavorite = { onToggleFavorite(selectedDrama!!.id) },
                onClose = { selectedDrama = null }
            )
        }
        showSearch -> {
            SearchScreen(
                dramas = dramas,
                favorites = favoriteIds,
                onBack = { showSearch = false },
                onSelectDrama = {
                    selectedDrama = it
                    showSearch = false
                },
                onToggleFavorite = onToggleFavorite
            )
        }
        showFavorites -> {
            FavoriteScreen(
                dramas = dramas,
                favoriteIds = favoriteIds,
                onBack = { showFavorites = false },
                onOpenDrama = {
                    selectedDrama = it
                    showFavorites = false
                },
                onToggleFavorite = onToggleFavorite
            )
        }
        else -> {
            HomeScreen(
                dramas = dramas,
                favorites = favoriteIds,
                onOpenDrama = { selectedDrama = it },
                onToggleFavorite = onToggleFavorite,
                onSearchClicked = { showSearch = true },
                onOpenFavorites = { showFavorites = true }
            )
        }
    }
}
