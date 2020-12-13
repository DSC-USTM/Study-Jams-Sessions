package com.callebdev.studyjamsessions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.callebdev.studyjamsessions.controllers.SessionController
import com.callebdev.studyjamsessions.models.Session
import com.google.android.material.textfield.TextInputEditText

class NovaSessionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_session)

        val inputTitulo = findViewById<TextInputEditText>(R.id.inputTitulo)
        val inputLink = findViewById<TextInputEditText>(R.id.inputLink)
        val inputImagem = findViewById<TextInputEditText>(R.id.inputImagem)
        val inputDescricao = findViewById<TextInputEditText>(R.id.inputDescricao)
        val btnGuardarSession = findViewById<Button>(R.id.btnGuardarSession)

        btnGuardarSession.setOnClickListener {
            val session = Session("", inputTitulo.text.toString(), inputDescricao.text.toString(), inputLink.text.toString(), inputLink.text.toString(), "Sabado")
            SessionController.criarSession(this, session)
        }
    }
}