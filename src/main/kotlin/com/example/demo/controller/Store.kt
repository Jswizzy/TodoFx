package com.example.demo.controller

import com.example.demo.model.FilterState
import com.example.demo.model.FilterState.*
import com.example.demo.model.TodoItem
import tornadofx.*


class Store : Controller() {
    val todos = SortedFilteredList<TodoItem>()

    fun addTodo(text: String) = todos.add(TodoItem(text))

    fun removeTodo(todo: TodoItem) = todos.remove(todo)

    fun toggleCompleted(completed: Boolean) = with(todos) {
        filter { it.completed != completed }.forEach { it.completed = completed }
        invalidate()
    }

    fun filterBy(state: FilterState) = when (state) {
        Completed -> todos.predicate = { it.completed }
        Active -> todos.predicate = { !it.completed }
        else -> todos.predicate = { true }
    }
}