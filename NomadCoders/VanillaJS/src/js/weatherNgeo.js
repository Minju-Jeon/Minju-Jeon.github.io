const API_KEY = "b2a5e987c96963f6209407e17abbdb9d";

function onGeoOk(position) {
  const lat = position.coords.latitude;
  const lon = position.coords.longitude;
  const unit = "metric";
  const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&unit=${unit}`;
  fetch(url).then(response => response.json()).then(data => {
      const name = data.name;
      const weather =  data.weather[0].main;
  });
  const weather = document.querySelector("#weather span");
  const city = document.querySelector("#city span");
}

function onGeoError() {
  alert("No Location Information");
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
