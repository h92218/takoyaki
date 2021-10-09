<template>
    <div class="result">
     <span>{{$route.query.res}}</span>
        <br><br>
        <router-link to="/" id="btn_goHome">홈으로</router-link>
    </div>
</template>
<script>
export default {
  name: 'result',

}

</script>
<style>
span{
   font-size:2em
}

#btn_goHome:hover {
  background-color: #45a049;
}

#btn_goHome {
  width: 60%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>