package com.example.estatebook_app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.estatebook_app.data.local.EstateMainEntity

@Database(entities = [EstateMainEntity::class], version = 3)
public abstract class EstateDatabase: RoomDatabase() {
    abstract val dao: EstateMainDao //доступ к dao для редактирования таблицы бд
}