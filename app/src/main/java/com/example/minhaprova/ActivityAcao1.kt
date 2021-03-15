package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.minhaprova.databinding.ActivityAcao1Binding
import com.example.minhaprova.databinding.ActivityMainBinding

class ActivityAcao1 : AppCompatActivity() {
    lateinit var binding: ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)

        binding.apply {
            btnOk.setOnClickListener {
                    val intent = Intent()
                    intent.putExtra("valor", edtValor.text.toString())
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