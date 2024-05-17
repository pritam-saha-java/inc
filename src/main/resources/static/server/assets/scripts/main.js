// this file will be connected to index file


function search(){

    var city = cities.value;
    var state = states.value;
    var category = categories.value;
    var text = searchText.value;
    var selectedCity = city!=="Select City";
    var selectedState = state!=="Select State";
    var selectedText = text.length>0;
//    alert(categories.value)
//    alert(selectedText)



    if (selectedText) {
        if(selectedCity){

            document.location="/"+category+"/"+city+"/"+text.toLowerCase().replaceAll(" ","-");
            }
        else{
            alert("please choose city first");
        }
    } else if (selectedCity) {
       document.location="/"+category+"/"+city;

    }  else if (selectedState) {
             alert("please select city")

    }  else {
       document.location="/"+category;

    }
//    "hello world".replaceAll(" ","_")
//    document.location="/"+categories.value;
//    if(selectedState)
//        alert("this is search"+state);
//    else
//        alert("this is search without state");

}


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
        showButton.innerHTML =  '&#xf106;'
    }else{
        category.style.display = 'none'
        showButton.innerHTML =  '&#xf105;'

     }
   
    
}


document.addEventListener("DOMContentLoaded", function() {
    const descriptionContainers = document.querySelectorAll('.description-container');

    descriptionContainers.forEach(container => {
        const content = container.querySelector('.card-text');
        const lineHeight = parseInt(window.getComputedStyle(content).lineHeight);
        const maxHeight = lineHeight * 3;

        if (container.offsetHeight < maxHeight) {
            container.style.maxHeight = 'none';
            container.removeChild(container.querySelector('::after'));
        }
    });
});

// document.getElementById("upload-file").addEventListener("click",function(){
//   document.getElementById("image-input1").click();
// })


document.getElementById('image-input1').addEventListener('change', function() {
    // Get the number of files selected
    var selectedFiles = this.files.length;
    
    console.log("Data: "+selectedFiles);
    // Check if the number of selected files exceeds the limit (6)
    if (selectedFiles > 6) {
      alert('You can only select a maximum of 6 files.');
      // Clear the selected files
      this.value = '';
    }
  });


console.log('js connected');