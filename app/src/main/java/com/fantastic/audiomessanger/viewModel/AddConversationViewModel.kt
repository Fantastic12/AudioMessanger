package com.fantastic.audiomessanger.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.widget.Toast
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.model.repository.ConversationRepository

class AddConversationViewModel(application: Application) : AndroidViewModel(application) {

    val name = MutableLiveData<String>()

    private val repository : ConversationRepository = ConversationRepository(application)

    private fun addConversation(){
        val conversation = Conversation()
        conversation.name = name.value
        repository.addConversation(conversation)
        Log.d("Name",name.value.toString())
        Toast.makeText(getApplication(),"Successful", Toast.LENGTH_LONG).show()
    }

    fun attemptField(finish: () -> Unit){
        when {
            name.value == null -> Toast.makeText(getApplication(),"Name empty", Toast.LENGTH_SHORT).show()
            name.value!!.length <= 4 -> Toast.makeText(getApplication(),"Name < 4", Toast.LENGTH_SHORT).show()
            else -> {
                addConversation()
                finish()
            }
        }
    }

}