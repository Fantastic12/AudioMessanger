package com.fantastic.audiomessanger.model.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.fantastic.audiomessanger.model.dataBase.dao.AudioMessageDao
import com.fantastic.audiomessanger.model.dataBase.dao.ConversationDao
import com.fantastic.audiomessanger.model.dataBase.dao.ConversationPersonJoinDao
import com.fantastic.audiomessanger.model.dataBase.dao.PersonDao
import com.fantastic.audiomessanger.model.dataBase.entity.AudioMessage
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.model.dataBase.entity.ConversationPersonJoin
import com.fantastic.audiomessanger.model.dataBase.entity.Person

@Database(entities = [Person::class, Conversation::class, ConversationPersonJoin::class, AudioMessage::class],
    version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {

    abstract fun conversationPersonJoinDao() : ConversationPersonJoinDao
    abstract fun conversationDao() : ConversationDao
    abstract fun personDao() : PersonDao
    abstract fun audioMessageDao() : AudioMessageDao

    companion object {

        @Volatile private var INSTANCE : LocalDB? = null

        fun getInstance(context: Context) : LocalDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                LocalDB::class.java, "local.db")
                .build()

    }

        fun destroyInstance(){
            INSTANCE = null
        }
}
