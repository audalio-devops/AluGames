package com.ajrdevops.alugames.modelo

//sealed : classe abstract que as herdeiras tem que estar no mesmo pacote
sealed class Plano(val tipo:String) {
    open fun obterValor(aluguel: Aluguel):Double {
        return aluguel.jogo.preco * aluguel.periodo.emDias
    }
}