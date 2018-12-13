package top.rechinx.rikka.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseAppCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initNightTheme()
        super.onCreate(savedInstanceState)
    }

    private fun initNightTheme() {
        if (isNightTheme()) {
            delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    abstract fun isNightTheme() : Boolean
}