<template>
<div class="container">
<label>이름</label>
<input type="text" v-model="name">
<label>번호</label>
<input type="text" v-model="phone">
<div class='menu'>메뉴</div>
<div class='menu'>
<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedMenu' value="3개">3개 1,500원
</div>
<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedMenu' value="8개">8개 3,000원
</div>
<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedMenu' value="14개">14개 5,000원
</div>
<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedMenu' value="28개">28개 10,000원
</div>
</div>
<br>
<div class='menu'>맛 선택(여러개 선택 가능)</div>
<div class='menu'>

<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedFlavor' value="오리지널">오리지널
</div>
<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedFlavor' value="매운맛">매운맛
</div>
<div class='checkbox_box_default' @click='boxclick'>
<input type="checkbox" v-model='selectedFlavor' value="치즈맛">치즈맛
</div>
</div>


<input type="submit" value="등록하기">
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
    boxclick: function(event){
    let dv = event.currentTarget;
    let cb = dv.querySelector('input');
    console.log(cb.checked);
    if(dv.className=="checkbox_box_default"){
        dv.className="checkbox_box_selected";
        cb.checked=true;
    }else{
        dv.className="checkbox_box_default";
        cb.checked=false;
    }




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

label{
float: left;
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
    background-color: #FFFFFF;
    border : 2px solid #4CAF50;
    border-radius: 18px;
    margin: 0.8em 0em;
}

.checkbox_box_selected{
    width:93%;
    padding : 0.5em;
    background-color: #4CAF50;
    color:white;
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
