package com.fantastic.audiomessanger.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fantastic.audiomessanger.ui.mainFragment.MainFragment
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.ui.conversationFragment.ConversationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ConversationFragment.newInstance())
                .commitNow()
        }
    }

}
