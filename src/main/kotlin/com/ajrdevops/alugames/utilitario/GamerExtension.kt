import com.ajrdevops.alugames.modelo.Gamer
import com.ajrdevops.alugames.modelo.InfoGamerJson

fun InfoGamerJson.criaGamer():Gamer {
    return  Gamer(this.nome, this.email, this.dataNascimento, this.usuario)
}