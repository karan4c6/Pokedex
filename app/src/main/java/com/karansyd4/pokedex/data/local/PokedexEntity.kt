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
    val type: List<String>,

    @ColumnInfo(name = "evolveCandy")
    val evolveCandy: Int? = null,

    @ColumnInfo(name = "evolveToName")
    val evolveToName: String? = null,

    @ColumnInfo(name = "evolveToNumber")
    val evolveToNumber: Int? = null,

    @ColumnInfo(name = "mega")
    val mega: Boolean,

    @ColumnInfo(name = "megaEnergy")
    val megaEnergy: String? = null,

    @ColumnInfo(name = "buddyCandyKm")
    val buddyCandyKm: Int,

    @ColumnInfo(name = "fastMove")
    val fastMove: String,

    @ColumnInfo(name = "chargedMove")
    val chargedMove: String,

    @ColumnInfo(name = "specialMove")
    val specialMove: String? = null
)