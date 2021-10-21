<template>
    <div class="container">

        <strong>번호를 입력하세요</strong>
        <br>
           <input type="text" v-model="phone">
        <br>
        <input type="submit" value="조회" @click="checkRegister">

        <div class="checkResult">
            남은 대기시간 {{dataDto.remainTime}}분<br><br>
            앞의 대기 팀 {{waitingCount}}명<br><br>

            대기번호 : {{dataDto.idx}}번 <br><br>
            메뉴 :   {{dataDto.flavor}} {{dataDto.menu}}<br><br>
            등록시각 : {{dataDto.registerDate}}<br><br>
             <input type="submit" class="changeBtn" value="메뉴변경" @click="changeMenu"><br>
             <input type="submit" class="changeBtn" value="대기 삭제" @click="deleteWaiting">
        </div>
        <div class="checkResultIsNull">
            등록되지 않은 번호입니다.
        </div>



    </div>

</template>

<script>
export default {
  name: 'check',
  data(){
    return{
        phone:'01032719321',
        dataDto:'',
        waitingCount:''
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
            }).then(response => {
                                    if(response.status != 200){
                                        let x = document.getElementsByClassName("checkResultIsNull");
                                        let y = document.getElementsByClassName("checkResult");
                                        y[0].style.display='none';
                                        x[0].style.display='block';


                                    }else{
                                        return response.json().then(res=>{
                                             this.dataDto = res;
                                             let x = document.getElementsByClassName("checkResultIsNull");
                                             let y = document.getElementsByClassName("checkResult");
                                             x[0].style.display='none';
                                             y[0].style.display='block';

                                        });
                                    }
            }),

        fetch("http://localhost:8081/selectWaitingCount?phone="+this.phone,{
                method : 'GET'

        }).then(response=>response.text())
        .then(res=>{
            this.waitingCount=res;
        })

    },

    deleteWaiting(){
        if(confirm("주문을 취소하시겠습니까?") == true){
            fetch("http://localhost:8081/deleteCustomer?phone="+this.phone,{
                 method : 'GET',
                 mode : 'cors',
                 cache : 'no-cache',
                 credentials : 'same-origin',
                 redirect : 'follow',
                 referrer : 'no-referrer'
            }).then(response=>response.text())
            .then(res=>{
                console.log(res);
                location.href='http://localhost:8081/result?res='+res;
            })

        }else{
            return;
        }

    },
    changeMenu(){
        location.href='http://localhost:8081/changeMenu?phone='+this.phone+"&menu="+this.dataDto.menu+"&flavor="+this.dataDto.flavor;
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

.changeBtn{
    width:35% !important;
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

.checkResult, .checkResultIsNull{
    display : none;
    margin-top: 20px;
    font-weight : bold;
    font-size:1.5em;
}



</style>