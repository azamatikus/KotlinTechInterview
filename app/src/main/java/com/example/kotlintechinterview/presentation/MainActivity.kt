package com.example.kotlintechinterview.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintechinterview.R
import com.example.kotlintechinterview.data.repository.UserRepositoryImpl
import com.example.kotlintechinterview.domain.useCase.GetUserNameUseCase
import com.example.kotlintechinterview.domain.useCase.SaveUserNameUseCase
import com.example.kotlintechinterview.domain.useCase.models.SaveUserNameParam
import com.example.kotlintechinterview.domain.useCase.models.UserName
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userRepository by lazy (LazyThreadSafetyMode.NONE) { UserRepositoryImpl(context = applicationContext) }
        val getUserNameUseCase by lazy (LazyThreadSafetyMode.NONE) {GetUserNameUseCase(userRepository)}
        val saveUserNameUseCase by lazy (LazyThreadSafetyMode.NONE) {SaveUserNameUseCase(userRepository)}

        val dataTextView = findViewById<MaterialTextView>(R.id.dataTextView)
        val dataEditView = findViewById<TextInputEditText>(R.id.dataEditView)
        val saveButton = findViewById<MaterialButton>(R.id.saveButton)
        val getButton = findViewById<MaterialButton>(R.id.getButton)

        saveButton.setOnClickListener(){

            val text = dataEditView.text.toString().trim()
            val params = SaveUserNameParam(name = text)
            val result : Boolean = saveUserNameUseCase.execute(param = params)
            dataTextView.text = "Save result = $result"
        }

        getButton.setOnClickListener(){

            val userName : UserName = getUserNameUseCase.execute()
            dataTextView.text = "${userName.firstName} ${userName.lastName}"
        }
    }
}