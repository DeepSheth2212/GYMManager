package com.example.gymmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gymmanager.Adapters.AddTrainerListAdapter
import com.example.gymmanager.Model.Trainer
import com.example.gymmanager.databinding.ActivityAddTrainerBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddTrainerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddTrainerBinding
    private var trainerList = mutableListOf<Trainer>()
    private lateinit var adapter: AddTrainerListAdapter
    val dbref = FirebaseDatabase.getInstance().getReference("Trainers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTrainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AddTrainerListAdapter(trainerList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        getList()

        binding.addBtn.setOnClickListener {
            val trainerName = binding.trainerName.text.toString()
            addTrainer(Trainer(trainerName))
        }
    }

    private fun addTrainer(trainer : Trainer) {
        dbref.push().setValue(trainer).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(this, "Trainer added successfully!", Toast.LENGTH_SHORT).show()
                getList()
            }
        }
    }

    private fun getList() {
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