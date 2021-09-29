package com.example.apprenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

class Extrato : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extrato)

        val adapter = ArrayAdapter(this,
            R.layout.listview_item, extrato)

        val listView: ListView = findViewById(R.id.list_view2)
        listView.setAdapter(adapter)

        val actionBar = supportActionBar
        actionBar!!.title = "Extrato"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}