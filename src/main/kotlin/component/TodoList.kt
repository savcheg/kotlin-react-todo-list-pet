package component

import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.useState

val TodoList = FC<Props> {
    var todoList by useState(mutableListOf<TodoModel>())

    val addTodo: (TodoModel) -> Unit = {
        if (it.value.isNotBlank()) {
            it.value = it.value.trim()
            todoList.add(it)
            todoList = todoList.toMutableList()
        }
    }

    val completeTodo: (Int) -> Unit = { id ->
        todoList = todoList.map { todo ->
            if (id == todo.id) todo.completed = todo.completed.not()
            return@map todo
        }.toMutableList()
    }

    val removeTodo: (Int) -> Unit = { id ->
        todoList = todoList.filter { it.id != id }.toMutableList()
    }

    val updateTodo: (Int, String?) -> Unit = { id, text ->
        if (!text.isNullOrBlank())
            todoList = todoList.map {
                if (it.id == id) it.value = text.trim()
                return@map it
            }.toMutableList()
    }


    return@FC div {
        h1 {
            +"plans for today"
        }
        TodoForm {
            onSubmit = addTodo
        }
        Todo {
            this.todos = todoList
            this.completeTodo = completeTodo
            this.removeTodo = removeTodo
            this.updateTodo = updateTodo
        }
    }
}
