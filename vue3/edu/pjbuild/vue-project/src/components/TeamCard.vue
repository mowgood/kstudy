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
    import { useWeatherStore } from '@/stores/weatherstore'

    const p = defineProps( {
        name : String,
        food : {
            type: String,
            default: "떡볶이"            
        },
        srcUrl: String,
        teamNum: {
            type: Number,
            default: 5
        },
        idx: Number,
    });
    
    const emit = defineEmits(["emitData"]);

    const store = useWeatherStore();

    store.getWeather();
    
    function handleClick(idx) {
        alert(`${this.p.teamNum}팀 입니다 ~~~!! 오늘의 날씨는 ${store.time}입니다.`);
        emit('emitData', idx);
    }
</script>

