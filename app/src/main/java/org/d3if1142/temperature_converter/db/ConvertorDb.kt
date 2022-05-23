package org.d3if1142.temperature_converter.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConvertorEntity::class], version = 1, exportSchema = false)
abstract class ConvertorDb: RoomDatabase() {

    abstract val dao: ConvertorDao

    companion object{

        @Volatile
        private var INSTANCE: ConvertorDb? = null

        fun getInstance(context: Context): ConvertorDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConvertorDb::class.java,
                        "ConvertorDb"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}