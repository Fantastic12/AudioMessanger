package com.fantastic.audiomessanger.model.repository

import android.arch.lifecycle.LiveData
import com.fantastic.audiomessanger.model.dataBase.dao.AudioMessageDao
import com.fantastic.audiomessanger.model.dataBase.entity.AudioMessage
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AudioRepository private constructor(private val audioMessageDao: AudioMessageDao) {

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: AudioRepository? = null

        fun getInstance(audioMessageDao: AudioMessageDao) =
            instance ?: synchronized(this) {
                instance ?: AudioRepository(audioMessageDao).also { instance = it }
            }
    }

    fun addAudioMessage(audioMessage: AudioMessage){
        Single.fromCallable{
            audioMessageDao.insertAudioMessage(audioMessage)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllAudioMessages() : LiveData<List<AudioMessage>>{
        return audioMessageDao.getAllAudioMessages()
    }

}