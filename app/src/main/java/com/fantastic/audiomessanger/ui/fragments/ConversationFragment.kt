package com.fantastic.audiomessanger.ui.fragments

import android.Manifest
import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.ui.adapters.AudioListAdapter
import com.fantastic.audiomessanger.ui.adapters.PersonListAdapter
import com.fantastic.audiomessanger.viewModel.ConversationViewModel
import kotlinx.android.synthetic.main.conversation_fragment.*

class ConversationFragment : Fragment() {

    private var isClickedRecordAudio = false

    private var adapter = AudioListAdapter(emptyList())
    private var adapterPerson = PersonListAdapter(Application(), emptyList())

    private lateinit var viewModel: ConversationViewModel

    companion object {
        fun newInstance() = ConversationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(ConversationViewModel::class.java)
        return inflater.inflate(R.layout.conversation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.allAudioMessages.observe(viewLifecycleOwner,
            Observer { t -> t?.let { adapter.setAudioMessages(it) } })

        viewModel.allPersons.observe(viewLifecycleOwner,
            Observer { t -> t?.let { adapterPerson.setPersons(it) } })
    }

    private fun initListeners() {
        recordAudioButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val permissions = arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(requireActivity(), permissions, 0)
            } else {
                viewModel.initMediaRecorder()
                viewModel.startRecording()
                isClickedRecordAudio = true
            }
        }
        stopAndSaveButton.setOnClickListener {
            if (isClickedRecordAudio) {
                viewModel.stopAndSaveAudio()
                isClickedRecordAudio = false
            } else {
                Toast.makeText(context, "Please start record", Toast.LENGTH_LONG).show()
            }
        }
        addPersonButton.setOnClickListener { viewModel.addPerson() }
    }

    private fun initRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        recyclerViewPerson.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPerson.adapter = adapterPerson
    }
}



