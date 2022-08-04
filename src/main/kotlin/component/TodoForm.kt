package component

import csstype.ClassName
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input

external interface TodoFormProps : Props {
    var edit: TodoModel?
    var onSubmit: (TodoModel) -> Unit
}

val TodoForm = FC<TodoFormProps> { props ->
    var input: String by useState(if (props.edit == null) "" else props.edit!!.value)

    return@FC form {
        className = ClassName("todo-form")
        onSubmit = {
            it.preventDefault()
            props.onSubmit(TodoModel(value = input))
            input = ""
        }
        input {
            className = ClassName("todo-input")
            type = InputType.text
            placeholder = "Add a todo"
            value = input
            onChange = {
                input = it.target.value
            }
        }
        button {
            className = ClassName("todo-button")
            +"Add todo"
        }
    }
}