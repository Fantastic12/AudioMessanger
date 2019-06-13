package com.fantastic.audiomessanger.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fantastic.audiomessanger.R
import com.fantastic.audiomessanger.databinding.FragmentAddConversationBinding
import com.fantastic.audiomessanger.interfaces.ListenerFragment
import com.fantastic.audiomessanger.viewModel.AddConversationViewModel
import kotlinx.android.synthetic.main.fragment_add_conversation.view.*

class AddConversationFragment : Fragment(), ListenerFragment {

    private lateinit var viewModel : AddConversationViewModel

    private lateinit var destroyFragment : DestroyFragment

    companion object{
        fun newInstance() = AddConversationFragment()
    }

    interface DestroyFragment{
        fun destroyAddConversation()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        viewModel = ViewModelProviders.of(this).get(AddConversationViewModel::class.java)
        val binding : FragmentAddConversationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_conversation, container, false)
        val view : View = binding.root

        binding.viewModel = viewModel

        onListener(view)

        return view
    }

    override fun onListener(view: View) {
        view.addConversation.setOnClickListener {
            viewModel.attemptField {
                destroyFragment.destroyAddConversation()
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        destroyFragment = activity as DestroyFragment
    }

}
