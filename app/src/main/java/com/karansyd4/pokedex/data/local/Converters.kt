package com.karansyd4.pokedex.data.local

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    companion object {
        const val DELIMITER_COMMA = ","
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun stringToList(stringListString: String): List<String> {
        return stringListString.split(DELIMITER_COMMA).map { it }
    }

    @TypeConverter
    fun listToString(stringList: List<String>): String {
        return stringList.joinToString(separator = DELIMITER_COMMA)
    }

}