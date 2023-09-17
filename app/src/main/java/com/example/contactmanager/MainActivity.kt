package com.example.contactmanager

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.contactmanager.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dialog: Dialog
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        databaseReference =
            FirebaseDatabase.getInstance().reference.child("contacts")
                .child(auth.currentUser?.uid.toString())
        createDialog()
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val phone = binding.etNumber.text.toString()

            val contacts = ContactDetails(name, phone)
            if (name.isNotEmpty() && phone.isNotEmpty()) {
               databaseReference.setValue(contacts).addOnCompleteListener {
                   if (it.isSuccessful){
                       dialog.show()
                   }else{
                       Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()

                   }
               }

            } else {
                Toast.makeText(this, "Enter your name and password", Toast.LENGTH_SHORT).show()

            }


        }
        val btn_ok = dialog.findViewById<Button>(R.id.ok_button)

        btn_ok.setOnClickListener {
            dialog.dismiss()
        }


    }


    private fun createDialog() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_layout)
    }

}