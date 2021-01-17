package com.example.jetpack

import dagger.Component


@Component
interface ApplicationGraph{
    fun repository(): UserRepository
}