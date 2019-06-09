package com.fantastic.audiomessanger.model.dataBase.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

    @Entity(
        primaryKeys = ["conversationId", "personId"],
        foreignKeys = [
            ForeignKey(
                entity = Conversation :: class,
                parentColumns = ["id"],
                childColumns =  ["conversationId"],
                onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Person :: class,
            parentColumns = ["idPerson"],
            childColumns = ["personId"],
            onDelete = ForeignKey.CASCADE
        )]
    )
    data class ConversationPersonJoin(
        val conversationId : Int,
        val personId : Int
    )
