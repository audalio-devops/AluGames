package com.ajrdevops.alugames.modelo

import java.time.LocalDate
import java.time.Period

data class Aluguel(
    val gamer: Gamer,
    val jogo: Jogo,
    val periodo: Periodo
)
{
    val valorAluguel:Double = gamer.plano.obterValor(this);
    override fun toString(): String {
        return "\n Aluguel do ${jogo.titulo} por ${gamer.nome} pelo valor de R$ ${valorAluguel.toString()}"
    }
}