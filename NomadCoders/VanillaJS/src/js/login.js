const loginForm = document.getElementById("loginForm");
const nameInput = loginForm.querySelector("input");
const nameArea = document.getElementById("name");

function getLogin(event) {
  event.preventDefault();
  localStorage.setItem("username", nameInput.value);
  printname();
}

function printname() {
  const name = localStorage.getItem("username");
  document.querySelector("#name span").innerHTML = `${name}의 TODO LIST`;
  loginForm.classList.toggle("hidden");
  nameArea.classList.toggle("hidden");
  console.log(`${name} 로그인`);
}

if (localStorage.getItem("username")) {
  printname();
}

loginForm.addEventListener("submit", getLogin);
