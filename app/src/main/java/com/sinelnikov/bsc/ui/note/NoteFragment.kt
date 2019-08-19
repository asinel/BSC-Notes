package com.sinelnikov.bsc.ui.note


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sinelnikov.bsc.R

import com.sinelnikov.bsc.databinding.FragmentNoteBinding
import com.sinelnikov.bsc.model.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : Fragment() {

    private val noteViewModel by viewModel<NoteViewModel>()
    private lateinit var binding : FragmentNoteBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        noteViewModel.init(navArgs<NoteFragmentArgs>().value)
        binding.vm = noteViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.liveData.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> Snackbar.make(view, R.string.success, Snackbar.LENGTH_SHORT).show()
                Status.ERROR -> Snackbar.make(view, it.message!!, Snackbar.LENGTH_SHORT).show()
                else -> { }
            }
        })
    }
}
