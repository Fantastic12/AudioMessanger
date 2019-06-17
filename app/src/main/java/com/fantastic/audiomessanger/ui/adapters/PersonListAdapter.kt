package com.fantastic.audiomessanger.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.model.dataBase.entity.Person
import kotlinx.android.synthetic.main.item_person.view.*

class PersonListAdapter(val context : Context, list : List<Person>?)
    : RecyclerView.Adapter<PersonListAdapter.PersonView>() {

    private val TAG = PersonListAdapter::class.qualifiedName

    private var listPersons = ArrayList<Person>()

    init {
        this.listPersons = list as ArrayList<Person>
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PersonView {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_person, parent, false)

        return PersonView(view)
    }

    override fun getItemCount(): Int {
        return listPersons.size
    }

    override fun onBindViewHolder(holder: PersonView, position: Int) {
        holder.bind(listPersons[position])
    }

    fun setPersons(list : List<Person>){
        listPersons.clear()
        listPersons.addAll(list)
        notifyDataSetChanged()
    }

    inner class PersonView(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(person: Person){
            with(itemView){
                namePerson.text = person.namePerson
                idPerson.text = person.idPerson.toString()
            }
            itemView.setOnLongClickListener {
                itemView.deletePerson.visibility = View.VISIBLE
                true
            }
            itemView.deletePerson.setOnClickListener {
                itemView.deletePerson.visibility = View.GONE
                deleteById(person.idPerson)
            }
        }

        private fun deleteById(id : Int){

//            val factory = InjectorUtils.provideQuotesViewModelFactory(context.applicationContext)
//
//            val viewModel = ViewModelProviders.of(ConversationFragment().requireActivity(), factory)
//                .get(ConversationViewModel::class.java)
//
//            viewModel.deletePerson(id)

            Log.d(TAG,"Delete")
        }
    }
}
