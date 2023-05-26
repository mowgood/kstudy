<template>
    <div class="card" style="width: 18rem;margin: auto;">
        <img v-bind:src="srcUrl" class="card-img-top" width="70" height="200" alt="...">
        <div class="card-body">
            <h5 class="card-title">{{ name }}</h5>
            <p class="card-text">{{ food }}</p>
            <button class="btn btn-primary" @click="handleClick(idx)">OK</button>
        </div>
    </div>
</template>
<script setup>
    import { defineProps, defineEmits } from 'vue'; 
    const p = defineProps( {
        name : String,
        food : {
            type: String,
            default: "떡볶이"            
        },
        srcUrl: String,
        teamNum : Number,
        idx: Number
    });
    
    const emit = defineEmits(["emitData"]);
    
    function handleClick(idx) {
        emit('emitData', idx);
    }
</script>

