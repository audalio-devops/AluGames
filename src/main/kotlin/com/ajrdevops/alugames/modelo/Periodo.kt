package com.ajrdevops.alugames.modelo

import java.time.LocalDate
import java.time.Period

data class Periodo(
    val dataInicial: LocalDate,
    val dataFinal: LocalDate,
    val emDias: Int = Period.between(dataInicial, dataFinal).days
)

