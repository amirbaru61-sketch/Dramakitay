package com.dramakita

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.MaterialTheme
import com.dramakita.ui.theme.DramaKitaTheme

@Composable
fun DramaKitaApp() {
    val viewModel: MainViewModel = viewModel()
    val dramas = DramaRepository.getAllDramas()
    val favoriteIds = viewModel.favoriteIds

    DramaKitaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppContent(
                dramas = dramas,
                favoriteIds = favoriteIds,
                onToggleFavorite = viewModel::toggleFavorite
            )
        }
    }
}
