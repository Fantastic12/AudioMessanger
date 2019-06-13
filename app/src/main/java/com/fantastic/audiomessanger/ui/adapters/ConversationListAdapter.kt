package com.fantastic.audiomessanger.ui.adapters

import android.app.Application
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.ui.activites.ConversationViewActivity
import com.fantastic.audiomessanger.ui.fragments.ConversationFragment
import com.fantastic.audiomessanger.viewModel.MainViewModel
import kotlinx.android.synthetic.main.item_conversation.view.*

class ConversationListAdapter(val application: Application, list: List<Conversation>?)
    : RecyclerView.Adapter<ConversationListAdapter.ConversationView>() {

    private var listConversation = ArrayList<Conversation>()

    init {
        this.listConversation = list as ArrayList<Conversation>
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ConversationView {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_conversation, parent, false)

        return ConversationView(view)
    }

    override fun getItemCount(): Int {
        return listConversation.size
    }

    override fun onBindViewHolder(holder: ConversationView, position: Int) {
        holder.bind(listConversation[position])
    }

    fun setConversation(list : List<Conversation>){
        listConversation.clear()
        listConversation.addAll(list)
        notifyDataSetChanged()
    }

    inner class ConversationView(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(conversation: Conversation){
            with(itemView){
                numberAudio.text = conversation.numberMessage.toString()
                numberPerson.text = conversation.numberPerson.toString()
                nameConversation.text = conversation.name

                Glide
                    .with(this)
                    .load(R.drawable.messenger_icon)
                    .into(avatarConversation)
            }
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context,ConversationViewActivity::class.java))
            }
            itemView.setOnLongClickListener {
                itemView.delete.visibility = View.VISIBLE
                true
            }
            itemView.delete.setOnClickListener {
                itemView.delete.visibility = View.GONE
                deleteById(conversation.id)
            }
        }

        private fun deleteById(id : Int){
            val viewModel = MainViewModel(application)
            viewModel.deleteConversation(id)
        }
    }
}