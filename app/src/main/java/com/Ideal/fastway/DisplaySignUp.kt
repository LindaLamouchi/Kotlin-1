package com.Ideal.fastway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_display_sign_up.*
import kotlinx.android.synthetic.main.activity_display_signed.*

class DisplaySignUp : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference ?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_sign_up)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("DisplaySigned")
        register()
    }

    private fun register() {
        regbutton.setOnClickListener {
            if (TextUtils.isEmpty(fname.text.toString())) {
                fname.setError("Please enter your First name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(lname.text.toString())) {
                lname.setError("Please enter your Last name")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(mail.text.toString())) {
                mail.setError("Please enter your email")
                return@setOnClickListener
            } else if (TextUtils.isEmpty(pass.text.toString())) {
                pass.setError("Please enter your password")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(mail.text.toString(),
            pass.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                   val currentUser =  auth.currentUser
                   val currentUSerDb  = databaseReference?.child((currentUser?.uid!!))
                    currentUSerDb?.child("firstn")?.setValue(fname.text.toString())
                    currentUSerDb?.child("lsname")?.setValue(lname.text.toString())

                    Toast.makeText(this@DisplaySignUp,"Registration succeed" ,Toast.LENGTH_LONG).show()
                    finish()

                } else{
                    Toast.makeText(this@DisplaySignUp,"Registration failed" +
                            "please try again!",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
