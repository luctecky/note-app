package com.example.thenoteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thenoteapp.databinding.NoteLayoutBinding
import com.example.thenoteapp.fragments.HomeFragmentDirections
import com.example.thenoteapp.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBind: NoteLayoutBinding): RecyclerView.ViewHolder(itemBind.root)

    private val differCallback = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteDesc == newItem.noteDesc &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBind.noteTitle.text = currentNote.noteTitle
        holder.itemBind.noteDesc.text = currentNote.noteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)//currentNote
            it.findNavController().navigate(direction)
        }
    }
}