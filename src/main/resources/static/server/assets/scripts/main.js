// this file will be connected to index file





var defaultRule = document.getElementsByClassName('category-text-bar');
for (const iterator of defaultRule) {
    iterator.style.display = 'none';
}
var allTDs = document.getElementsByTagName('td');
for (const element of allTDs) {

    element.classList.add('bg-light');
    element.classList.add('text-light');
    
}
function showCategory(button,categoryBody){
    
//    let catergoryButton = element.parentNode.parentNode.parentNode;
    
  
//    let category = catergoryButton.querySelector('.category-text-bar');
    let category = document.getElementById(categoryBody);
    let showButton = document.getElementById(button);
    
    let boolean = category.style.display==='none'?true:false;
     if(boolean){
        category.style.display = ''
        showButton.innerHTML =  '&#11165;'
    }else{
        category.style.display = 'none'
        showButton.innerHTML =  '&#11166;'

     }
   
    
}
console.log('js connected');