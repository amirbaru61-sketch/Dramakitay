package com.dramakita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.dramakita.data.NetworkMonitor
import com.dramakita.data.DramaRepository
import com.dramakita.model.Drama
import com.dramakita.ui.screens.FavoriteScreen
import com.dramakita.ui.screens.HomeScreen
import com.dramakita.ui.screens.SearchScreen
import com.dramakita.ui.screens.VideoPlayerScreen
import com.dramakita.ui.theme.DramaKitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DramaKitaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DramaKitaApp()
                }
            }
        }
    }
}

@Composable
private fun AppContent(
    dramas: List<Drama>,
    favoriteIds: androidx.lifecycle.compose.collectAsStateWithLifecycle
    ,
    onToggleFavorite: (Int) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val networkMonitor = remember { NetworkMonitor(context) }
    val isOnline = remember { networkMonitor.isNetworkAvailable() }

    var selectedDrama by remember { mutableStateOf<Drama?>(null) }
    var showSearch by remember { mutableStateOf(false) }
    var showFavorites by remember { mutableStateOf(false) }

    when {
        selectedDrama != null -> {
            VideoPlayerScreen(
                drama = selectedDrama!!,
                isOnline = isOnline,
                isFavorite = favoriteIds.value.contains(selectedDrama!!.id),
                onBack = { selectedDrama = null },
                onToggleFavorite = { onToggleFavorite(selectedDrama!!.id) },
                onClose = { selectedDrama = null }
            )
        }
        showSearch -> {
            SearchScreen(
                dramas = dramas,
                favorites = favoriteIds.value,
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
                favoriteIds = favoriteIds.value,
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
                favorites = favoriteIds.value,
                onOpenDrama = { selectedDrama = it },
                onToggleFavorite = onToggleFavorite,
                onSearchClicked = { showSearch = true },
                onOpenFavorites = { showFavorites = true }
            )
        }
    }
}
