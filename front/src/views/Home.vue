<template>
  <div class="home">
    <img id="titleImg" src="../assets/title.png"><br>
    <img id="mainImg" src="../assets/takoyaki.png">
    <status/>
    <router-link to="/register"><button class="homeBtn">대기 등록하기</button></router-link>
    <br>
    <router-link to="/check"><button class="homeBtn">등록 확인/변경하기</button></router-link>
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
   width: 50%;
}

#titleImg{
    width:60%;
}

.homeBtn{
    width:30%;
    border : 1px solid;
    text-decoration-line: none;
    border-radius: 4px;
    padding : 1em;
    margin : 0.4em;
    background-color: #566ee7;
    border:none;
    color:white;
}

.homeBtn:hover{
    cursor: pointer;
    background-color: #4457b7;
}
</style>