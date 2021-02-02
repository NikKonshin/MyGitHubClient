package com.example.mygithubclient.mvp.di.modules

import androidx.room.Room
import com.example.mygithubclient.App
import com.example.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.example.mygithubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.mygithubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.example.mygithubclient.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App) =
        Room.databaseBuilder(
            app,
            Database::class.java,
            Database.DB_NAME
        ).build()

    @Singleton
    @Provides
    fun userCache(database: Database): IGithubUsersCache =
        RoomGithubUsersCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGithubRepositoriesCache =
        RoomGithubRepositoriesCache(database)
}