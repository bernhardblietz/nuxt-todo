<script lang="ts">

    type TodoItem = {
        id: number;
        text: string;
        completed: boolean;
    };

    export const todoItems = reactive({
        todos: [] as TodoItem[],
        add(text : string) {
            const lastIndex = this.todos.length - 1;
            const newId = (this.todos[lastIndex]?.id ?? 0) + 1 ;
            this.todos.push({ id: newId, text: text, completed: false });
        },
        markCompleted(id: number) {
            const item = this.todos.find((todo) => todo.id === id);
            if (item) {
                item.completed = !item.completed;
            }
        },
        delete(id: number) {
            this.todos = this.todos.filter((todo) => todo.id !== id);
        },
    });
</script>

<script setup lang="ts">
    const newTodoText = ref('');
    const addMode = ref(false);
    todoItems.add('Buy groceries')
    todoItems.add('Walk the dog')
    todoItems.add('Read a book')
</script>

<template>
    <div class="place-self-center flex flex-col min-h-80 w-1/2 pt-4">
        <TodoCard v-for="item in todoItems.todos" :key="item.id" :id="item.id" :text="item.text" :completed="item.completed" />
        <UButton v-if="!addMode" icon="i-lucide-plus" color="primary" variant="solid" @click="() => { addMode = true }"/>
        <div v-else class="flex flex-row gap-2 shadow-md bg-blue-100 rounded-lg p-4 items-center justify-between w-full">
            <UInput v-model="newTodoText" placeholder="Enter new todo" />
            <div class="flex flex-row gap-2">
                <UButton icon="i-lucide-check" color="primary" variant="solid" @click="() => { todoItems.add(newTodoText); newTodoText = ''; addMode = false; }"/>
                <UButton icon="i-lucide-x" color="error" variant="solid" @click="() => { newTodoText = ''; addMode = false; }"/>
            </div>
        </div>
    </div>
</template>