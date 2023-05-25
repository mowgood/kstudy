<template>
    <div class="work">
      <h1>홍승희의 Vue 실습 페이지</h1>
    </div>
    <hr />
    <div style="display: flex;">
        <div v-for="(member, index) in members" :key="index">
            <TeamCard :name="member.name" :food="member.food" :srcUrl="member.urlSrc" :teamNum="member.teamNum"></TeamCard>
        </div>
    </div>
  </template>
  
  <script setup>
  import TeamCard from '@/components/TeamCard.vue'
  import {ref} from 'vue'

  const members = ref([
    {
        name: '홍승희',
        food: '돈까스',
        urlSrc: '/images/team1.png',
        teamNum: '5'
    },
    {
        name: '김민성',
        food: '마파두부',
        urlSrc: '/images/team4.png',
        teamNum: '5'
    },
    {
        name: '박주희',
        food: '1955 버거',
        urlSrc: '/images/team2.png',
        teamNum: '5'
    },
    {
        name: '김대엽',
        food: '치킨',
        urlSrc: '/images/team3.png',
        teamNum: '5'
    }
  ])

  </script>
  
  <style>
  .work {
    color: blue;
  }
  </style>