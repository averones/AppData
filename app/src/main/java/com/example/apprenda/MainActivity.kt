package com.example.apprenda

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.*
import com.example.apprenda.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var selectedDataSaida: String
    lateinit var selectedDataEntrada: String
    var cal = Calendar.getInstance()
    var saldo = mutableMapOf<String, Double>(
        "Habitação" to 0.0,
        "Alimentação" to 0.0,
        "Saúde e bem estar" to 0.0,
        "Lazer" to 0.0,
        "Transporte e Viagens" to 0.0,
        "Veículo" to 0.0,
        "Investimentos" to 0.0,
        "Outros gastos" to 0.0,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //val intent = getIntent()
        val formattedsaldo = intent.getStringExtra("SaldoTotal")

        binding.saldoresult.text = getString(R.string.saldofinal,formattedsaldo)
        //val sharedPreferences = getSharedPreferences("SP_INFO",Context.MODE_PRIVATE)


        val categoria_entrada = resources.getStringArray(R.array.entradas_array)
        val spinner = findViewById<Spinner>(R.id.receb_spinner)
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,categoria_entrada)

        val categoria_saida = resources.getStringArray(R.array.saidas_array)
        val spinner2 = findViewById<Spinner>(R.id.saida_spinner)
        spinner2.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,categoria_saida)


        var selected_category_ent = categoria_entrada[spinner.selectedItemPosition]
        var selected_category_said = categoria_saida[spinner2.selectedItemPosition]


        val dateSetListener = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                binding.textView26.text = sdf.format(cal.time)
                selectedDataEntrada = sdf.format(cal.time)
            }

        }
        binding.textView26.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })


        val dateSetListener2 = object: DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
                binding.textView31.text = sdf.format(cal.time)
                selectedDataSaida = binding.textView31.text.toString()
            }

        }
        binding.textView31.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View) {
                DatePickerDialog(this@MainActivity,dateSetListener2,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })




        binding.buttonReceb.setOnClickListener{
            val stringInTextField = binding.textView20.text.toString()
            val entrada = stringInTextField.toDouble()
            saldo_total += entrada
            val formattedsaldo = NumberFormat.getCurrencyInstance().format(saldo_total)
            val formattedentrada = NumberFormat.getCurrencyInstance().format(entrada)
            extrato.add("$formattedentrada ${selected_category_ent.padStart(30,' ')}\n$selectedDataEntrada")

            if(saldo_total>0){
                binding.saldoresult.text = getString(R.string.saldofinal,formattedsaldo)
                binding.saldoresultneg.text = ""
            } else {
                binding.saldoresult.text = ""
                binding.saldoresultneg.text = getString(R.string.saldofinal,formattedsaldo)
            }

            binding.textView20.text.clear()
            Toast.makeText(this@MainActivity, "Entrada Adicionada", Toast.LENGTH_SHORT).show()



           // val editor = sharedPreferences.edit()
            //editor.putString("Saldo formatado",formattedsaldo)
            //editor.apply()
        }
        binding.buttonReceb2.setOnClickListener{
            val stringInTextField2= binding.textView27.text.toString()
            val saida = stringInTextField2.toDouble()
            saldo[selected_category_said] = saida
            saldo_total -= saldo.map{it.value}.sum()
            val formattedsaldo = NumberFormat.getCurrencyInstance().format(saldo_total)
            val formattedsaida = NumberFormat.getCurrencyInstance().format(saida)
            extrato.add("-$formattedsaida ${selected_category_said.padStart(30,' ')}\n$selectedDataSaida")

            if(saldo_total>0){
                binding.saldoresult.text = getString(R.string.saldofinal,formattedsaldo)
                binding.saldoresultneg.text = ""
            } else {
                binding.saldoresult.text = ""
                binding.saldoresultneg.text = getString(R.string.saldofinal,formattedsaldo)
            }

            binding.textView27.text.clear()
            Toast.makeText(this@MainActivity, "Pagamento Adicionado", Toast.LENGTH_SHORT).show()
           // val editor = sharedPreferences.edit()
           // editor.putString("Saldo formatado",formattedsaldo)
           // editor.apply()
        }

        val actionBar = supportActionBar
        actionBar!!.title = "Lançamentos"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateDateInView(){

    }

}