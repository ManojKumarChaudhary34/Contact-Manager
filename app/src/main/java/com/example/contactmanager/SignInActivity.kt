package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        signInUser()
    }

    private fun signInUser() {

        binding.btnSignIn.setOnClickListener {
            val signInEmail= binding.etEmailSignIn.text.toString().trim()
            val signInPassword= binding.etPasswordSignIn.text.toString().trim()

            if (signInEmail.isNotEmpty() && signInPassword.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(signInEmail,signInPassword).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()

                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()

                    }
                }

            }else{
                Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()

            }
        }

        binding.tvSignUp.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}