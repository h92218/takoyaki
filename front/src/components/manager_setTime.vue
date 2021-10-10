<template>
    <div id="setTime">
        대기 시간 설정 페이지.
        현재 대기 시간
    </div>
</template>
<script>
export default {
  name: 'setTime',
  mounted(){
    fetch("http://localhost:8081/selectTime",{
         method : 'GET'
    })
  }
}
</script>
<style>
#setTime{
    background-color: #f2f2f2;
}

</style>