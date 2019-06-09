package com.fantastic.audiomessanger.model.dataBase.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.fantastic.audiomessanger.model.dataBase.entity.AudioMessage

@Dao
interface AudioMessageDao {

    @Query(value = "SELECT * FROM AudioMessage")
    fun getAllAudioMessages() : LiveData<List<AudioMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(audioMessage: AudioMessage)

    @Query(value = "DELETE FROM AudioMessage WHERE idAudioMessage = :id")
    fun deleteAudioMessage(id : Int)

    @Query(value = "DELETE FROM AudioMessage")
    fun deleteAllAudioMessages()
}