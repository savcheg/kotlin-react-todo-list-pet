import component.TodoList
import kotlinx.browser.document
import org.w3c.dom.css.CSS
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.div

val App = FC<Props> {
    div {
        TodoList()
    }
}

fun main() {
    kotlinext.js.require("App.css")
    document.title = "Todo-app"
    val container = document.createElement("div")
    document.body!!.appendChild(container)
    createRoot(container).render(App.create())
}
