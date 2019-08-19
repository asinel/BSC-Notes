package com.sinelnikov.bsc.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sinelnikov.bsc.databinding.ItemNoteBinding
import com.sinelnikov.bsc.model.PublishedNote

class NotesAdapter(private var notes: List<PublishedNote>, private val onNoteClickListener: OnNoteClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = notes[position]
        holder.binding.root.setOnClickListener { onNoteClickListener.onNoteClicked(notes[position]) }
    }

    override fun getItemCount() = notes.size

    fun setNotes(notes: List<PublishedNote>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder constructor(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnNoteClickListener {

        fun onNoteClicked(note: PublishedNote)
    }
}