package com.karansyd4.pokedex.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pokedex_table", indices = [Index(value = ["number"], unique = true)])
data class PokedexEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "number")
    val number: Int,

    @ColumnInfo(name = "type")
    val type: List<String>
)