package com.example.kotlintechinterview.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintechinterview.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("AAA", "Activity created")

        val dataTextView = findViewById<MaterialTextView>(R.id.dataTextView)
        val dataEditView = findViewById<TextInputEditText>(R.id.dataEditView)
        val saveButton = findViewById<MaterialButton>(R.id.saveButton)
        val getButton = findViewById<MaterialButton>(R.id.getButton)

        vm.resultLive.observe(this) {
            dataTextView.text = it
        }

        saveButton.setOnClickListener{

            val text = dataEditView.text.toString().trim()
            vm.save(text)
        }

        getButton.setOnClickListener{
            vm.load()
        }
    }
}