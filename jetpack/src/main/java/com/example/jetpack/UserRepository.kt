package com.example.jetpack

import dagger.Component
import javax.inject.Inject

class UserRepository @Inject constructor (
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
)

class UserLocalDataSource @Inject constructor()

class UserRemoteDataSource @Inject constructor()

