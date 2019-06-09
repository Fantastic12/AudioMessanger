package com.fantastic.audiomessanger.model.dataBase.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Conversation (
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    val name : String?,
    val numberPerson: Int?,
    val numberMessage : Int?
)

