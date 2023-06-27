package com.example.gymmanager.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gymmanager.AddTrainerActivity
import com.example.gymmanager.Model.Trainer
import com.example.gymmanager.R

class AddTrainerListAdapter(val list : List<Trainer> , private val listener : ItemClicked) : Adapter<AddTrainerListAdapter.AddTrainerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddTrainerListViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.traineritem,parent,false)
        val viewHolder =  AddTrainerListViewHolder(view)

        viewHolder.removeBtn.setOnClickListener {
            listener.onRemoveClicked(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AddTrainerListViewHolder, position: Int) {
        holder.trainerName.text = list[position].name
    }


    class AddTrainerListViewHolder(itemView: View) : ViewHolder(itemView){
        val trainerName = itemView.findViewById<TextView>(R.id.trainer_name)
        val removeBtn = itemView.findViewById<ImageButton>(R.id.remove_btn)
    }
}

interface ItemClicked{
    fun onRemoveClicked(trainer: Trainer)
}