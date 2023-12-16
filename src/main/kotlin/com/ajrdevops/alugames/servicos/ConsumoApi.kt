package com.ajrdevops.alugames.servicos

import com.ajrdevops.alugames.modelo.Gamer
import com.ajrdevops.alugames.modelo.InfoGamerJson
import com.ajrdevops.alugames.modelo.InfoJogo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import criaGamer
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {

    private fun consumoDados (endereco:String):String {

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }

    fun buscaJogo(id: String): InfoJogo? {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val json = consumoDados(endereco)
        val gson = Gson()
        var meuInfoJogo: InfoJogo? = null

        val resultadoIJ = runCatching {
            meuInfoJogo =  gson.fromJson(
                json,
                InfoJogo::class.java
            )
        }
        resultadoIJ.onFailure{
            return null
        }
        return (meuInfoJogo as InfoJogo?)!!
    }

    fun buscaGamers(): List<Gamer> {
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

        val json = consumoDados(endereco)
        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>(){}.type
        val listaGamers:List<InfoGamerJson> =  gson.fromJson(
            json,
            meuGamerTipo
        )

        val listaGamersConvertida = listaGamers.map { infoGamerJson -> infoGamerJson.criaGamer() }

        return (listaGamersConvertida)

    }

}