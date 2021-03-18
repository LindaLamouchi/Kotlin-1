package com.Ideal.fastway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()
    signIn()
    }
    /** Called when the user taps the Sign Up button */
   fun signUp(view: View) {
        // Do something in response to button
        val intent = Intent(this, DisplaySignUp::class.java)
        startActivity(intent)
    }
    /** Called when the user taps the Sign In button */
     fun signIn() {
        // Do something in response to button
        /*val intent = Intent(this, DisplaySigned::class.java)
        startActivity(intent)*/
        sign.setOnClickListener{
            if(TextUtils.isEmpty(username.text.toString())){
                username.setError("Please enter username")
                return@setOnClickListener
            } else  if(TextUtils.isEmpty(passw.text.toString())){
                username.setError("Please enter your password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(username.text.toString(),passw.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, DisplaySigned::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@MainActivity,"login failed" , Toast.LENGTH_LONG).show()

                    }
                }

        }

    }
}
