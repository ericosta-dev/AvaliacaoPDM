package com.example.minhaprova

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.minhaprova.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ViewModelTexto
    val PREFS = "prefs_file"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val settings = getSharedPreferences(PREFS,Context.MODE_PRIVATE)
        val bemVindo = settings.getBoolean("Bem vindo", true)
        var editor = settings.edit()

        if (bemVindo) {
            Toast.makeText(this, "Bem-vindo", Toast.LENGTH_SHORT).show()
            editor.putBoolean("Bem vindo", false).apply()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ViewModelTexto::class.java)

        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.apply {
            button1.setOnClickListener {
                var intent = Intent(this@MainActivity,ActivityAcao1::class.java)
                startActivityForResult(intent,1)
            }
            button2.setOnClickListener {
                var dialog = DialogXicara()
                dialog.show(supportFragmentManager,"Dialog")
            }
            button3.setOnClickListener {
                val intent = Intent(this@MainActivity, ActivityAcao2::class.java)
                startActivityForResult(intent, 3)
            }

            button4.setOnClickListener {
                val intent = Intent(this@MainActivity, ActivityAcao3::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

            when (requestCode){
                1->{
                    when(resultCode){
                        RESULT_OK->{
                            binding.apply {
                                text1.text = data?.getStringExtra("valor").toString()
                                viewModel.texto1 = data?.getStringExtra("valor").toString()
                                invalidateAll()
                            }
                        }
                        RESULT_CANCELED -> {
                            Snackbar.make(binding.button1,"Cancelado!",Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
                3->{
                    when(resultCode){
                        RESULT_OK->{
                            binding.apply {
                                text2.text = data?.getStringExtra("valor2").toString()
                                viewModel.texto2 = data?.getStringExtra("valor2").toString()
                                invalidateAll()
                            }
                        }
                    }
                }
            }

    }

    override fun onStop() {
        super.onStop()
        val settings = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        var editor = settings.edit()
        editor.putString("texto",binding.text1.text.toString())
        editor.putBoolean("salvar", true)
        editor.commit()

    }


}