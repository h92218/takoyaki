<template>
    <div id="setTime">
        현재 대기 시간 {{time}}분<br>
        <br>
        <button class="minusBtn" @click='setValue' value="-5">-5분</button><button class="plusBtn" @click='setValue' value="5">+5분</button><br>
        <button class="minusBtn" @click='setValue' value="-10">-10분</button><button class="plusBtn" @click='setValue' value="10">+10분</button><br>
        <button class="minusBtn" @click='setValue' value="-15">-15분</button><button class="plusBtn" @click='setValue' value="15">+15분</button><br>
        <button class="minusBtn" @click='setValue' value="-20">-20분</button><button class="plusBtn" @click='setValue' value="20">+20분</button><br>

    </div>
</template>
<script>
export default {
  name: 'setTime',
  data(){
    return{
        time:'',
    }
  },
  mounted(){
    this.getValue();
  },
  methods:{
    setValue(){
       let val = event.currentTarget.value;
       fetch("http://localhost:8081/setTime?val="+val,{
         method : 'GET'
        }).then(response=>response.text())
            .then(res=>{
                this.time=res;
        })
    },
    getValue(){
        fetch("http://localhost:8081/getTime",{
         method : 'GET'
        }).then(response=>response.text())
            .then(res=>{
                this.time=res;
        })
    }
  }
}
</script>
<style>
#setTime{
    background-color: #f2f2f2;
}

.minusBtn{
    width:20%;
    text-decoration-line: none;
    border-radius: 4px;
    padding : 1em;
    margin : 0.4em;
    background-color: #566ee7;
    border:none;
    color:white;
}

.plusBtn{
    width:20%;
    text-decoration-line: none;
    border-radius: 4px;
    padding : 1em;
    margin : 0.4em;
    background-color: #e76056;
    border:none;
    color:white;
}

.minusBtn:hover{
    cursor: pointer;
    background-color: #4457b7;
}
.plusBtn:hover{
    cursor: pointer;
    background-color: #d25950;
}

</style>