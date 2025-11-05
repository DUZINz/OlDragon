package com.edu.oldragon.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonagemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personagemDao(): PersonagemDao
}
