import com.ajrdevops.alugames.modelo.Periodo
import com.ajrdevops.alugames.modelo.PlanoAssinatura
import com.ajrdevops.alugames.servicos.ConsumoApi
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
    val jogoLastOfUs = listaJogoJson.get(2)

    //println(gamerCaroline)
    //println(jogoResidentVillage)

    val periodo1 = Periodo(LocalDate.now(), LocalDate.now().plusDays(7))
    val periodo2 = Periodo(LocalDate.now(), LocalDate.now().plusDays(3))
    val periodo3 = Periodo(LocalDate.now(), LocalDate.now().plusDays(10))
    val periodo4 = Periodo(LocalDate.of(2023,11,2), LocalDate.of(2023,11,15))

    gamerCaroline.alugaJogo(jogoResidentVillage, periodo1)
    gamerCaroline.alugaJogo(jogoSpider, periodo2)
    gamerCaroline.alugaJogo(jogoLastOfUs, periodo3)
    gamerCaroline.alugaJogo(jogoSpider, periodo4)

    //println(gamerCaroline.jogosAlugados)
    //println(gamerCaroline.jogosDoMes(12))

    val gamerCamila = listaGamers.get(5)
    gamerCamila.plano = PlanoAssinatura("PRATA", 9.9, 3)
    gamerCamila.alugaJogo(jogoResidentVillage, periodo1)
    gamerCamila.alugaJogo(jogoSpider, periodo2)
    gamerCamila.alugaJogo(jogoLastOfUs, periodo3)
    gamerCamila.alugaJogo(jogoSpider, periodo3)
    println(gamerCamila.jogosAlugados)

}