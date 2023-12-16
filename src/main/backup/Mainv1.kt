package com.ajrdevops.alugames.principal

import com.ajrdevops.alugames.modelo.InfoJogo
import com.ajrdevops.alugames.modelo.Jogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite o codigo de jogo a ser pesquisado: ")
    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()
    //println(json)

    /*
    val meuJogo = com.ajrdevops.alugames.modelo.Jogo(
        "Batman: Arkham Asylum Game of the Year Edition",
        "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587")

    val novoJogo = com.ajrdevops.alugames.modelo.Jogo(
        capa = "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587",
        titulo = "Batman: Arkham Asylum Game of the Year Edition"
        )
    println(novoJogo)
    */

    val gson = Gson()

//    try {
//        val meuInfoJogo = gson.fromJson(json, infoJogo::class.java)
//        val meuJogo = com.ajrdevops.alugames.modelo.Jogo(
//            meuInfoJogo.info.title,
//            meuInfoJogo.info.thumb)
//
//        println(meuJogo)
//    } catch (ex: JsonSyntaxException) {
//        println("Retorno vazio. Tente outro id.")
//    } catch (ex: NullPointerException) {
//        println("com.ajrdevops.alugames.modelo.Jogo Inexistente. Tente outro id.")
//    }

//    var meuInfoJogo:infoJogo? = null
//    try {
//        meuInfoJogo = gson.fromJson(json, infoJogo::class.java)
//    } catch (ex: JsonSyntaxException) {
//        println("Retorno vazio. Tente outro id.")
//    }

    var meuInfoJogo: InfoJogo? = null

    val resultadoIJ = runCatching {
        meuInfoJogo =  gson.fromJson(
            json,
            InfoJogo::class.java
        )
    }
    resultadoIJ.onFailure{
        println("Id informado inexistente. Tente outro id.")
        System.exit(1)
    }

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(
            meuInfoJogo!!.info.title,
            meuInfoJogo!!.info.thumb
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
    println(meuJogo)

}