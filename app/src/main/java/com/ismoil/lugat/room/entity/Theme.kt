package com.ismoil.lugat.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme")
class Theme (

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var name : String

){

}