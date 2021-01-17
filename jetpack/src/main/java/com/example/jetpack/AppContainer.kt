package com.example.jetpack

class AppContainer {

    private val userLocalDataSource = UserLocalDataSource()
    private val userRemoteDataSource = UserRemoteDataSource()

    val userRepository = UserRepository(userLocalDataSource, userRemoteDataSource)

    val loginViewModeFactory = LoginViewModeFactory(userRepository)

    var loginContainer: LoginContainer? = null

    interface Factory<T> {
        fun create(): T
    }
}

class LoginViewModeFactory(private val userRepository: UserRepository) : AppContainer.Factory<LoginViewModel> {
    override fun create(): LoginViewModel {
        return LoginViewModel(userRepository)
    }
}

class LoginUserData

class LoginContainer(private val userRepository: UserRepository) {
    val loginUserData = LoginUserData()
    val loginViewModeFactory = LoginViewModeFactory(userRepository)
}