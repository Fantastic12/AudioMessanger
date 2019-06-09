package com.fantastic.audiomessanger.model.dataBase.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class AudioMessage(
    @PrimaryKey(autoGenerate = true) var idAudioMessage : Int = 0,
    var url : String?
){
    @Ignore
    constructor():this(0,"")
}
