<script setup lang="ts">

    const todoItems = ref<TodoItem[]>([
        { id: 1, text: 'Buy groceries', completed: false },
        { id: 2, text: 'Walk the dog', completed: false },
        { id: 3, text: 'Read a book', completed: false },
    ]);

    function addTodo(text: string) {
        const lastIndex = todoItems.value.length - 1;
        const newId = (todoItems.value[lastIndex]?.id ?? 0) + 1;
        todoItems.value.push({ id: newId, text: text, completed: false });
    }

    function markCompleted(id: number) {
        const item = todoItems.value.find((todo) => todo.id === id);
        if (item) {
            item.completed = !item.completed;
        }
    }

    function deleteTodo(id: number) {
        todoItems.value = todoItems.value.filter((todo) => todo.id !== id);
    }

    provide('todoItems', {
        values: readonly(todoItems),
        add: addTodo,
        markCompleted: markCompleted,
        delete: deleteTodo,
    });

</script>

<template>
    <div class="place-self-center flex flex-col min-h-80 w-1/2 pt-4">
        <TodoCard v-for="item in todoItems" :key="item.id" :todo-item="item" />
        <TodoAddCard />
    </div>
</template>