package com.fantastic.audiomessanger.model.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.fantastic.audiomessanger.model.dataBase.LocalDB
import com.fantastic.audiomessanger.model.dataBase.dao.AudioMessageDao
import com.fantastic.audiomessanger.model.dataBase.entity.AudioMessage
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AudioRepository(application: Application) {

    private val audioMessageDao : AudioMessageDao
    private val listAudioMessages: LiveData<List<AudioMessage>>

    init{
        val db : LocalDB? = LocalDB.getInstance(application)
        audioMessageDao = db?.audioMessageDao()!!
        listAudioMessages = audioMessageDao.getAllAudioMessages()
    }

    fun addAudioMessage(audioMessage: AudioMessage){
        Single.fromCallable{
            audioMessageDao.insert(audioMessage)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllAudioMessages() : LiveData<List<AudioMessage>>{
        return listAudioMessages
    }

}