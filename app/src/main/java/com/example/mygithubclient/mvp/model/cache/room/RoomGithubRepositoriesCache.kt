package com.example.mygithubclient.mvp.model.cache.room

import com.example.mygithubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.mygithubclient.mvp.model.entity.GithubRepository
import com.example.mygithubclient.mvp.model.entity.GithubUser
import com.example.mygithubclient.mvp.model.entity.room.Database
import com.example.mygithubclient.mvp.model.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubRepositoriesCache(private val db: Database) : IGithubRepositoriesCache {

    override fun insert(user: GithubUser, repository: List<GithubRepository>) =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
            val roomRepo = repository.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepo)
        }.subscribeOn(Schedulers.io())

    override fun update(user: GithubUser, repository: List<GithubRepository>) =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
            db.repositoryDao.update(repository.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            })
        }.subscribeOn(Schedulers.io())

    override fun delete(user: GithubUser, repository: List<GithubRepository>) =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
            db.repositoryDao.delete(repository.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            })
        }.subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login)
        return@fromCallable db.repositoryDao.findForUser(roomUser.id)
            .map { GithubRepository(it.id, it.name, it.forksCount) }
    }.subscribeOn(Schedulers.io())
}