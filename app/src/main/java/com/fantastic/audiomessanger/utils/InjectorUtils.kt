package com.fantastic.audiomessanger.utils

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import com.fantastic.audiomessanger.model.dataBase.LocalDB
import com.fantastic.audiomessanger.model.repository.AudioRepository
import com.fantastic.audiomessanger.model.repository.PersonRepository

object InjectorUtils {

    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory(context: Context): ConversationViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val audioRepository = AudioRepository.getInstance(LocalDB.getInstance(context).audioMessageDao())
        val personRepository = PersonRepository.getInstance(LocalDB.getInstance(context).personDao())

        return ConversationViewModelFactory(context,audioRepository,personRepository)
    }
}