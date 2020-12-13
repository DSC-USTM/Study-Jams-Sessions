package com.callebdev.studyjamsessions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.callebdev.studyjamsessions.R
import com.callebdev.studyjamsessions.listeners.SessionListener
import com.callebdev.studyjamsessions.models.Session
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SessionAdapter(
    private val sessionList: List<Session>,
    private val listener: SessionListener
) : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_session, parent, false)
        return SessionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bindView(sessionList[position], listener)
    }

    override fun getItemCount() = sessionList.size

    class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgSession = itemView.findViewById<ImageView>(R.id.imgSession)
        val txtSessionTitulo = itemView.findViewById<TextView>(R.id.txtSessionTitulo)
        val txtSessionLink = itemView.findViewById<TextView>(R.id.txtSessionLink)
        val txtSessionDiaDaSemana = itemView.findViewById<TextView>(R.id.txtSessionDiaDaSemana)
        val fabApagarSession = itemView.findViewById<FloatingActionButton>(R.id.fabApagarSession)
        val fabEditarSession = itemView.findViewById<FloatingActionButton>(R.id.fabEditarSession)

        fun bindView(session: Session, listener: SessionListener): Unit {

            Glide.with(itemView.context)
                .load(session.imagem)
                .placeholder(R.drawable.ic_sessions) // If there's an error loading
                .into(imgSession)
            txtSessionTitulo.text = session.titlulo
            txtSessionDiaDaSemana.text = session.dia_semana
            txtSessionLink.text = session.link

            itemView.setOnClickListener {
                listener.onSessionClicked(session)
            }

            fabApagarSession.setOnClickListener {
                listener.onDeleteSession(session)
            }

            fabEditarSession.setOnClickListener {
                listener.onEditSession(session)
            }
        }
    }
}