<template>
<div id="status">
<span class="waitingText">현재 대기 <strong>{{waitingCount}}</strong>명</span><br>
<span class="waitingText">대기 시간 <strong>0</strong>분</span>
<br>
<br>

</div>
</template>

<script>
export default {
  name: 'status',
  data(){
    return{
        waitingCount:''
    }
  },
  mounted(){
    fetch("http://localhost:8081/selectCount",{
         method : 'GET'
    }).then(response=>response.text())
    .then(res=>{
        this.waitingCount=res;
    })
  }

}
</script>
<style>
.waitingText{
    font-size:2em;
}
</style>