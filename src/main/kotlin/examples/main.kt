import com.github.kotlin.everywhere.browser.Attribute.Companion.onClick
import com.github.kotlin.everywhere.browser.Cmd
import com.github.kotlin.everywhere.browser.Html
import com.github.kotlin.everywhere.browser.Html.Companion.button
import com.github.kotlin.everywhere.browser.Html.Companion.div
import com.github.kotlin.everywhere.browser.Html.Companion.text
import com.github.kotlin.everywhere.browser.runProgram
import kotlin.browser.window

private data class Model(val count: Int = 0)

private sealed class Msg
private object Increment : Msg()
private object Decrement : Msg()

private val update: (Msg, Model) -> Pair<Model, Cmd<Msg>> = { msg, model ->
    val newModel = when (msg) {
        Increment -> model.copy(count = model.count + 1)
        Decrement -> model.copy(count = model.count - 1)
    }
    newModel to Cmd.none()
}

private val view: (Model) -> Html<Msg> = { (count) ->
    div(listOf(), listOf(
            text("$count"),
            button(listOf(onClick(Increment)), listOf(text("+"))),
            button(listOf(onClick(Decrement)), listOf(text("-")))
    ))
}

fun main(args: Array<String>) {
    runProgram(window.document.getElementById("app")!!, Model(), update, view)
}