package com.bernhardblietz.nuxt_todo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoController(private val todoRepository: TodoRepository) {

    @GetMapping
    fun getAllTodos(): List<Todo> {
        return todoRepository.findAll()
    }

    @PostMapping
    fun createTodo(@RequestBody request: TodoPostDTO): Todo {
        val todo = Todo(
        text = request.text,
        completed = request.completed ?: false
    )
    return todoRepository.save(todo)
    }

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody updatedTodo: TodoPutDTO): ResponseEntity<Todo> {
        try {
            val existingTodo = todoRepository.findById(id).orElseThrow()
            val newTodo: Todo = existingTodo.copy(
                text = updatedTodo.text ?: existingTodo.text,
                completed = updatedTodo.completed ?: existingTodo.completed
            )
            return ResponseEntity.ok(todoRepository.save(newTodo))
        }
        catch (e: Exception) {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }

}