package com.ismoil.lugat.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.ismoil.lugat.room.entity.Theme
import com.ismoil.lugat.room.entity.ThemeWithWords
import com.ismoil.lugat.room.entity.Word
@Dao
interface ThemeDao {

    @Query("SELECT * FROM theme")
    fun getAllThemes(): LiveData<List<Theme>>

    @Insert
    suspend fun insertTheme(theme: Theme) // <- suspend qo‘shildi

    @Transaction
    @Query("SELECT * FROM theme WHERE id = :themeId")
    fun getThemeWithWords(themeId: Int): ThemeWithWords
}
