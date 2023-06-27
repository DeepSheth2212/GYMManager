package com.example.gymmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymmanager.Adapters.AddTrainerListAdapter
import com.example.gymmanager.Adapters.CheckBoxClicked
import com.example.gymmanager.Adapters.OnlineTrainerListAdapter
import com.example.gymmanager.Model.Trainer
import com.example.gymmanager.databinding.ActivityAddTrainerBinding
import com.example.gymmanager.databinding.ActivityOnlineTrainerBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OnlineTrainerActivity : AppCompatActivity() , CheckBoxClicked {


    private lateinit var binding: ActivityOnlineTrainerBinding
    private var trainerList = mutableListOf<Trainer>()
    private lateinit var adapter: OnlineTrainerListAdapter
    val dbref = FirebaseDatabase.getInstance().getReference("Trainers")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnlineTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = OnlineTrainerListAdapter(trainerList,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        getList()


    }

    override fun onCheckBoxClicked(trainer: Trainer) {
        dbref.child(trainer.name.toString()).child("live").setValue(!(trainer.isLive))
    }

    fun getList(){
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trainerList.clear()
                for(childSnapshot in snapshot.children){
                    val listItem = childSnapshot.getValue(Trainer::class.java)
                    listItem?.let{
                        trainerList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("deep", "loadPost:onCancelled", error.toException())
            }
        })
    }
}