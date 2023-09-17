package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactmanager.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        createUser()
    }

    private fun createUser() {
        binding.btnSignUp.setOnClickListener{
            val email= binding.etEmailSignUp.text.toString().trim()
            val password= binding.etPasswordSignUp.text.toString().trim()
            val confirmPassword= binding.etConformPassSignUp.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show()

                            val intent= Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                            finish()

                        }else{
                            Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()

                        }
                    }
                }else{
                    Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show()

                }

            }else{
                Toast.makeText(this, "Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }

        }
        binding.tvSignIn.setOnClickListener {
            val intent= Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}