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


function placeholderChangerIn() {
    nameInput.placeholder = "이름을 적고 엔터";
}

function placeholderChangerOut() {
    const temp = nameInput.placeholder.substring[0, 1];
    nameInput.placeholder = temp;
}

if (localStorage.getItem("username")) {
    printname();
}


nameInput.addEventListener("focus", placeholderChangerIn);
loginForm.addEventListener("submit", getLogin);