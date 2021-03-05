/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 let tabHeader = document.getElementsByClassName("tab-header")[0];
let tabIndicator = document.getElementsByClassName("tab-indicator")[0];
let tabBody = document.getElementsByClassName("tab-body")[0];

let tabsPane = tabHeader.getElementsByTagName("div");

for(let i=0;i<tabsPane.length;i++){
  tabsPane[i].addEventListener("click",function(){
    tabHeader.getElementsByClassName("headerActive")[0].classList.remove("headerActive");
    tabsPane[i].classList.add("headerActive");
    tabBody.getElementsByClassName("bodyActive")[0].classList.remove("bodyActive");
    tabBody.getElementsByClassName("divTab")[i].classList.add("bodyActive");
    
    tabIndicator.style.left = `calc(calc(100% / 4) * ${i})`;
  });
}