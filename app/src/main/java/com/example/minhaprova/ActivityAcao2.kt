package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.example.minhaprova.databinding.ActivityAcao2Binding

class ActivityAcao2 : AppCompatActivity() {
    lateinit var binding: ActivityAcao2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)
        binding.apply{
            btnConfirma.setOnClickListener {
                var nome = binding.editTextNome.text
                var autor = binding.editTextAutor.text
                var ano = binding.editTextAno.text
                var nota = binding.ratingBar.rating
                var livro = Livro(0, nome.toString(), autor.toString(), ano.toString().toInt(), nota)
                var database = LivroDBOpener(this@ActivityAcao2)
                database.insertLivro(livro)
                val intent = Intent()
                intent.putExtra("valor2",nome.toString())
                intent.putExtra("result","Cadastrado")
                setResult(Activity.RESULT_OK, intent)

                finish()
            }
            btnCancelar.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }


    }
}