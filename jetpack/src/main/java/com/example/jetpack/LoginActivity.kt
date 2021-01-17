package com.example.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginUserData: LoginUserData
    private lateinit var appContainer: AppContainer

    @Inject
    lateinit var applicationGraph: ApplicationGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appContainer = (application as MyApplication).appContainer

        appContainer.loginContainer = LoginContainer(appContainer.userRepository)

//        loginViewModel = appContainer.loginViewModeFactory.create()
        loginViewModel = appContainer.loginContainer!!.loginViewModeFactory.create()
        loginUserData = appContainer.loginContainer!!.loginUserData

    }

    override fun onDestroy() {
        appContainer.loginContainer = null
        super.onDestroy()
    }
}