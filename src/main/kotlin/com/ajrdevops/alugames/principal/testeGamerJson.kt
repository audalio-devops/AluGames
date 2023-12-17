import com.ajrdevops.alugames.modelo.Periodo
import com.ajrdevops.alugames.modelo.PlanoAssinatura
import com.ajrdevops.alugames.servicos.ConsumoApi
import com.google.gson.GsonBuilder
import java.io.File
import java.math.BigDecimal
import java.time.LocalDate

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamers()
    val listaJogoJson = consumo.buscaJogosJson()

 //   println(listaGamers)
 //   println(jogoApi)

    val gamerCaroline = listaGamers.get(3)
    val jogoResidentVillage = listaJogoJson.get(10)
    val jogoSpider = listaJogoJson.get(13)
    val jogoTheLastOfUs = listaJogoJson.get(2)
    val jogoDandara = listaJogoJson.get(5)
    val jogoAssassins = listaJogoJson.get(4)
    val jogoCyber = listaJogoJson.get(6)
    val jogoGod = listaJogoJson.get(7)
    val jogoSkyrim = listaJogoJson.get(18)


    //println(gamerCaroline)
    //println(jogoResidentVillage)

    val periodo1 = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))
    val periodo2 = Periodo(LocalDate.now(), LocalDate.now().plusDays(3))
    val periodo3 = Periodo(LocalDate.now(), LocalDate.now().plusDays(10))
    val periodo4 = Periodo(LocalDate.of(2023,11,2), LocalDate.of(2023,11,15))

    gamerCaroline.alugaJogo(jogoResidentVillage, periodo1)
    gamerCaroline.alugaJogo(jogoSpider, periodo2)
    gamerCaroline.alugaJogo(jogoTheLastOfUs, periodo3)
    gamerCaroline.alugaJogo(jogoSpider, periodo4)

    //println(gamerCaroline.jogosAlugados)
    //println(gamerCaroline.jogosDoMes(12))

    val gamerCamila = listaGamers.get(5)
    gamerCamila.plano = PlanoAssinatura("PRATA", 9.9, 3, BigDecimal("0.15"))
    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)
    gamerCamila.alugaJogo(jogoSpider, periodo2)
    gamerCamila.alugaJogo(jogoTheLastOfUs, periodo3)
    gamerCamila.alugaJogo(jogoSpider, periodo2)
    // println(gamerCamila.jogosAlugados)

    gamerCamila.recomendar(10)
    gamerCamila.recomendar(-6)
    gamerCamila.recomendar(7)
    gamerCamila.recomendar(10)
    println(gamerCamila)

    gamerCamila.alugaJogo(jogoTheLastOfUs, periodo3)
    // println(gamerCamila.jogosAlugados)

    gamerCamila.recomendarJogo(jogoResidentVillage,7)
    gamerCamila.recomendarJogo(jogoSpider, 10)

    gamerCamila.recomendarJogo(jogoResidentVillage, 7)
    gamerCamila.recomendarJogo(jogoTheLastOfUs, 10)
    gamerCamila.recomendarJogo(jogoAssassins, 8)
    gamerCamila.recomendarJogo(jogoCyber, 7)
    gamerCamila.recomendarJogo(jogoGod, 10)
    gamerCamila.recomendarJogo(jogoDandara, 8)
    gamerCamila.recomendarJogo(jogoSkyrim, 8)
    gamerCamila.recomendarJogo(jogoSpider, 6)

    println("\n Jogos Camila")
    println(gamerCamila.jogosRecomendados)
//    println("\n Jogos Caroline")
//    println(gamerCaroline.jogosRecomendados)

    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val serializacao = gson.toJson(gamerCamila.jogosRecomendados)
    println(serializacao)
    val arquivo = File("JogosRecomendados-${gamerCamila.nome}.json")
    arquivo.writeText(serializacao)
    println(arquivo.absolutePath)

}