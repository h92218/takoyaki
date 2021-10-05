<template>
<div class="container">
<label>이름</label>
<input type="text" v-model="name">
<label>번호</label>
<input type="text" v-model="phone">
<div class='menu'>메뉴</div>{{selectedMenu}}
<div class='menu'>
    <label for="3">
<div class='checkbox_box_default' >
<input type="checkbox" id="3" v-model='selectedMenu' value="3개">3개 1,500원
</div>
    </label>
    <label for="8">
<div class='checkbox_box_default' >
<input type="checkbox" id="8" v-model='selectedMenu' value="8개">8개 3,000원
</div>
    </label>
    <label for="14">
<div class='checkbox_box_default' >
<input type="checkbox" id="14" v-model='selectedMenu' value="14개">14개 5,000원
</div>
    </label>
    <label for="28">
<div class='checkbox_box_default' >
<input type="checkbox" id="28" v-model='selectedMenu' value="28개">28개 10,000원
</div>
    </label>
</div>
<br>
<div class='menu'>맛 선택(여러개 선택 가능)</div>{{selectedFlavor}}
<div class='menu'>

    <label for="original" >
<div class='checkbox_box_default' >
<input type="checkbox" id="original" v-model='selectedFlavor' value="오리지널">오리지널
</div>
    </label>
    <label for="spicy">
<div class='checkbox_box_default' >
<input type="checkbox" id="spicy" v-model='selectedFlavor' value="매운맛">매운맛
</div>
    </label>
    <label for="cheese">
<div class='checkbox_box_default' >
<input type="checkbox" id="cheese" v-model='selectedFlavor' value="치즈맛">치즈맛
</div>
    </label>
</div>


<input type="submit" @click="submit" value="등록하기">
</div>


</template>

<script>
export default {
  name: 'register',
  data(){
    return{
        name:'현선',
        phone :'01032719321',
        selectedMenu:[],
        selectedFlavor:[]
    }
  },
  methods:{
    submit(){
        let obj = {"name":this.name,"phone":this.phone,"menu":this.selectedMenu,"flavor":this.selectedFlavor};
        console.log(obj);
         fetch("http://localhost:8081/waitingRegister",{
                                    method : 'POST',
                                    mode : 'cors',
                                    cache : 'no-cache',
                                    credentials : 'same-origin',
                                    redirect : 'follow',
                                    referrer : 'no-referrer',
                                    body: JSON.stringify(obj)
                                }).then(response => console.log(response))


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

input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}



input[type=submit]:hover {
  background-color: #45a049;
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

.checkbox_box_default{
    width:93%;
    padding : 0.5em;
    background-color: white;
    border : 2px solid #4CAF50;
    border-radius: 18px;
    margin: 0.8em 0em;
}


.menu{
   text-align:left;

}


input[type="checkbox"]{
    appearance: none;
    width: 16px;
    height: 16px;
    border: 1px solid grey;
    vertical-align: -5px;
    border-radius: 4px;
    margin-right: 5px;
}

input[type="checkbox"]:checked {
  background: green;
}

input[type="checkbox"]:after {
  content: '';
  position: relative;
  left: 40%;
  top: 20%;
  width: 15%;
  height: 40%;
  border: solid #fff;
  border-width: 0 2px 2px 0;
  transform: rotate(45deg);
  display: none;
}

input[type="checkbox"]:checked:after {
  display: block;
}


</style>
