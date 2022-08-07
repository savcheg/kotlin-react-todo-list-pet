import component.TodoList
import csstype.ClassName
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

val App = FC<Props> {
    div {
        className = ClassName("todo-app")
        TodoList()
    }
}
