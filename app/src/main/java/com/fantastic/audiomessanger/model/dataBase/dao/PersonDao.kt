package com.fantastic.audiomessanger.model.dataBase.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.fantastic.audiomessanger.model.dataBase.entity.Person

@Dao
interface PersonDao {

    @Query(value = "SELECT * FROM Person")
    fun getAllPersons() : List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person : Person)

    @Query(value = "DELETE FROM Person WHERE idPerson = :id")
    fun deletePerson(id : Int)

    @Query(value = "DELETE FROM Person")
    fun deleteAllPersons()
}