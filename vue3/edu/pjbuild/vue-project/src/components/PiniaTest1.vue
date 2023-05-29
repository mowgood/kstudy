<template>
        <!-- Access the state directly from the store -->
        <div>현재 값: {{ counter.count }}</div>
</template>

<script setup>
import { useCounterStore } from '@/stores/counter'

const counter = useCounterStore()
console.log(counter);
counter.count++
// with autocompletion ✨
counter.$patch({ count: counter.count + 1 })
// or using an action instead
counter.increment()
</script>

