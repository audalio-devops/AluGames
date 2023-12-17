package com.ajrdevops.alugames.modelo

import java.math.BigDecimal

//sealed : classe abstract que as herdeiras tem que estar no mesmo pacote
sealed class Plano(val tipo:String) {
    open fun obterValor(aluguel: Aluguel):BigDecimal {
        return aluguel.jogo.preco * aluguel.periodo.emDias.toBigDecimal()
    }
}