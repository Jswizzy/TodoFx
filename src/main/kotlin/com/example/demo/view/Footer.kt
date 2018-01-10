package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controller.Store
import com.example.demo.model.FilterState
import tornadofx.*

class Footer : View() {
    val store: Store by inject()
    val itemsLeft = integerBinding(store.todos.items) { count { !it.completed } }

    override val root = hbox {
        addClass(Styles.footer)
        label(stringBinding(itemsLeft) { "$value item${value.plural} left" })
        hbox {
            togglegroup {
                for (state in FilterState.values())
                    togglebutton(state.name).whenSelected { store.filterBy(state) }
            }
        }
    }

    val Int.plural: String get() = if (this == 1) "" else "s"
}