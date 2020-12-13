package com.callebdev.studyjamsessions.controllers

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.callebdev.studyjamsessions.models.Session
import com.google.firebase.firestore.FirebaseFirestore

object SessionController {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun criarSession(context: Context, session: Session) {

        mFirestore.collection("sessions")
            .add(session)
            .addOnSuccessListener {
                // Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${it.id}")
                Toast.makeText(
                    context,
                    "Sessao criada com sucesso!",
                    Toast.LENGTH_LONG
                ).show()
            }
            .addOnFailureListener {
                // Log.w(ContentValues.TAG, "Error adding document", it)
                Toast.makeText(
                    context,
                    "Erro ao criar sessao",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

}