<template>
    <div id="checkWaiting">
        <br><br>
    <table>
        <thead>
        <th>번호</th><th>등록시각</th><th>전화번호</th><th>메뉴</th><th>맛</th><th>완료</th><th>삭제</th>
        </thead>
        <tr v-for="data in list" :key="data">
            <td><span class={{data.completion}}>{{data.idx}}</span></td><td>{{data.registerDate}}</td><td>{{data.phone}}</td><td>{{data.menu}}</td><td>{{data.flavor}}</td>
            <td><button class="tableBtn" @click="completeCustomer">완료</button></td><td><button class="tableBtn" @click="deleteCustomer">삭제</button></td>
        </tr>
    </table>
        <br>
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
  },
  methods:{
    completeCustomer(){
        let phone = event.currentTarget.parentNode.parentNode.children[2].outerText;
        fetch("http://localhost:8081/completeCustomer?phone="+phone,{
            method : 'GET'
        })
    },
    deleteCustomer(){
         let phone = event.currentTarget.parentNode.parentNode.children[2].outerText;
         fetch("http://localhost:8081/deleteCustomer?phone="+phone,{
            method : 'GET'
        })
    }
  }
}

</script>
<style>
#checkWaiting{
    background-color: #f2f2f2;
}

.tableBtn{
border-radius: 4px;
    padding:0.5em 1em;
    background-color: #566ee7;
    border:none;
    color:white;
}

.tableBtn:hover{
    cursor:pointer;
    background-color: #4457b7;
}

tr,td,th{
    border : 1px solid;
}
table{
    border-collapse : collapse;
    width:90%;
    margin:2em auto;
}
</style>