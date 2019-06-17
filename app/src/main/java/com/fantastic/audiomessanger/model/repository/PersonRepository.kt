package com.fantastic.audiomessanger.model.repository

import android.arch.lifecycle.LiveData
import com.fantastic.audiomessanger.model.dataBase.dao.PersonDao
import com.fantastic.audiomessanger.model.dataBase.entity.Person
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonRepository private constructor(private val personDao : PersonDao) {

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: PersonRepository? = null

        fun getInstance(personDao : PersonDao) =
            instance ?: synchronized(this) {
                instance ?: PersonRepository(personDao).also { instance = it }
            }
    }

    fun addPerson(person: Person){
        Single.fromCallable{
            personDao.insertPerson(person)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllPersons() : LiveData<List<Person>> {
        return personDao.getAllPersons()
    }

    fun deleteAllPersons(){
        Single.fromCallable{
            personDao.deleteAllPersons()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun deletePerson(id : Int){
        Single.fromCallable{
            personDao.deletePerson(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}