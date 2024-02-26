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
function showCategory(element){
    
    let catergoryButton = element.parentNode.parentNode.parentNode;
    
  
    let category = catergoryButton.querySelector('.category-text-bar');
    let button = element;
    
    let boolean = category.style.display==='none'?true:false;
     if(boolean){
        category.style.display = ''
        button.innerHTML =  '&#x25B3;'
    }else{
        category.style.display = 'none'
        button.innerText =  '>'

     }
   
    
}
console.log('js connected');