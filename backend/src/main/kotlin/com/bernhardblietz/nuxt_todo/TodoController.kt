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
    fun createTodo(@RequestBody todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody updatedTodo: Todo): ResponseEntity<Todo> {
        if (!todoRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(todoRepository.save(updatedTodo.copy(id = id)))
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