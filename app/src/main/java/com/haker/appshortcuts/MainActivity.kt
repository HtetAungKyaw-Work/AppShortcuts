package com.haker.appshortcuts
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 25) {
            createShortcuts()
        }else{
            removeShortcuts()
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun createShortcuts() {
        val sM = getSystemService(ShortcutManager::class.java)

        val intent1 = Intent(applicationContext, ShortcutActivity1::class.java)
        intent1.action = Intent.ACTION_VIEW

        val shortcut1 = ShortcutInfo.Builder(this, "shortcut1")
            .setIntent(intent1)
            .setShortLabel("SC 1")
            .setLongLabel("Shortcut 1")
            .setShortLabel("This is the shortcut 1")
            .setDisabledMessage("Login to open this")
            .setIcon(Icon.createWithResource(this, android.R.drawable.ic_input_add))
            .build()

        sM.dynamicShortcuts = listOf(shortcut1)
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun removeShortcuts() {
        val shortcutManager = getSystemService(ShortcutManager::class.java)
        shortcutManager.disableShortcuts(listOf("shortcut1"))
        shortcutManager.removeAllDynamicShortcuts()
    }
}