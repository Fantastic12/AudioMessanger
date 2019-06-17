package com.fantastic.audiomessanger.ui.fragments

import android.Manifest
import android.app.Activity
import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.interfaces.ListenerFragment
import com.fantastic.audiomessanger.ui.adapters.AudioListAdapter
import com.fantastic.audiomessanger.ui.adapters.PersonListAdapter
import com.fantastic.audiomessanger.utils.InjectorUtils
import com.fantastic.audiomessanger.viewModel.ConversationViewModel
import kotlinx.android.synthetic.main.conversation_fragment.view.*

class ConversationFragment : Fragment(), ListenerFragment{

    private var isClickedRecordAudio : Boolean = false

    private var adapter : AudioListAdapter? = null
    private var adapterPerson : PersonListAdapter? = null

    private var recyclerView : RecyclerView? = null
    private var recyclerViewPerson : RecyclerView? = null

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var linearLayoutManagerPerson : LinearLayoutManager

    private lateinit var viewModel : ConversationViewModel

    companion object {
        fun newInstance() = ConversationFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {

        val factory = InjectorUtils.provideQuotesViewModelFactory(context!!.applicationContext)

        viewModel = ViewModelProviders.of(this, factory)
            .get(ConversationViewModel::class.java)

        val view : View = inflater.inflate(R.layout.conversation_fragment, container, false)

        initRecycleView(view)
        onListener(view)

        viewModel.getAllAudioMessages().observe(this, Observer {
                audioMessages -> adapter!!.setAudioMessages(audioMessages!!)
        })

        viewModel.getAllPersons().observe(this, Observer {
            persons -> adapterPerson!!.setPersons(persons!!)
        })

//        viewModel.allAudioMessages.observe(this,
//            Observer<List<AudioMessage>> { t -> adapter!!.setAudioMessages(t!!) })
//
//        viewModel.allPersons.observe(this,
//            Observer<List<Person>> { t -> adapterPerson!!.setPersons(t!!) })

        return view
    }

    override fun onListener(view: View) {
        view.recordAudioButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(requireActivity(), permissions,0)
            } else {
                viewModel.initMediaRecorder()
                viewModel.startRecording()
                isClickedRecordAudio = true
                Toast.makeText(context,"start record",Toast.LENGTH_LONG).show()
            }
        }
        view.stopAndSaveButton.setOnClickListener {
            if(isClickedRecordAudio){
                viewModel.stopAndSaveAudio()
                isClickedRecordAudio = false
            }else{
                Toast.makeText(context,"Please start record",Toast.LENGTH_LONG).show()
            }
        }
        view.addPersonButton.setOnClickListener {
            viewModel.addPerson()
        }
    }

    private fun initRecycleView(view: View){
        adapter = AudioListAdapter(ArrayList())
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.adapter = adapter

        adapterPerson = PersonListAdapter(context!!.applicationContext, ArrayList())
        linearLayoutManagerPerson = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPerson = view.findViewById(R.id.recyclerViewPerson)
        recyclerViewPerson!!.layoutManager = linearLayoutManagerPerson
        recyclerViewPerson!!.adapter = adapterPerson
    }
}



