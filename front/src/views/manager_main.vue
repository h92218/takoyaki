<template>
    <div id="managerMain">
       <button class="selectedTab" id="tab1" @click='changeTab'>웨이팅 확인</button>
       <button class="managerBtn" id="tab2" @click='changeTab'>대기 시간 설정</button>
       <div v-if="check">
            <checkWaiting/>
       </div>
       <div v-else>
            <setTime/>
       </div>
    </div>

</template>
<script>
import checkWaiting from '@/components/manager_checkWaiting.vue'
import setTime from '@/components/manager_setTime.vue'

export default {
   name: 'managerMain',
   components: {
    checkWaiting,
    setTime
   },
   data(){
    return{
        check:true
    }
   },
   methods:{
    changeTab(){
       let x = event.currentTarget;
       let another = x.parentNode.querySelector('.selectedTab');
       another.className='managerBtn';
       x.className='selectedTab';
       if(x.id == 'tab1'){
            this.check = true;
       }else{
            this.check=false;
       }
    }

   }
}


</script>
<style>
.managerBtn{
    width:50%;
    border : 1px solid;
    text-decoration-line: none;
    border-radius: 4px;
    padding : 1em;
    background-color: #566ee7;
    border:none;
    color:white;
    float:left;
    }

.managerBtn:hover{
    background-color: #f2f2f2;
    color:#566ee7;
    cursor: pointer;
}

.selectedTab{
    width:50%;
    border : 1px solid;
    text-decoration-line: none;
    border-radius: 4px;
    padding : 1em;
    border:none;
    color:black;
    float:left;
    background-color: #f2f2f2;
    font-weight:bold;
}

</style>