package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.Store
import tornadofx.*


class Header : View() {
    val store: Store by inject()
    val allDone = booleanBinding(store.todos.items) { all { it.completed } }

    override val root = vbox {
        addClass(Styles.header)
        label("todos").setId(Styles.title)
        hbox {
            addClass(Styles.addItemRoot)
            checkbox {
                addClass(Styles.mainCheckBox)
                visibleWhen { booleanBinding(store.todos) { isNotEmpty() } }
                action { store.toggleCompleted(isSelected) }
                allDone.onChange { isSelected = it }
            }
            textfield {
                promptText = "What needs to be done?"
                action {
                    store.addTodo(text)
                    clear()
                }
            }
        }
    }
}
