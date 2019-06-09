package com.fantastic.audiomessanger.model.dataBase.dao

import android.arch.persistence.room.*
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.model.dataBase.entity.ConversationPersonJoin
import com.fantastic.audiomessanger.model.dataBase.entity.Person

@Dao
interface ConversationPersonJoinDao {

    @Insert
    fun insert(conversationPersonJoin: ConversationPersonJoin)

    @Query("""
           SELECT * FROM conversation
           INNER JOIN conversationpersonjoin
           ON conversation.id = conversationpersonjoin.conversationId
           WHERE conversationpersonjoin.personId=:personId
           """)
    fun getConverstationsForSong(personId: Int): List<Conversation>

    @Query("""
           SELECT * FROM person
           INNER JOIN conversationpersonjoin
           ON person.idPerson = conversationpersonjoin.personId
           WHERE conversationpersonjoin.conversationId=:conversationId
           """)
    fun getPersonsForConversation(conversationId: Int): List<Person>
}