package com.example.jetpack

import android.app.Application
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

class MyApplication : Application() {
    val appContainer = AppContainer()

    @Inject
    lateinit var dataSource: DataSource

}

data class DataBean(val name: String)

@Module
class DataModule {

//    @Provides
//    fun providerBean() = DataBean("xxx")
}

@Component(modules = [DataModule::class])
interface DataComponent : AndroidInjector<MyApplication> {
    fun getBean(): DataBean
}

class DataSource {

    @Inject
    lateinit var dataBen: DataBean

    fun getName() = dataBen.name
}

class DaggerDemoActivity : AppCompatActivity() {

    @Inject
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this).also {
            it.textSize = 26f
            it.text = dataSource.getName()
        }

        setContentView(textView)
    }
}