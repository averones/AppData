package com.example.apprenda

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import java.text.NumberFormat

public var saldo_total = 0.0
var extrato: ArrayList<String> = ArrayList()

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val formattedsaldo = NumberFormat.getCurrencyInstance().format(saldo_total)

        if(saldo_total>=0){
            findViewById<TextView>(R.id.saldo_menu).text = formattedsaldo
            findViewById<TextView>(R.id.saldo_menu_neg).text = ""
        } else {
            findViewById<TextView>(R.id.saldo_menu).text = ""
            findViewById<TextView>(R.id.saldo_menu_neg).text = formattedsaldo
        }


        findViewById<ImageButton>(R.id.Lancabtn).setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("SaldoTotal",formattedsaldo)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.extratobtn).setOnClickListener{
            val intent = Intent(this,Extrato::class.java)
            intent.putExtra("SaldoTotal",formattedsaldo)
            intent.putExtra("Extrato",extrato)
            startActivity(intent)
        }

        val adapter = ArrayAdapter(this,
            R.layout.listview_item, extrato)

        val listView: ListView = findViewById(R.id.listview_1)
        listView.setAdapter(adapter)

        //val tv_dynamic = TextView(this)
        //tv_dynamic.textSize = 16f
        //tv_dynamic.text = extrato[0]
        //findViewById<LinearLayout>(R.id.extratolayout).addView(tv_dynamic)



        //val editor = getSharedPreferences("editorid",0)
        //findViewById<TextView>(R.id.saldo_menu).text = editor.getString("Saldo formatado","")

    }

    /*private fun saveData() {
        val finalsaldo = findViewById<TextView>(R.id.saldo_menu).text.toString()
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY",finalsaldo)
        }.apply()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY","0.00")

        findViewById<TextView>(R.id.saldo_menu).text = savedString
    }*/
}