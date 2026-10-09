package com.dramakita.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.favoriteDataStore: DataStore<Preferences> by preferencesDataStore(name = "favorite_drama")

class FavoriteRepository(private val context: Context) {
    private val favoriteKey = stringSetPreferencesKey("favorite_ids")

    fun getFavoritesFlow(): Flow<Set<Int>> = context.favoriteDataStore.data.map { prefs ->
        prefs[favoriteKey]?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    }

    suspend fun toggleFavorite(dramaId: Int) {
        context.favoriteDataStore.edit { prefs ->
            val current = prefs[favoriteKey]?.toMutableSet() ?: mutableSetOf()
            if (current.contains(dramaId.toString())) {
                current.remove(dramaId.toString())
            } else {
                current.add(dramaId.toString())
            }
            prefs[favoriteKey] = current
        }
    }
}
