import com.ajrdevops.alugames.servicos.ConsumoApi

fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaGamers()
    val jogoApi = consumo.buscaJogo("612")

    println(listaGamers)
    println(jogoApi)
}