package com.sinelnikov.bsc.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

import com.sinelnikov.bsc.databinding.FragmentNotesBinding
import com.sinelnikov.bsc.model.Status
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : Fragment() {

    private val notesViewModel by viewModel<NotesViewModel>()
    private val notesAdapter = NotesAdapter(listOf())
    private lateinit var binding : FragmentNotesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = notesViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvNotes.adapter = notesAdapter
        notesViewModel.liveData.observe(this, Observer { resource ->
            when(resource.status) {
                Status.SUCCESS -> notesAdapter.setNotes(resource.data!!)
                Status.ERROR -> Snackbar.make(view, resource.message!!, Snackbar.LENGTH_SHORT).show()
                else -> { }
            }
        })
    }
}
