package com.example.gymmanager.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gymmanager.Model.Trainer
import com.example.gymmanager.R

class OnlineTrainerListAdapter(val list : List<Trainer> , val listener : CheckBoxClicked) : Adapter<OnlineTrainerListAdapter.OnlineTrainerListViewHolder>() {

    class OnlineTrainerListViewHolder(itemView: View) : ViewHolder(itemView){
        val trainerName = itemView.findViewById<TextView>(R.id.trainer_name)
        val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineTrainerListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.online_trainer_item,parent,false)
        val viewHolder = OnlineTrainerListViewHolder(view)

        viewHolder.checkBox.setOnClickListener {
            listener.onCheckBoxClicked(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnlineTrainerListViewHolder, position: Int) {
        holder.trainerName.text = list[position].name
        holder.checkBox.isChecked = list[position].isLive
    }
}

interface CheckBoxClicked{
    fun onCheckBoxClicked(trainer : Trainer)

}