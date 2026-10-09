package com.dramakita

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dramakita.data.DramaRepository
import com.dramakita.data.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteRepository = FavoriteRepository(application.applicationContext)

    val favoriteIds: StateFlow<Set<Int>> = favoriteRepository
        .getFavoritesFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptySet()
        )

    fun toggleFavorite(dramaId: Int) {
        viewModelScope.launch {
            favoriteRepository.toggleFavorite(dramaId)
        }
    }
}
