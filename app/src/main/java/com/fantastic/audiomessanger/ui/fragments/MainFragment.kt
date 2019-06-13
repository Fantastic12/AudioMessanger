package com.fantastic.audiomessanger.ui.fragments

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.databinding.MainFragmentBinding
import com.fantastic.audiomessanger.interfaces.ListenerFragment
import com.fantastic.audiomessanger.model.dataBase.entity.Conversation
import com.fantastic.audiomessanger.ui.activites.AddConversationActivity
import com.fantastic.audiomessanger.ui.adapters.ConversationListAdapter
import com.fantastic.audiomessanger.viewModel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment(), ListenerFragment {

    private var adapter : ConversationListAdapter? = null
    private var recyclerView : RecyclerView? = null
    private lateinit var linearLayoutManager : LinearLayoutManager

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding : MainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        val view : View  = binding.root

        binding.viewModel = viewModel

        onListener(view)
        initRecycleView(view)

        viewModel.allConversations.observe(this,
            Observer<List<Conversation>> { t -> adapter!!.setConversation(t!!) })

        return view
    }

    override fun onListener(view: View) {
        view.fab.setOnClickListener {
            startActivity(Intent(context, AddConversationActivity::class.java))
        }
    }

    private fun initRecycleView(view: View){
        adapter = ConversationListAdapter(Application(),ArrayList())
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById(R.id.list_conversation)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.adapter = adapter
    }

}
