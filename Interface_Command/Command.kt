package com.example.ilamakarov.wave2.Interface_Command


class CommandDoException: Error("Can't do command")

interface ICommand {
    fun execute()
    fun undo()
}