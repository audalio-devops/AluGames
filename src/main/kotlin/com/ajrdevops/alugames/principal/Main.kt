package com.ajrdevops.alugames.principal

import com.ajrdevops.alugames.modelo.Gamer
import com.ajrdevops.alugames.modelo.Jogo
import com.ajrdevops.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*

fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluído com sucesso. Dados do gamer:")
    println(gamer)
    println("Idade do gamer: " + (gamer.dataNascimento?.transformarEmIdade()))

    do {
        println("Digite o codigo de jogo a ser pesquisado: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        if (informacaoJogo == null) {
            println("Id informado inexistente. Tente outro id.")
            System.exit(1)
        }

        var meuJogo: Jogo? = null
        val resultado = runCatching {
            meuJogo = Jogo(
                informacaoJogo!!.info.title,
                informacaoJogo.info.thumb
            )
        }

        resultado.onFailure{
            println("com.ajrdevops.alugames.modelo.Jogo inexistente. Tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descrição personalizada para o jogo:")
                val descricaoPersonalizada = leitura.nextLine();
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo.toString()
            }
        }
        gamer.jogosBuscados.add(meuJogo)
        println(meuJogo)

        println("Desejaria buscar um novo jogo ? (S/N) ")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por título:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }
    gamer.jogosBuscados.forEach {
        println("Titulo: " + it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("\n Jogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)) {
        println(gamer.jogosBuscados)
        println("\n Informe a posição do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)

        println("\n Lista atualizada:")
        println(gamer.jogosBuscados)

    }


    println("Busca finalizada com sucesso")
}