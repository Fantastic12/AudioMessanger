package com.fantastic.audiomessanger.ui.activites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.interfaces.MainNavigator
import com.fantastic.audiomessanger.ui.fragments.ConversationFragment

class ConversationViewActivity : AppCompatActivity(), MainNavigator {

    private val fragmentMain = ConversationFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.conversation_view_activity)

        if (savedInstanceState == null) {
            openFragment(fragmentMain)
        }

    }

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container1, fragment)
            .commitNow()
    }

}
