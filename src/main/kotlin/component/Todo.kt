package component

import csstype.ClassName
import npm_packages.RiCloseCircleLine
import npm_packages.TiEdit
//import npm_packages.TiEdit
import react.FC
import react.Props
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.key
import react.useState

external interface TodoProps : Props {
    var todos: List<TodoModel>
    var completeTodo: (Int) -> Unit
    var removeTodo: (Int) -> Unit
    var updateTodo: (id: Int, text: String?) -> Unit
}

val Todo = FC<TodoProps> { props ->
    val (edit, setEdit) = useState(TodoModel(id = null))

    val submitUpdate: (TodoModel) -> Unit = {
        props.updateTodo(edit.id!!, it.value)
        setEdit(TodoModel(null, ""))
    }

    if (edit.id != null)
        return@FC TodoForm {
            this.edit = edit
            onSubmit = submitUpdate
        }

    return@FC props.todos.forEachIndexed { index, item ->
        console.log("$index $item is here")
        div {
            className = if (item.completed) ClassName("todo-row complete")
            else ClassName("todo-row")
            key = index.toString()

            div {
                className = ClassName("text-container")
                key = item.id.toString()
                onClick = { props.completeTodo(item.id!!) }

                p {
                    +item.value
                }
            }

            div {
                className = ClassName("icons")
                a {
                    className = ClassName("delete-icon")
                    onClick = { props.removeTodo(item.id!!) }
                    RiCloseCircleLine { size = 30 }
                }
                a {
                    className = ClassName("edit-icon")
                    onClick = { setEdit(item) }
                    TiEdit { size = 30 }
                }
            }
        }
    }
}

data class TodoModel(
    var id: Int? = count++,
    var value: String = count.toString(),
    var completed: Boolean = false
) {
    private companion object {
        var count: Int = 0
    }
}