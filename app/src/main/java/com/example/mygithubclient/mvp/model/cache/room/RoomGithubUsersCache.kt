package com.example.mygithubclient.mvp.model.cache.room

import com.example.mygithubclient.mvp.model.cache.IGithubUsersCache
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.entity.room.Database
import com.example.mygithubclient.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(private val db: Database) : IGithubUsersCache {
    override fun insert(users: List<GithubUser>): Completable = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl ?: "", user.reposUrl ?: "")
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun insert(user: GithubUser): Completable = Completable.fromAction {
        val roomUsers = user.let { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl ?: "", user.reposUrl ?: "")
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun delete(user: GithubUser): Completable = Completable.fromAction {
        val roomUser = user.let { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl ?: "", user.reposUrl ?: "")
        }
        db.userDao.delete(roomUser)
    }.subscribeOn(Schedulers.io())


    override fun getAll() = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }.subscribeOn(Schedulers.io())

    override fun getUser(user: GithubUser): Single<GithubUser> = Single.fromCallable {
        db.userDao.findByLogin(user.login).let { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }

    }
}