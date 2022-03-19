const body = document.querySelector("body");
const img = [
   "0.jpg",
   "1.jpg",
   "2.jpg",
   "3.jpg",
   "4.jpg",
   "5.jpg",
   "6.jpg",
   "7.jpg"
];
const rdmImg = img[Math.floor(Math.random()*img.length)];

function changeBgImgRnd(){
    body.style.backgroundImage = `url("src/img/${rdmImg}")`;
}

changeBgImgRnd();