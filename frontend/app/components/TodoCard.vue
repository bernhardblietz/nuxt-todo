<script setup lang="ts">
    const props = defineProps<{
        todoItem: TodoItem;
    }>();

    const todoItems = inject('todoItems') as {
        toggleCompleted: (id: number) => void;
        delete: (id: number) => void;
    };

</script>

<template>
    <div class="shadow-md bg-blue-100 rounded-lg p-4 flex flex-row justify-between items-center mb-4">
        <p class="text-center">{{ props.todoItem.id }}</p>
        <p class="text-center">{{ props.todoItem.text }}</p>
        <div class="flex flex-row gap-2">
            <UButton icon="i-lucide-trash" @click="todoItems.delete(props.todoItem.id)"/>
            <UButton :icon="props.todoItem.completed ? 'i-lucide-check' : 'i-lucide-square'" @click="todoItems.toggleCompleted(props.todoItem.id)"/>
        </div>
    </div>
</template>