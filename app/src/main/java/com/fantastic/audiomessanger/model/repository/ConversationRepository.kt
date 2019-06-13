package com.fantastic.audiomessanger.model.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.fantastic.audiomessanger.model.dataBase.LocalDB
import com.fantastic.audiomessanger.model.dataBase.dao.ConversationDao
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ConversationRepository(application: Application) {

    private val conversationDao : ConversationDao
    private val listConversation : LiveData<List<Conversation>>

    init{
        val db : LocalDB? = LocalDB.getInstance(application)
        conversationDao = db?.conversationDao()!!
        listConversation = conversationDao.getAllConversation()
    }

    fun addConversation(conversation : Conversation){
        Single.fromCallable{
            conversationDao.insertConversation(conversation)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllConversations() : LiveData<List<Conversation>>{
        return listConversation
    }

    fun deleteAllConversations(){
        Single.fromCallable{
            conversationDao.deleteAllConversation()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun deleteConversation(id : Int){
        Single.fromCallable{
            conversationDao.deleteConversation(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}