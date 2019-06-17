package com.fantastic.audiomessanger.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.fantastic.audiomessanger.model.repository.AudioRepository
import com.fantastic.audiomessanger.model.repository.PersonRepository
import com.fantastic.audiomessanger.viewModel.ConversationViewModel

class ConversationViewModelFactory(private val context: Context,
                                   private val audioRepository : AudioRepository,
                                   private val personRepository : PersonRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConversationViewModel(context,audioRepository,personRepository) as T
    }

}