package com.Ideal.fastway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    /** Called when the user taps the Sign Up button */
    fun signUp(view: View) {
        // Do something in response to button
        val intent = Intent(this, DisplaySignUp::class.java)
        startActivity(intent)
    }
    /** Called when the user taps the Sign In button */
    fun signIn(view: View) {
        // Do something in response to button
        val intent = Intent(this, DisplaySigned::class.java)
        startActivity(intent)
    }
}
