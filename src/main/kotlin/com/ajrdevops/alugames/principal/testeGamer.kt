import com.ajrdevops.alugames.modelo.Gamer

fun main() {

    val gamer1 = Gamer("Audalio", "audalio@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Marcos",
        "marcos@email.com",
        "17/09/1954",
        "marcosPX"
    )
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "10/01/2001"
        it.usuario= "ajrdevolop"
    }.also {
        println(gamer1.idInterno)
    }
    println(gamer1)
    gamer1.usuario = "Junior"
    println(gamer1)
}