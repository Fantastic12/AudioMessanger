package com.fantastic.audiomessanger.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.model.repository.ConversationRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ConversationRepository = ConversationRepository(application)
    internal var allConversations: LiveData<List<Conversation>>

    init {
        allConversations = repository.getAllConversations()
    }

    fun deleteAllConversations() = repository.deleteAllConversations()
    fun deleteConversation(id: Int) = repository.deleteConversation(id)
}
