package com.callebdev.studyjamsessions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.callebdev.studyjamsessions.adapters.SessionAdapter
import com.callebdev.studyjamsessions.listeners.SessionListener
import com.callebdev.studyjamsessions.models.Session
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Source

class MainActivity : AppCompatActivity(), SessionListener {

    private val TAG = "MainActivity"
    private lateinit var recyclerViewSessions: RecyclerView
    private lateinit var swipeRefreshSessions: SwipeRefreshLayout
    private lateinit var layoutSemSessoes: ConstraintLayout
    private lateinit var sessionAdapter: SessionAdapter
    private lateinit var firestoreDB: FirebaseFirestore
    private lateinit var fabCriarSession: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewSessions = findViewById(R.id.recyclerSessions)
        swipeRefreshSessions = findViewById(R.id.swipeRefreshSessions)
        layoutSemSessoes = findViewById(R.id.layoutNenhumaSession)
        fabCriarSession = findViewById(R.id.fabCriarSession)

        firestoreDB = FirebaseFirestore.getInstance()

        swipeRefreshSessions.setOnRefreshListener {
            buscarSessions()
        }

        fabCriarSession.setOnClickListener {
            val intent = Intent(this, NovaSessionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buscarSessions() {

        swipeRefreshSessions.isRefreshing = true

        firestoreDB.collection("sessions").get()
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val sessionList = mutableListOf<Session>()

                    for (doc in task.result!!) {
                        val session = doc.toObject<Session>(Session::class.java)
                        session.id = doc.id
                        sessionList.add(session)
                    }

                    sessionAdapter = SessionAdapter(sessionList, this)

                    recyclerViewSessions.layoutManager = LinearLayoutManager(applicationContext)
                    recyclerViewSessions.itemAnimator = DefaultItemAnimator()
                    recyclerViewSessions.adapter = sessionAdapter

                    if (sessionList.isNullOrEmpty()) {
                        layoutSemSessoes.visibility = View.VISIBLE
                    } else {
                        layoutSemSessoes.visibility = View.GONE
                    }

                    swipeRefreshSessions.isRefreshing = false

                } else {
                    Log.d(TAG, "Error getting documents", task.exception)
                    layoutSemSessoes.visibility = View.VISIBLE
                    swipeRefreshSessions.isRefreshing = false
                }
            }
    }

    override fun onSessionClicked(session: Session) {
        val intent = Intent(this, DetalhesDaSession::class.java)
        intent.putExtra("session", session)
        startActivity(intent)
    }

    override fun onDeleteSession(session: Session) {
        firestoreDB.collection("sessions")
            .document(session.id)
            .delete()
            .addOnCompleteListener {
                sessionAdapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "Sessao Apagada com Sucesso", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    // T.P.C
    override fun onEditSession(session: Session) {
        TODO("Not yet implemented")
    }

}