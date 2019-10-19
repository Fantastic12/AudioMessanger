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
import com.fantastic.audiomessanger.ui.adapters.ConversationListAdapter
import com.fantastic.audiomessanger.viewModel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var openNewFragment: OpenNewFragment

    private var adapter = ConversationListAdapter(Application(), ArrayList())
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    interface OpenNewFragment {
        fun openFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        val view: View = binding.root

        binding.viewModel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        initListeners()
        viewModel.allConversations.observe(
            viewLifecycleOwner,
            Observer { t -> t?.let { adapter.setConversation(it) } })
    }

    private fun initListeners() {
        fab.setOnClickListener { openNewFragment.openFragment() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        openNewFragment = activity as OpenNewFragment
    }

    private fun initRecycleView() {
        list_conversation.layoutManager = LinearLayoutManager(requireContext())
        list_conversation.adapter = adapter
    }

}
