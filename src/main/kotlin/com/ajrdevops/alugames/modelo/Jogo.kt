package com.ajrdevops.alugames.modelo

import com.google.gson.annotations.Expose
import formatoComDuasCasasDecimais
import java.math.BigDecimal

data class Jogo(
    @Expose val titulo:String?,
    @Expose val capa:String?
): Recomendavel
{
    var descricao: String? = null
    var preco = BigDecimal("0.0")
    private val listaNotas = mutableListOf<Int>()

    override val media: Double
        get() = listaNotas.average().formatoComDuasCasasDecimais()

    override fun recomendar(nota: Int) {
        if (nota < 1 || nota > 10) {
            println("Nota inválida ($nota). Insira uma nota entre 1 e 10")
        } else {
            listaNotas.add(nota)
        }
    }

    constructor(titulo: String, capa: String, preco: BigDecimal, descricao: String):
            this(titulo, capa) {
        this.preco = preco
        this.descricao = descricao
    }

    override fun toString(): String {
        return  "\nJogo: \n" +
                "Titulo.......= $titulo \n" +
                "Capa.........= $capa \n" +
                "Descricao....= $descricao \n" +
                "Preco........= $preco \n" +
                "Recomendacao.= $media \n"
    }

}