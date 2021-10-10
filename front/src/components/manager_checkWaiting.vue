<template>
    <div id="checkWaiting">
            대기 명단 확인 페이지.
    <table>
        <tr v-for="data in list" :key="data">
            <td>{{data.idx}}</td><td>{{data.phone}}</td><td>{{data.menu}}</td><td>{{data.flavor}}</td>
        </tr>
    </table>
    </div>

</template>
<script>
export default {
  name: 'checkWaiting',
  data(){
    return{
        list:''
    }
  },
  mounted(){
    fetch("http://localhost:8081/selectAll",{
        method : 'GET',
        cache : 'no-cache',
        credentials : 'same-origin',
        redirect : 'follow',
        referrer : 'no-referrer'
    }).then(response=>response.json())
    .then(res=>{
        console.log(res);
        this.list=res;
    })
  }
}

</script>
<style>
#checkWaiting{
    background-color: #f2f2f2;
}

</style>