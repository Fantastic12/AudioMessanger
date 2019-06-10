package com.fantastic.audiomessanger.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.fantastic.audiomessanger.model.dataBase.entity.AudioMessage
import com.fantastic.audiomessanger.model.dataBase.entity.Person
import com.fantastic.audiomessanger.model.repository.AudioRepository
import com.fantastic.audiomessanger.model.repository.PersonRepository
import java.io.IOException
import java.util.*

class ConversationViewModel(application : Application)
    : AndroidViewModel(application) {

    private val audioRepository : AudioRepository
    private val personRepository : PersonRepository

    internal val allAudioMessages: LiveData<List<AudioMessage>>
    internal val allPersons : LiveData<List<Person>>

    init {
        audioRepository = AudioRepository(application)
        personRepository = PersonRepository(application)
        allPersons = personRepository.getAllPersons()
        allAudioMessages = audioRepository.getAllAudioMessages()
    }

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null

    fun startRecording() {
        try {
            mediaRecorder?.prepare()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaRecorder?.start()
        Toast.makeText(getApplication(),"start", Toast.LENGTH_LONG).show()
    }

    fun stopAndSaveAudio() {
        mediaRecorder?.stop()
        mediaRecorder?.reset()
        mediaRecorder?.release()
        mediaRecorder = null
        saveAudio()
        Toast.makeText(getApplication(),"stop", Toast.LENGTH_LONG).show()
    }

    fun initMediaRecorder(){
        mediaRecorder = MediaRecorder()
        output = Environment.getExternalStorageDirectory().absolutePath + "/" + generateRandomName() + ".mp3"

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    private fun generateRandomName() : String{
        return UUID.randomUUID().toString()
    }

    private fun saveAudio() {
        val audioMessage = AudioMessage()
        audioMessage.url = output
        Log.d("Audio url", audioMessage.url.toString())
        audioRepository.addAudioMessage(audioMessage)
    }

    fun addPerson() {
        val person = Person()
        person.namePerson = generateRandomName()
        Log.d("Person", person.namePerson)
        personRepository.addPerson(person)
    }

}
