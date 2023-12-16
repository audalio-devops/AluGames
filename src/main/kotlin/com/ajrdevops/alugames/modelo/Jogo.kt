package com.ajrdevops.alugames.modelo

data class Jogo(
    val titulo:String?,
    val capa:String?
)
{
    var descricao  = ""
    override fun toString(): String {
        return  "\nJogo: \n" +
                "Titulo.....= $titulo \n" +
                "Capa.......= $capa \n" +
                "Descricao..= $descricao \n"
    }

}