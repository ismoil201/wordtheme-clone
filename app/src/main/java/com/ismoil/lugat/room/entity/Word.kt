package com.ismoil.lugat.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "word",
    foreignKeys = [
    ForeignKey(
        entity = Theme::class,
        parentColumns = ["id"],
        childColumns = ["themeId"],
        onDelete = ForeignKey.CASCADE
    )
]
)
class Word(

    @PrimaryKey(autoGenerate = true)
    var id : Int =0,
    var word : String,
    var meaning : String,
    var themeId : Int
) {

}