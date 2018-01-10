package com.example.demo.view

import com.example.demo.controller.Store
import tornadofx.*

class TodoList : View() {
    val store: Store by inject()

    override val root = listview(store.todos) {
        isEditable = true
        cellFragment(TodoItemFragment::class)
    }

}
