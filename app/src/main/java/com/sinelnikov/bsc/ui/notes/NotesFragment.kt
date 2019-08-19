package com.sinelnikov.bsc.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sinelnikov.bsc.R

import com.sinelnikov.bsc.databinding.FragmentNotesBinding
import com.sinelnikov.bsc.model.PublishedNote
import com.sinelnikov.bsc.model.Status
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : Fragment() {

    private val notesViewModel by viewModel<NotesViewModel>()
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter(listOf(), notesViewModel) }
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
        notesViewModel.liveData.observe(this, Observer {
            when(it.status) {
                Status.SUCCESS -> notesAdapter.setNotes(it.data!!)
                Status.ERROR -> Snackbar.make(view, it.message!!, Snackbar.LENGTH_SHORT).show()
                else -> { }
            }
        })
        notesViewModel.navigationLiveData.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                if (it.resId == R.id.noteFragment) {
                    val action = NotesFragmentDirections.actionNotesFragmentToNoteFragment(it.data as? PublishedNote)
                    findNavController().navigate(action)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //TODO: update in store and VM, without refresh
        notesViewModel.onRefresh()
    }
}
