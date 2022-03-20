const date = document.querySelector("#clock .date");
const time = document.querySelector("#clock .time .time");
const switch24btn = document.querySelector("#clock #swith24hbtn");
const dayOrNight = document.querySelector("#clock .dayOrNight");
const T = 24;
const F = 12;

//시간표기법 변경
function switch24() {
    if (localStorage.getItem("24h") == T) {
        //24시간 표기중 버튼 클릭할경우 12시간 표기로
        localStorage.setItem("24h", F);
        console.log("24h: false");
    } else {
        //12시간 표기중 버튼 클릭할경우 24시간 표기로
        localStorage.setItem("24h", T);
        console.log("24h: true");
    }
    dayOrNight.classList.toggle("hidden");
    getClock();
}

//시계출력
function getClock() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const day = now.getDate();
    const weekDay = now.toString().slice(0, 3);
    const hour = now.getHours().toString().padStart(2, "0");
    const min = now.getMinutes().toString().padStart(2, "0");
    const sec = now.getSeconds().toString().padStart(2, "0");

    date.innerHTML = `${year}/${month}/${day} ${weekDay}`;
    //설정값에 따라 12~24시간 표기법으로 변경
    if (localStorage.getItem("24h") == T) {
        print24Clock(hour, min, sec);
    } else {
        print12Clock(hour, min, sec);
    }
}

//24시간표기
function print24Clock(hour, min, sec) {
    switch24btn.innerHTML = "to 12h";
    time.innerHTML = `${hour}:${min}:${sec}`;
}

//12시간표기
function print12Clock(hour, min, sec) {
    const hourFor12 = hour % 12;
    if (hour > 11) {
        if (hour == 12) {
            hourFor12 = 12;
        }
        //오전오후 변경
        dayOrNight.innerHTML = "오후";
    } else {
        dayOrNight.innerHTML = "오전";
    }
    switch24btn.innerHTML = "to 24h";
    time.innerHTML = `${hourFor12.toString().padStart(2, "0")}:${min}:${sec}`;
}

if (localStorage.getItem("24h") == T && !dayOrNight.classList.contains("hidden")) {
    dayOrNight.classList.toggle("hidden");
}

getClock();
setInterval(getClock, 1000);
//시간표기법 변경
switch24btn.addEventListener("click", switch24);