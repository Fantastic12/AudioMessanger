package com.fantastic.audiomessanger.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.widget.Toast
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.model.repository.ConversationRepository
import com.fantastic.audiomessanger.ui.fragments.MainFragment

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository : ConversationRepository = ConversationRepository(application)
    internal var allConversations: LiveData<List<Conversation>>

    init {
        allConversations = repository.getAllConversations()
    }

    fun deleteAllConversations(){
        repository.deleteAllConversations()
    }

    fun deleteConversation(id : Int){
        repository.deleteConversation(id)
    }
}
