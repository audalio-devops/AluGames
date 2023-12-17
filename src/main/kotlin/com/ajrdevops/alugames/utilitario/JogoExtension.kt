import com.ajrdevops.alugames.modelo.InfoJogoJson
import com.ajrdevops.alugames.modelo.Jogo

fun InfoJogoJson.criaJogo(): Jogo {
    return Jogo(this.titulo, this.capa, this.preco.toBigDecimal(), this.descricao)
}
