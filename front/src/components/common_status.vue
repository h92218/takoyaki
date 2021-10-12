<template>
<div id="status">
<span class="waitingText">현재 대기 <strong>{{waitingCount}}</strong>명</span><br>
<span class="waitingText">대기 시간 <strong>{{waitingTime}}</strong>분</span>
<br>
<br>

</div>
</template>

<script>
export default {
  name: 'status',
  data(){
    return{
        waitingCount:'',
        waitingTime:'',
    }
  },
  mounted(){
    window.setInterval('location.reload()', 60000);

    fetch("http://localhost:8081/selectCount",{
         method : 'GET'
    }).then(response=>response.text())
    .then(res=>{
        this.waitingCount=res;
    });


    fetch("http://localhost:8081/getTime",{
         method : 'GET'
    }).then(response=>response.text())
    .then(res=>{
        this.waitingTime=res;
    })
  }

}
</script>
<style>
.waitingText{
    font-size:2em;
}
</style>