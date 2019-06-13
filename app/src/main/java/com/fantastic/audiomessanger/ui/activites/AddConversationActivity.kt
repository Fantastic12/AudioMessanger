package com.fantastic.audiomessanger.ui.activites

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.databinding.FragmentAddConversationBinding
import com.fantastic.audiomessanger.interfaces.ListenerActivity
import com.fantastic.audiomessanger.viewModel.AddConversationViewModel
import kotlinx.android.synthetic.main.fragment_add_conversation.*

class AddConversationActivity : AppCompatActivity(), ListenerActivity {

    private lateinit var viewModel : AddConversationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProviders.of(this).get(AddConversationViewModel::class.java)
        val binding: FragmentAddConversationBinding = DataBindingUtil.setContentView(this, R.layout.fragment_add_conversation)
        binding.viewModel = viewModel

        onListener()

    }

    override fun onListener() {
        addConversation.setOnClickListener {
            viewModel.attemptField {
                finish()
            }
        }
    }

}
