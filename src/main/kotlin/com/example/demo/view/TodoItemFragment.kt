package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.Store
import com.example.demo.model.TodoItem
import com.example.demo.model.TodoItemModel
import javafx.scene.layout.Priority
import tornadofx.*

class TodoItemFragment : ListCellFragment<TodoItem>() {
    val store: Store by inject()
    val todo = TodoItemModel(itemProperty)

    override val root = hbox {
        // Enable if you want ellipsis instead of text overflow
//        cellProperty.onChange { cell ->
//            if (cell != null)
//                maxWidthProperty().cleanBind(cell.widthProperty().minus(15))
//        }
        addClass(Styles.itemRoot)
        checkbox(property = todo.completed) {
            action {
                startEdit()
                commitEdit(item)
            }
        }
        label(todo.text) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
            toggleClass(Styles.strikethrough, todo.completed)
        }
        textfield(todo.text) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action { commitEdit(item) }
        }
        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { store.removeTodo(item) }
        }
    }

}
