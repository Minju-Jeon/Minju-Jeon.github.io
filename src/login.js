const loginForm = document.querySelector("#login");
const nameInput = loginForm.querySelector("input");
const greetingArea = document.querySelector("#greeting");

function getLogin(event) {
  event.preventDefault();
  localStorage.setItem("username", nameInput.value);
  greeting();
}


function greeting() {
  const name = localStorage.getItem("username");
  const HELLO = `Hello, ${name}!`

  document.querySelector("#greeting span").innerHTML= HELLO;
  loginForm.classList.toggle("hidden");
  greetingArea.classList.toggle("hidden");
  console.log(`${name} 로그인`);
}


if(localStorage.getItem("username")){
    greeting();
}

loginForm.addEventListener("submit", getLogin);
