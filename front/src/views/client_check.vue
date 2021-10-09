<template>
    <div class="container">

        <strong>번호를 입력하세요</strong>
        <br>
           <input type="text" v-model="phone">
        <br>
        <input type="submit" value="조회" @click="checkRegister">

        <div class="checkResult">
            주문 메뉴 : {{}}

        </div>



    </div>

</template>

<script>
export default {
  name: 'check',
  data(){
    return{
        phone:'01032719321'
        dataDto:'';
    }
  },
  methods:{
    checkRegister(){
        let obj={"phone" : this.phone};
        fetch("http://localhost:8081/checkRegister",{
                                    method : 'POST',
                                    mode : 'cors',
                                    cache : 'no-cache',
                                    credentials : 'same-origin',
                                    redirect : 'follow',
                                    referrer : 'no-referrer',
                                    body: JSON.stringify(obj)
                                }).then(response => response.text())
                                .then(res=>{
                                    console.log(res);
                                    if(res.equals(null))
                                })

    }
  }
}
</script>
<style>
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  width:80%;
  margin : 0 auto;
}


input[type=text]{
  width: 90%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}


</style>