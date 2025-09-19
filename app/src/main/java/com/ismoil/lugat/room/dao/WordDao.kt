package com.ismoil.lugat.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ismoil.lugat.room.entity.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word WHERE themeId = :themeId")
     fun getWordsForTheme(themeId: Int): LiveData<List<Word>>

    @Query("SELECT * FROM word WHERE word LIKE '%' || :query || '%'")
    suspend fun searchWords(query: String): List<Word>

    @Query("SELECT * FROM word WHERE word LIKE '%' || :query || '%' AND themeId = :themeId")
     fun searchWordsInTheme(query: String, themeId: Int): LiveData<List<Word>>

    @Query("SELECT * FROM word WHERE word = :word")
    suspend fun getWordByText(word: String): Word?

    @Query("SELECT * FROM word WHERE id = :id")
     fun getWordById(id: Int): Word?

    @Query("SELECT * FROM word WHERE word = :word AND themeId = :themeId")
    suspend fun getWordByTextAndThemeId(word: String, themeId: Int): Word?


    @Insert
     fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
     fun deleteWord(word: Word)


}