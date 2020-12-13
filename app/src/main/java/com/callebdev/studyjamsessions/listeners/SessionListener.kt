package com.callebdev.studyjamsessions.listeners

import com.callebdev.studyjamsessions.models.Session

interface SessionListener {
    fun onSessionClicked(session: Session)
    fun onDeleteSession(session: Session)
    fun onEditSession(session: Session)
}