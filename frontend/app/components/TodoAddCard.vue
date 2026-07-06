<script setup lang="ts">
    const newTodoText = ref('');
    const addMode = ref(false);

    const { add } = inject('todoItems') as {
        add: (text: string) => void;
    };

    function addTodo(text: string) {
        add(text);
        newTodoText.value = '';
        addMode.value = false;
    }

    function resetInput() {
        newTodoText.value = '';
        addMode.value = false;
    }

</script>


<template>
    <UButton v-if="!addMode" icon="i-lucide-plus" color="primary" variant="solid" @click="() => { addMode = true }"/>
    <div v-else class="flex flex-row gap-2 shadow-md bg-blue-100 rounded-lg p-4 items-center justify-between w-full">
        <UInput v-model="newTodoText" placeholder="Enter new todo" />
        <div class="flex flex-row gap-2">
            <UButton icon="i-lucide-check" color="primary" variant="solid" @click="addTodo(newTodoText)"/>
            <UButton icon="i-lucide-x" color="error" variant="solid" @click="resetInput"/>
        </div>
    </div>
</template>