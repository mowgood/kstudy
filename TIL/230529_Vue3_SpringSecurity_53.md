## [day53] 2023.05.29 월 학습 내용 정리
1. vue3
2. Spring Security
---
## 1. vue3  
### provide, inject  
조상에서 provide 한 것을 자식에서 inject로 받을 수 있다.  
부모-자손 컴포넌트간의 통신을 할 수 있다.  
```vue
<!-- ProvideInject.vue -->
<template>
  <div>
    <h2>provide와 inject</h2>
    <ProvideInjectChild />
  </div>
</template>
<script setup>
import ProvideInjectChild from './ProvideInjectChild';
import { provide, ref, reactive } from 'vue'

provide('pmess', '안녕?'); // (key, value)
const count = ref(10); 
provide('pnum', count);
const obj = reactive({
  name: "유니코",
  age: 20
});
provide('pobj', obj);
</script>
```

```vue
<!-- ProvideInjectChild.vue -->
<template>
  <h3>자식이 받았어요.. {{ message }}</h3>
  <h3>자식이 받았어요.. {{ number }}</h3>
  <h3>자식이 받았어요.. {{ obj.name }}</h3>
  <h3>자식이 받았어요.. {{ obj.age }}</h3>
  <hr />
  <div>
    <ProvideInjectGrandChild />
  </div>
</template>
<script setup>
import { inject, provide } from 'vue'
import ProvideInjectGrandChild from './ProvideInjectGrandChild';

const message = inject('pmess');
const number = inject('pnum');
const obj = inject('pobj');
provide("pnum", number.value + 100);
</script>
```

```vue
<!-- ProvideInjectGrandChild.vue -->
<template>
  <h4>자손도 받았어요.. {{ message }}</h4>
  <h4>자손도받았어요.. {{ number }}</h4>
  <h4>자손도 받았어요.. {{ obj.name }}</h4>
  <h4>자손도 받았어요.. {{ obj.age }}</h4>
</template>
<script setup>
import { inject } from 'vue'

const message = inject('pmess');
const number = inject('pnum');
const obj = inject('pobj');
</script>
```

### VueX  
- state: 뷰 컴포넌트에서 사용되는 데이터
- getters: 뷰 컴포넌트의 computed()와 동일한 기능을 작성하는 부분, state 데이터를 읽기만 가능 
- mutations: 뷰 컴포넌트의 methods()와 동일한 기능을 작성하는 부분, - state 값 변경 가능
- actions: 쌍방통행 함수(= methods)이 외의 함수를 작성하는 부분, 비동기적 처리가능, state 데이
터를 읽기만 가능, mutations에 작성되는 로직 외 대부분의 로직을 actions에 작성하는 것을 권장함.

```js
// main.js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import store from "./store_vuex/store";

createApp(App).use(router).use(store).use(createPinia()).mount('#app')
```

```js
// store.js
import { createStore } from "vuex";

export default createStore({
  state: {
    counter: 10
  },
  // 원하는 연산을 수행
  getters: {
    time2(state) {
      return state.counter * 2;
    }
  },
  mutations: {
    setCounter(state, value) {
      state.counter = value;
    }
  },
  actions: {
    setLayzCounter(context) {
      setTimeout(() =>context.commit('setCounter', 1000), 5000)
    }
  }
  
});
```
- getters (read only) 
읽어온 데이터에서 데이터의 개수, 어떤 조건을 만족하는 개수 등을 구할 때 사용한다.

- mutations
  - state에 있는 데이터를 변경할 수 있다.
  - setCounter(공유할 데이터, 전달된 데이터)

- actions (비동기 처리)  
호출 후 기다리지 않고 바로 리턴.    
  - setLayzCounter  
    - 5초 후 setCounter를 1000으로 set

```vue
<!-- StoreTest.vue -->
<template>
        <div>
                <h2>Vuex의 store 를 사용하여 출력하는 내용</h2>
                <h3>{{ counter }}</h3>
                <h3>getter 호출 : {{ test }}</h3>
                <button @click="inc">mutation 호출결과</button>
                <button @click="asyncinc">action 호출결과</button>
        </div>
</template>
<script>
import { computed } from "vue";
import { useStore } from "vuex";
export default {
        setup() {
                const store = useStore();
                console.log(store)
                const counter = computed(() => store.state.counter);
                const test = computed(() => store.getters);
                const inc = () => store.commit("setCounter", counter.value + 1);
                const asyncinc = () => store.dispatch("setLayzCounter");

                return { counter, inc, test, asyncinc };
        }
};
</script>
```

### Pinia  
Vue.js용 스토어 라이브러리 및 상태 관리 프레임워크.  
주로 프론트엔드 웹 애플리케이션 구축을 위해 설계되어있다.
선언적 구문을 사용하고 자체 상태 관리 API를 제공한다.

|용어|설명|
|--|--|
|Store|전역 데이터가 저장되는 저장소|
|State|전역 데이터|
|Getters|저장소에서 데이터를 검색하는 방법|
|Actions|저장소에서 데이터를 수정하는 방법|

- 장점
  - 직관적
  - 유형 안전
  - Devtools 지원
  - 확장 가능
  - 모듈식 설계
  - 매우 가벼움

> Vue3 및 Composition API가 출시되면서 Pinia라는 상태 관리 모듈이 추가되었다.  
> Vuex의 단점 중 TS와 같이 설계하기 어려운 점(문법이나 Type 등에서)이 많이 개선되었다.

```js
// counter.js
// 옵션 api 형식
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', {
  state: () => {
    return { count: 0, name: '김정현' }
  },
  // could also be defined as
  // state: () => ({ count: 0 })
  getters: {
    getName: (state) => state.name+"!!",
  },
  actions: {
    increment() {
      this.count++
    },
  },
})
```
- defineStore
  - 함수를 리턴
  - 예제에서는` useCounterStore` 함수를 호출하여 사용하면 된다.

```vue
<!-- PiniaTest1.vue -->
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
```
- `'@/stores/counter' ` 
  - @ : src 폴더

- $patch    
데이터의 일부분을 변경한다.  

```vue
<!-- PiniaTest2.vue -->
<template>
        <h3>Option API 스타일의 스토아</h3>
        <div>num1: {{ num1 }}</div>
        <div>num2: {{ num2 }}</div>
        <div>num2: {{ store1.count }}</div>
        <button @click="store1.count++">1 증가(연산식)</button>
        <button @click="store1.increment">1 증가(action 호출)</button>
        <div>현재 값: {{ store1.count }}</div>
        <hr />
        <h3>Composition API 스타일의 스토아</h3>
        <div>친구 이름: {{ name }}</div>
        <div>친구 나이 : {{ age }}</div>
        <button @click="store2.updateAge(5)">친구나이변경</button>
        <button @click="age++">+</button>
</template>

<script setup>
import { useCounterStore } from '@/stores/counter'
import { useFriendStore } from '@/stores/friendstore'
import { storeToRefs } from 'pinia'

const store1 = useCounterStore();
let num1 = store1.count;
store1.increment();
let num2 = store1.count;


const store2 = useFriendStore()
const { name, age } = storeToRefs(store2) // 반응성을 유지하면서 구조 분해 할당
</script>
```
state 객체 안에 들어가는 요소는 자동으로 reactive가 적용된다.  
reative 프록시 객체로 만들어진 요소를 구조 분해 할당을 하게 되면 반응성을 잃게 된다. -> `storeToRefs()` 를 사용해서 구조 분해 할당을 하게 되면 반응성을 갖게 된다. 


### 컴포지션 api 형식으로 구현
```js
<!-- friendstore.js -->
import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useFriendStore = defineStore('friend', () => {
  const age = ref(10)
  const name = ref('둘리')
  function updateAge(n) {
    age.value+=n;
  }
  return { age, name, updateAge }
})
```

### watch  
```vue
<!-- WatchTest.vue -->
<template>
    <h2>watch와 watchEffect 테스트</h2>
    <div>
        <h3>{{ num1 }}</h3>
        <button @click="num1++">increase num1</button>
        <h3>{{ num2 }}</h3>
        <button @click="num2++">increase num2</button>
    </div>
</template>

<script setup>
import { ref, watch, watchEffect } from 'vue';
const num1 = ref(10);
const num2 = ref(100);

// watch : num1 값의 변경이 감지되어야 실행이됨.
watch(num1, (after, previous) => {
    console.log(`[W]현재값 : ${after}`);
    console.log(`[W]이전값 : ${previous}`);
});

watchEffect(() => {
    console.log("num1 이나 num2 가 변경됨!!!");
    console.log(`[WE]num1 : ${num1.value}`);
    console.log(`[WE]num2 : ${num2.value}`);
});        
</script>
```
- watch()
  - watch(변수, 함수)    
  - 함수가 변수가 변경될 때마다 수행된다.
  - 최초 랜더링 시 수행 X, 변수가 바뀔때만 수행된다.

  ```vue
  // num1 값 변경 시 실행된다.
  watch(num1, (after, previous))  
  ```

- watchEffect()  
  - 등록
  - 최초 랜더링 시 무조건 수행
  - 등록된 함수에서 다루는 반응형 변수의 값이 변경될 때마다 실행
  - 감시할 필요가 없을 때 중단 가능(computed는 불가능하다.)

## 2. Spring Security  
Spring 기반의 어플리케이션 보안(인증과 권한, 인가 등)을 담당하는 스프링 하위 프레임워크
- Authentication(인증)  
특정 대상이 "누구"인지 확인하는 절차
- Authorization(권한부여, 인가)  
인증된 주체가 특정한 곳에 접근 권한을 확인하고 허용하는 과정

Spring security는 여러 가지 필터로 이루어져 있고, 필터들이 순서대로 동작한다. 이 구조를 Filter Chain 이라고 한다.  

> Security는 인증과 권한에 대한 부분을 Filter의 흐름에 따라 처리한다.  

### Spring Security의 securityConfig 작성하기  
WebSecuityConfigurerAdapter가 사라졌기 때문에,  
SecurityFilterChain을 Bean으로 등록해서 사용해야 한다.  이 두 방법은 모두 가능하며 구현 방법도 비슷하다.  
  - authorizeRequest()  
  보안 절차를 거치고
  - anyRequest()  
  어떠한 request라도  
  - authenticated()  
  인증을 받아야 한다
  - formLogin()  
  그 방식은 폼 로그인  

위와 같은 형식으로 설정 클래스가 처리되며, 최종적으로 SecurityFilterChain 타입으로 값을 빌드하여 리턴해야 한다. 그 외에도 다양한 사용자 정의 보안 설적 적용이 가능하다.  
```java
public class SecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http
  .authorizeRequests()
  .anyRequest().authenticated()
  .formLogin();
  
  return http.build();
  }
}
```

### 기본 formLogin 인증 API 구조  
```
http.formLogin()
.login("/login")
 .defaultSuccessUrl("/home")
 .failureUrl("/login")
 .usernameParameter("username")
 .passwordParameter("password")
 .loginProcessingUrl("/login")
 .successHandler(loginSuccessHandler())
 .failereHandler(loginFailureHander())
```
- login : 사용자가 정의한 로그인 페이지
- defaultSuccessUrl: 로그인 성공 후 이동하는 페이지
- failureUrl: 로그인 실패 후 이동하는 페이지
- usernameParameter: 폼 태그에 사용되는 아이디 파라미터명
- passwordParameter: 폼 태그에 사용되는 비밀번호 파라미터명
- loginProcessingUrl: 폼 태그에 사용되는 url
- successHandler: 로그인 성공 후 실행되는 handler 
- failereHandler: 로그인 실패 후 실행되는 handler

> Spring Security는 dispatcherServlet 이전에 필터링하는 역할을 수행하므로, 여기서 지칭하는 핸들러는 Security에 내장된 혹은 사용자가 정의한 핸들러이다.  

### 핸들러 작성 예시
```
.successHandler((request, response, authentication) -> {
 log.info("authentication name = {}", authentication.getName())
 response.sendRedirect("/");
 })
.failureHandler((request, response, exception) -> {
 log.info("exception.getMessage() = {}", exception.getMessage());
 response.sendRedirect("/login");
 })
```
successHandler를 람다식으로 해당 폼 로그인을 성공하면 인증객체의 이름으로 로그를 찍고, "/"으로 리다이렉트하는 핸들러다.   
만약 실패할 경우, 필터가 fauilerHandler를 찾아 해당 람다식 핸들러를 실행시킨다.

### Login Form 인증 절차  
1. Http Request가 서버로 넘어온다.
2. AuthenticationFilter가 요청을 낚아챈다. AuthenticationFilter에서 Request의 Username, password를 이용하여 UsernamePasswordAuthenticationToken을 생성한다.
3. 토큰을 AuthenticationManager가 낚아챈다.
4. AuthenticatonManager는 토큰을 AuthenticationProvide에게 토큰을 넘긴다.
5. AuthenticationProvider는 UserDetailsService로 토큰의 사용자 아이디(username)을 전달하여 DB에 존재하는지 확인한다.
6. UserDetailsService는 DB의 회원정보를 UserDetails라는 객체로 반환한다.  
7. AuthenticationProvider는 반환받은 UserDetails 객체와 실제 사용자의 입력정보를 비교한다.
비교가 완료되면 사용자 정보를 가진 Authentication 객체를 SecurityContextHolder에 담은 이후 AuthenticationSuccessHandle를 실행한다. (실패시 AuthenticationFailureHandler를 실행한다.)

### JWT (Json Web Token)  
Cookie / Session / Token 인증 방식 종류  
- 서버가 클라이언트 인증을 확인하는 방식 3가지
  1. 쿠키
  2. 세션
  3. 토큰

### 쿠키  
Key-Value 형식의 문자열 덩어리. 각 사이트가 사용하고 있는 서버를 통해 클라이언트의 브라우저에 설치되는 작은 기록 정보 파일이다.  
- 단점  
  - 보안이 취약하다.
  - 요청 시 쿠키 값 그대로 보내기 때문에 유출 및 조작 당할 위험 존재
  - 용량 제한이 있어 많은 정보를 담을 수 없다.
  - 브라우저간 공유가 불가능하다.
  - 쿠키 사이즈가 커질수록 네트워크 부하가 심해진다.

### 세션  
- 쿠키의 보안 이슈 때문에, 세션은 비밀번호 등 클라이언트의 민감한 인증 정보를 브라우저가 아닌 서버 측에 저장하고 관리한다. 
- 서버의 메모리에 저장하거나, 서버의 로컬 파일이나 데이터베이스에 저장하기도 한다.  
- 민감한 정보는 클라이언트에 보내지 않고 서버에서 모두 관리한다.
- Key-vlaue
  - Key : Session ID
  - Value : 세션 생성 시간, 마지막 접근 시간 및 User 가 저장한 속성 등이 Map 형태로 저장된다.
- 한계  
  - 해커가 세션 ID 자체를 탈취하여 클라이언트인척 위장할 수 있다. (이는 서버에서 IP특정을 통해 해결 가능)
  - 서버에서 세션 저장소를 사용하므로 요청이 많아지면 서버에 부하가 심해진다.

### 토큰  
1. 토큰 기반 인증 시스템은 클라이언트가 서버에 접속하면 서버에서 해당 클라이언트에게 인증되었다는 의미로 토큰을 부여한다.  
2. 이 토큰은 유일하며 토큰을 발급받은 클라이언트는 또 다시 서버에 요청을 보낼 때 요청 헤더에 토큰을 심어서 보낸다. 
3. 서버에서는 클라이언트로부터 받은 토큰을 서버에서 제공한 토큰과의 일치 여부를 체크하여 인증 과정을 처리하게 된다. 
- 단점
  - 쿠키/세션과 다르게 토큰 자체의 데이터 길이가 길어, 인증 요청이 많아질수록 네트워크 부하가 심해질 수 있다.
  -  Payload 자체는 암호화되지 않기 때문에 유저의 중요한 정보는 담을 수 없다.
  - 토큰을 탈취당하면 대처하기 어렵다. (따라서 **사용 기간 제한을 설정**하는 식으로 극복한다)

### 세션 VS 토큰
- 기존 세션기반 인증  
서버가 파일이나 데이터베이스에 세션 정보를 가지고 있어야 하고 이를 조회하는 과정에 필요하기 때문에 많은 오버헤드가 발생한다.

- 토큰  
세션과 달리 서버가 아닌 클라이언트에 저장되기 때문에 메모리나 스토리지 등을 통해 세션을 관리했던 서버의 부담을 덜 수 있다.
  - 토큰 자체에 데이터가 들어있기 때문에 클라이언트에서 받아 위조되었는지 판별만 하면 되기 때문.    

  토큰은 앱과 서버가 통신 및 인증할 때 가장 많이 사용된다. 왜냐하면 **웹에는 쿠키와 세션이 있지만 앱에는 없기 때문이다.**
