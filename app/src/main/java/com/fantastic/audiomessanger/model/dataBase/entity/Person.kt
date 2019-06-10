package com.fantastic.audiomessanger.model.dataBase.entity

import android.arch.persistence.room.*

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) var idPerson : Int = 0,
    var namePerson : String?,
    val numberPerson : Int?
){
    @Ignore
    constructor():this(0,"",123)
}