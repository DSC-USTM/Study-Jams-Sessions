package com.callebdev.studyjamsessions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.callebdev.studyjamsessions.models.Session

class DetalhesDaSession : AppCompatActivity() {

    private lateinit var sessionActual: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_da_session)

        sessionActual = intent.getSerializableExtra("session") as Session

        val imgSessionPerfil = findViewById<ImageView>(R.id.imgSessionPerfil)
        val txtTituloDetalhes = findViewById<TextView>(R.id.txtTituloDetalhes)
        val txtDiaDaSemanaDetalhes = findViewById<TextView>(R.id.txtDiaDaSemanaDetalhes)
        val txtDescricaoDetalhes = findViewById<TextView>(R.id.txtDescricaoDetalhes)
        val btnAcederAReuniao = findViewById<Button>(R.id.btnAcederAReuniao)

        Glide.with(this)
            .load(sessionActual.imagem)
            .placeholder(R.drawable.ic_sessions)
            .into(imgSessionPerfil)

        txtTituloDetalhes.text = sessionActual.titlulo
        txtDiaDaSemanaDetalhes.text = sessionActual.dia_semana
        txtDescricaoDetalhes.text = sessionActual.descricao

        btnAcederAReuniao.text = "ACEDER A REUNIAO: \n${sessionActual.link}"

        btnAcederAReuniao.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(sessionActual.link)
            startActivity(intent)
        }
    }
}