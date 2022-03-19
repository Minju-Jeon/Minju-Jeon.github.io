const city = document.querySelector("#city span");
const weatherIcon = document.querySelector("#weatherIcon i");
const weatherDesc = document.querySelector("#weatherDesc span");
const temp= document.querySelector("#temp span");

const API_KEY = "b2a5e987c96963f6209407e17abbdb9d";

function getWeatherIcon(weather) {
  switch (weather) {
    case "Clear":
      weather = "fa-sun";
      break;

    case "Clouds":
       weather =  "fa-cloud";
       break;

    case "Rain":
    case "Drizzle":
      weather =  "fa-cloud-rain";
      break;

    case "Thunderstorm":
      weather =  "fa-cloud-bolt";
      break;

    case "Snow":
      weather =  "fa-snowflake";
      break;

    default:
      weather =  "fa-smog";
  }
  return weather;
}

function onGeoOk(position) {
  const lat = position.coords.latitude;
  const lon = position.coords.longitude;
  const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`;
  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      const weatherData = `${data.weather[0].main}`;
      weatherIcon.classList.add(getWeatherIcon(weatherData));

      weatherDesc.innerHTML = `${data.weather[0].description}`;
      temp.innerHTML = `${data.main.temp}℃`;
      city.innerHTML = `${data.name}`;
    
      
    });
};

function onGeoError() {
  city.innerHTML = "확인된 위치정보가 없습니다.";
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
