package com.fantastic.audiomessanger.model.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.fantastic.audiomessanger.model.dataBase.LocalDB
import com.fantastic.audiomessanger.model.dataBase.dao.PersonDao
import com.fantastic.audiomessanger.model.dataBase.entity.Person
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonRepository(application: Application) {

    private val personDao : PersonDao
    private val listPersons: LiveData<List<Person>>

    init{
        val db : LocalDB? = LocalDB.getInstance(application)
        personDao = db?.personDao()!!
        listPersons = personDao.getAllPersons()
    }

    fun addPerson(person: Person){
        Single.fromCallable{
            personDao.insert(person)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getAllPersons() : LiveData<List<Person>> {
        return listPersons
    }

}