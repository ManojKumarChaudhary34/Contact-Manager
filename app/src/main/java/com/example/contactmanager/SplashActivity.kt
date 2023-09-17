package com.example.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        firebaseAuth= FirebaseAuth.getInstance()
        Handler(Looper.getMainLooper()).postDelayed({
            if (firebaseAuth.currentUser != null){
                Toast.makeText(this, "User already Logged In", Toast.LENGTH_SHORT).show()
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent= Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}