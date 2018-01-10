package com.example.demo.view

import tornadofx.*

class MainView : View("Hello TornadoFX") {
    override val root = borderpane {
        top(Header::class)
        center(TodoList::class)
        bottom(Footer::class)
    }
}