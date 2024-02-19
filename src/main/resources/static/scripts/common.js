// this file will have all the common js effects needed for all pages
const popupText = "<div class ='row' > <p class ='col-11' >Lorem ipsum dolor sit amenLorem ipsum dolor sit amen</p>   <b class ='col-1 ' id='closeButton' > x </b> </div>";
const popup = 'popup';
var body = document.body;
var title = document.createElement('h1');
title.classList.add('container');
title.classList.add('text-light-pink');
// title.classList.add('text-center');
title.id = popup;
title.innerHTML = popupText;
//check from session if it is first visitor or not if it is first link then show popup if it is already turned  off then don't show
body.insertBefore(title,body.childNodes[0]);
var closeButton = document.getElementById('closeButton'); 
closeButton.addEventListener('click',(myEvent)=>{
    let element = document.getElementById(popup);
    element.style.display = 'none';
    
});
function siteRedirect(file){
    window.location = file+'.html';
}
const logos = document.querySelectorAll('.site-logo');
logos.forEach(logo => {
    logo.addEventListener('click', () => {

        if (window.location.pathname !== '/index.html') {
                    window.location.href = "index.html";
            }
    });
  });

// for location access
// fetch('https://api.ipify.org/').then(
//   r => r.text()
// ).then(window.alert);

console.log("common js connected");