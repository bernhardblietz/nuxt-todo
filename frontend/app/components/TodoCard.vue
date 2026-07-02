<script setup lang="ts">
import { todoItems } from './TodoList.vue';

    const props = defineProps<{
        id: number;
        text: string;
        completed: boolean;
    }>();
</script>

<template>
    <div class="shadow-md bg-blue-100 rounded-lg p-4 flex flex-row justify-between items-center mb-4">
        <p class="text-center">{{ props.id }}</p>
        <p class="text-center">{{ props.text }}</p>
        <div class="flex flex-row gap-2">
            <UButton icon="i-lucide-trash" @click="todoItems.delete(props.id)"/>
            <UButton :icon="props.completed ? 'i-lucide-check' : 'i-lucide-square'" @click="todoItems.markCompleted(props.id)"/>
        </div>
    </div>
</template>