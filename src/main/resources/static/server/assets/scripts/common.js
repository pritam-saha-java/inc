
// this file will have all the common js effects needed for all pages


var sitePopup = sessionStorage.getItem('popup');
if(!sitePopup){





const popupText = "<small class ='row d-flex align-items-center ' > <p class ='col-11 text-center ' >Lorem ipsum dolor sit amenLorem ipsum dolor sit amen</p>   <b class ='col-1 ' id='closeButton' > x </b> </small>";
const popup = 'popup';
var body = document.body;
var title = document.createElement('div');
title.classList.add('container');
title.classList.add('lh-1');
title.classList.add('mb-0');
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
    sessionStorage.setItem('popup',true);
    
});

}
function siteRedirect(file){
    window.location = file+'.html';
}
const logos = document.querySelectorAll('.site-logo');
logos.forEach(logo => {
    logo.addEventListener('click', () => {

        if (window.location.pathname !== '/') {
                    window.location.href = "/";
            }
    });
  });

// for location access
// fetch('https://api.ipify.org/').then(
//   r => r.text()
// ).then(window.alert);

console.log("common js connected");

let navToggle = document.querySelector(".nav__toggle");
let navWrapper = document.querySelector(".nav__wrapper");
let showBtn = document.getElementById("menu-one");
let closeBtn = document.getElementById("menu-two");

navToggle.addEventListener("click", function () {
  if (navWrapper.classList.contains("active")) {
    this.setAttribute("aria-expanded", "false");
    this.setAttribute("aria-label", "menu");
    navWrapper.classList.remove("active");
    closeBtn.style.display = 'none'
    showBtn.style.display = ''

  } else {
    navWrapper.classList.add("active");
    this.setAttribute("aria-label", "close menu");
    this.setAttribute("aria-expanded", "true");
    closeBtn.style.display = ''
    showBtn.style.display = 'none'
  }
});
