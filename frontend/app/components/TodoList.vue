<script setup lang="ts">

    async function fetchTodos() {
        try {
            const response = await fetch('http://localhost:8080/api/todos');
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            todoItems.value = data;
        } catch (error) {
            console.error('Error fetching todos:', error);
        }
    }

    function addTodo(text: string) {
        fetch('http://localhost:8080/api/todos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ text }),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(newTodo => {
            todoItems.value.push(newTodo);
        })
        .catch(error => {
            console.error('Error adding todo:', error);
        });
    }

    function updateTodo(id: number, putDTO: TodoPutDTO) {
        fetch(`http://localhost:8080/api/todos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(putDTO),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(updatedTodo => {
            const index = todoItems.value.findIndex(todo => todo.id === id);
            if (index !== -1) {
                todoItems.value[index] = updatedTodo;
            }
        })
        .catch(error => {
            console.error('Error updating todo:', error);
        });
    }

    function deleteTodo(id: number) {
        fetch(`http://localhost:8080/api/todos/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            // Remove the todo from the local state
            todoItems.value = todoItems.value.filter(todo => todo.id !== id);
        })
        .catch(error => {
            console.error('Error deleting todo:', error);
        });
    }

    const todoItems = ref<TodoItem[]>([]);

    function toggleCompleted(id: number) {
        const existingTodo = todoItems.value.find(todo => todo.id === id);
        if (!existingTodo) {
            console.error(`Todo with id ${id} not found`);
            return;
        }
    updateTodo(id, { completed: !existingTodo.completed });
    }

    provide('todoItems', {
        values: readonly(todoItems),
        add: addTodo,
        toggleCompleted: toggleCompleted,
        delete: deleteTodo,
    });

    fetchTodos();

</script>

<template>
    <div class="place-self-center flex flex-col min-h-80 w-1/2 pt-4">
        <TodoCard v-for="item in todoItems" :key="item.id" :todo-item="item" />
        <TodoAddCard />
    </div>
</template>