package com.fantastic.audiomessanger.interfaces

import android.support.v4.app.Fragment

interface MainNavigator {

    fun startFragment(fragment : Fragment, hide : Boolean)
}