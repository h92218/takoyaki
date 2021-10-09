<template>
  <div class="home">
    <img id="mainImg" src="../assets/takoyaki.png">
    <status/>
    <router-link to="/register">대기 등록하기</router-link>
    <br><br>
    <router-link to="/check">등록 확인/변경하기</router-link>
  </div>

</template>

<script>
// @ is an alias to /src
import status from '@/components/common_status.vue'

export default {
  name: 'Home',
  components: {
    status
  },
  methods:{

  }
}
</script>
<style>
#mainImg{
   width: 300px;
}
</style>