package com.Ideal.fastway

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_display_signed.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class DisplaySigned : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_signed)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("DisplaySigned")

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        loadProfile()
    }

    private fun loadProfile() {
        val user = auth.currentUser
        val  userreference = databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                firstn.text=snapshot.child("firstn").value.toString()
                lsname.text=snapshot.child("lsname").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@DisplaySigned,MainActivity::class.java))

        }
    }

    fun addItem(view: View) {
        val index = Random.nextInt(8)
        val newItem = ExampleItem(
            R.drawable.ic_file,
            "New item at position $index",
            "Line 2"
        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun deleteItem(view: View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_file
                1 -> R.drawable.ic_dom
                else -> R.drawable.ic_pzz
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}
