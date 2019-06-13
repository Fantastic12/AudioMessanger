package com.fantastic.audiomessanger.ui.fragments

import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import com.fantastic.audiomessanger.ui.adapters.ConversationListAdapter
import com.fantastic.audiomessanger.viewModel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.view.*
import com.fantastic.audiomessanger.ui.fragments.MainFragment.OpenNewFragment as OpenNewFragment1

class MainFragment : Fragment(), ListenerFragment {

    private lateinit var openNewFragment : OpenNewFragment

    private var adapter : ConversationListAdapter? = null
    private var recyclerView : RecyclerView? = null
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var viewModel : MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    interface OpenNewFragment{
        fun openFragment()
    }

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
            openNewFragment.openFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        openNewFragment = activity as OpenNewFragment
    }

    private fun initRecycleView(view: View){
        adapter = ConversationListAdapter(Application(),ArrayList())
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById(R.id.list_conversation)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.adapter = adapter
    }

}
