package com.fantastic.audiomessanger.model.dataBase.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation

@Dao
interface ConversationDao {

    @Query(value = "SELECT * FROM Conversation")
    fun getAllConversation() : LiveData<List<Conversation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConversation(conversation : Conversation)

    @Query(value = "DELETE FROM Conversation WHERE id = :id")
    fun deleteConversation(id : Int)

    @Query(value = "DELETE FROM Conversation")
    fun deleteAllConversation()
}