package com.fantastic.audiomessanger.ui.adapters

import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.model.dataBase.entity.AudioMessage
import kotlinx.android.synthetic.main.item_audio.view.*
import java.io.IOException

class AudioListAdapter(list : List<AudioMessage>?)
    : RecyclerView.Adapter<AudioListAdapter.AudioView>() {

    private var listAudioMessages = ArrayList<AudioMessage>()

    init {
        this.listAudioMessages = list as ArrayList<AudioMessage>
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AudioView {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_audio, parent, false)

        return AudioView(view)
    }

    override fun getItemCount(): Int {
        return listAudioMessages.size
    }

    override fun onBindViewHolder(holder: AudioView, position: Int) {
        holder.bind(listAudioMessages[position])
    }

    fun setAudioMessages(list : List<AudioMessage>){
        val initPosition = listAudioMessages.size
        listAudioMessages.clear()
        listAudioMessages.addAll(list)
        notifyItemRangeInserted(initPosition, listAudioMessages.size)
    }

    inner class AudioView(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(audioMessage: AudioMessage){
            with(itemView){
                idMp3.text = audioMessage.idAudioMessage.toString()
                urlMp3.text = audioMessage.url
            }
            itemView.setOnClickListener {
                Log.d("ITEM CLICKED", audioMessage.idAudioMessage.toString())
                val mediaPlayer = MediaPlayer ()
                try {
                    mediaPlayer.setDataSource (audioMessage.url)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    Toast.makeText (itemView.context, "Playing", Toast.LENGTH_LONG).show()
                } catch (e: IOException) {
                    Log.d("Error",e.toString())
                    Toast.makeText (itemView.context, "The file does not exist", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}