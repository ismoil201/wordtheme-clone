package com.ismoil.lugat.room.entity

import androidx.room.Embedded
import androidx.room.Relation

class ThemeWithWords (
    @Embedded
    val theme: Theme,

    @Relation (
        parentColumn = "id",
        entityColumn = "themeId"
    )
    val words : List<Word>
){
}