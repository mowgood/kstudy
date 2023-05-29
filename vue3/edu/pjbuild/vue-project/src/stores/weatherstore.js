import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios'
export const useWeatherStore = defineStore('weather', () => {
  const time = ref("")
  function getWeather(data) {
    axios.get("http://localhost:8088/weather").then(function(response){time.value=response.data.time});
  }
  return { time, getWeather }
})