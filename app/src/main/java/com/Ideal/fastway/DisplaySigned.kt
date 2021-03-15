package com.Ideal.fastway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_display_signed.*

class DisplaySigned : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_signed)

        val exampleList=generateDummyList(500)
        recycler_view.adapter=ExampleAdapter(exampleList)
        recycler_view.layoutManager= LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }
    private fun generateDummyList(size: Int): List<ExampleItem> {
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
