package com.fourseven.daggertryout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.fourseven.daggertryout.data.DataManager
import com.fourseven.daggertryout.data.model.User
import com.fourseven.daggertryout.di.component.ActivityComponent
import com.fourseven.daggertryout.di.component.DaggerActivityComponent
import com.fourseven.daggertryout.di.module.ActivityModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mDataManager: DataManager

    private lateinit var mActivityComponent: ActivityComponent

    private fun getActComponent(): ActivityComponent {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(App.get(this).getComponent())
                .build()
        return mActivityComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getActComponent().inject(this)

        createUser()
        getUser()
        mDataManager.saveAccessToken("ASDR12443JFDJF43543J543H3K543")

        val token = mDataManager.getAccessToken()
        if (token != null) {
            tv_access_token.text = token
        }
    }

    private fun createUser() {
        try {
            mDataManager.createUser(User("Iqbal", "137"))
            Log.e("MAIN_ACT", "Berhasil create User")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("MAIN_ACT", "Gagal create User ${e.message}")
        }
    }

    private fun getUser() {
        try {
            val user = mDataManager.getUser(1L)
            tv_user_info.text = user.name
            Log.e("MAIN_ACT", "Berhasil get User")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("MAIN_ACT", "Gagal get User ${e.message}")
        }
    }
}