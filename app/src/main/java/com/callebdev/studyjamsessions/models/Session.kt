package com.callebdev.studyjamsessions.models

import java.io.Serializable

data class Session (
    var id: String = "",
    var titlulo: String = "",
    var descricao: String = "",
    var imagem: String = "",
    var link: String = "",
    var dia_semana: String = ""
): Serializable