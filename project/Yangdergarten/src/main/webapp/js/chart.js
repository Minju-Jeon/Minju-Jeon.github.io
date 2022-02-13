
/*
 make url example
//////////////////////////////////
http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst
?serviceKey=f3tTrUF%2FeWMAdERZkFf9HDRn4WO%2BbNxAvV9gV%2Bzh8bU9rF5ZzQM1OjEX0%2FByTTUh41uaPOU%2FgruxtRJwsOEA4w%3D%3D
&pageNo=1
&numOfRows=10
&dataType=JSON
&base_date=20210930
&base_time=1200
&nx=55
&ny=127

*/
const servicekey = 'f3tTrUF%2FeWMAdERZkFf9HDRn4WO%2BbNxAvV9gV%2Bzh8bU9rF5ZzQM1OjEX0%2FByTTUh41uaPOU%2FgruxtRJwsOEA4w%3D%3D';
google.charts.load("current", {packages:["gauge"]});
google.charts.setOnLoadCallback(drawgaugeChart);

function drawgaugeChart() {
    let d = new Date();
    let year = (d.getFullYear()).toString();
    let date = (d.getDate()).toString();
    let hour = (d.getHours()-1).toString();
    let minute = (d.getMinutes()).toString();
    let month = (d.getMonth()+1).toString();
    if(month.length == 1){
        month = "0"+month;
    }
    if(date.length == 1){
        date = "0"+date;
    }

    if(hour.length == 1){
        hour = "0"+hour;
    }

    if(minute.length == 1){
        minute = "0"+minute;
    }

    let url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

    let queryParams = '?' + encodeURIComponent('serviceKey') + '='+servicekey; /*Service Key*/
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10');
    queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON');
    queryParams += '&' + encodeURIComponent('base_date') + '=' + encodeURIComponent(year+month+date);
    queryParams += '&' + encodeURIComponent('base_time') + '=' + encodeURIComponent(hour+minute);
    queryParams += '&' + encodeURIComponent('nx') + '=' + encodeURIComponent('38');
    queryParams += '&' + encodeURIComponent('ny') + '=' + encodeURIComponent('127');

    console.log(url+queryParams);
    console.log(year+month+date);
    console.log(hour+minute);


    $.getJSON(url+queryParams,function (jsondata){
        console.log(jsondata);
        let ultrasrt = jsondata.response.body.items.item;
        let ultrasrtarr = [];

        ultrasrtarr.push(['Label', 'Value']);
        ultrasrtarr.push(['습도',Number(ultrasrt[1].obsrValue)]);
        ultrasrtarr.push(['온도',Number(ultrasrt[3].obsrValue)]);
        ultrasrtarr.push(['강수량',Number(ultrasrt[2].obsrValue)])

        console.log(ultrasrtarr);
        var data = google.visualization.arrayToDataTable(ultrasrtarr);

        console.log(data);

        var options = {
            width: 900, height: 500,
            redFrom: 90, redTo: 100,
            yellowFrom:75, yellowTo: 90,
            minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

        chart.draw(data, options);

    })

}
// side scroll

const wrapper = document.getElementById('wrapperElement');
let currentIndex = 0;
const margin = 8;
// const element = wrapper.querySelectorAll('.element');
const length = 4;
console.log(length);
const transitionDuration = 300;
let isInTransition = false;
let transitionStart = 0;

const scrollWrapper = (e) => {
    if (e.deltaY > 0) {
        currentIndex = Math.min(currentIndex + 1, length - 1);
    } else if (e.deltaY < 0) {
        currentIndex = Math.max(currentIndex - 1, 0);
    }
    wrapper.setAttribute('style', `transform: translateX(${-currentIndex * 100}%)`);
};

const onWheel = (e) => {
    if (!isInTransition) {
        scrollWrapper(e);
        isInTransition = true;
        transitionStart = Date.now();
    } else {
        if (Date.now() > transitionStart + transitionDuration) {
            isInTransition = false;
            onWheel(e);
        }
    }
};

////시계

function clock() {// We create a new Date object and assign it to a variable called "time".
    var time = new Date(),

        // Access the "getHours" method on the Date object with the dot accessor.
        hours = time.getHours(),

        // Access the "getMinutes" method with the dot accessor.
        minutes = time.getMinutes(),


        seconds = time.getSeconds();

    document.querySelectorAll('.clock')[0].innerHTML = harold(hours) + ":" + harold(minutes) + ":" + harold(seconds);

    function harold(standIn) {
        if (standIn < 10) {
            standIn = '0' + standIn
        }
        return standIn;
    }
}
setInterval(clock, 1000);
window.addEventListener('wheel', _.throttle(onWheel, 50));

////
google.charts.load("current", {packages:['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ["Element", "Density", { role: "style" } ],
        ["Copper", 8.94, "#b87333"],
        ["Silver", 10.49, "silver"],
        ["Gold", 19.30, "gold"],
        ["Platinum", 21.45, "color: #e5e4e2"]
    ]);

    var view = new google.visualization.DataView(data);
    view.setColumns([0, 1,
        { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
        2]);

    var options = {
        title: "Density of Precious Metals, in g/cm^3",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
        backgroundColor: '#0359aeCC',
        is3D: true
    };
    var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
    chart.draw(view, options);


}

google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawcolumnChart);

//column

/*
http://apis.data.go.kr/1360000/LivingWthrIdxServiceV2/getAirDiffusionIdxV2?
serviceKey=f3tTrUF%2FeWMAdERZkFf9HDRn4WO%2BbNxAvV9gV%2Bzh8bU9rF5ZzQM1OjEX0%2FByTTUh41uaPOU%2FgruxtRJwsOEA4w%3D%3D&pageNo=10
&numOfRows=10
&dataType=JSON
&areaNo=1100000000
&time=2021093018
 */
function drawcolumnChart() {

    let d = new Date();
    let year = (d.getFullYear()).toString();
    let date = (d.getDate()).toString();
    let hour = (d.getHours()-1).toString();
    let minute = (d.getMinutes()).toString();
    let month = (d.getMonth()+1).toString();
    if(month.length == 1){
        month = "0"+month;
    }
    if(date.length == 1){
        date = "0"+date;
    }

    if(hour.length == 1){
        hour = "0"+hour;
    }

    if(minute.length == 1){
        minute = "0"+minute;
    }
    // var url = 'http://apis.data.go.kr/1360000/LivingWthrIdxServiceV2/getUVIdxV2'; /*URL*/
    // var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+'서비스키'; /*Service Key*/
    // queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    // queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
    // queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('XML'); /**/
    // queryParams += '&' + encodeURIComponent('areaNo') + '=' + encodeURIComponent('1100000000'); /**/
    // queryParams += '&' + encodeURIComponent('time') + '=' + encodeURIComponent('2021070618'); /**/

    let url = "http://apis.data.go.kr/1360000/LivingWthrIdxServiceV2/getAirDiffusionIdxV2";
    let queryParams = '?' + encodeURIComponent('serviceKey') + '='+servicekey; /*Service Key*/
    queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
    queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10');
    queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON');
    queryParams += '&' + encodeURIComponent('areaNo') + '=' + encodeURIComponent('1100000000');
    queryParams += '&' + encodeURIComponent('time') + '=' + encodeURIComponent(year+month+date+hour);

    console.log(url+queryParams);

    $.getJSON(url+queryParams,function (jsondata){
        console.log(jsondata);
        let ultrasrt = jsondata.response.body.items.item;
        let ultrasrtarr = [];

        ultrasrtarr.push(['hour', '대기확산지수']);
        ultrasrtarr.push(['h3',Number(ultrasrt[0].h3)]);
        ultrasrtarr.push(['h6',Number(ultrasrt[0].h6)]);
        ultrasrtarr.push(['h9',Number(ultrasrt[0].h9)]);
        ultrasrtarr.push(['h12',Number(ultrasrt[0].h12)]);
        ultrasrtarr.push(['h15',Number(ultrasrt[0].h15)]);
        ultrasrtarr.push(['h18',Number(ultrasrt[0].h18)]);
        ultrasrtarr.push(['h21',Number(ultrasrt[0].h21)]);
        ultrasrtarr.push(['h24',Number(ultrasrt[0].h24)]);
        ultrasrtarr.push(['h27',Number(ultrasrt[0].h27)]);
        ultrasrtarr.push(['h30',Number(ultrasrt[0].h30)]);
        ultrasrtarr.push(['h33',Number(ultrasrt[0].h33)]);
        ultrasrtarr.push(['h36',Number(ultrasrt[0].h36)]);
        ultrasrtarr.push(['h39',Number(ultrasrt[0].h39)]);
        ultrasrtarr.push(['h42',Number(ultrasrt[0].h42)]);
        ultrasrtarr.push(['h45',Number(ultrasrt[0].h45)]);
        ultrasrtarr.push(['h48',Number(ultrasrt[0].h48)]);
        ultrasrtarr.push(['h51',Number(ultrasrt[0].h51)]);
        ultrasrtarr.push(['h54',Number(ultrasrt[0].h54)]);
        ultrasrtarr.push(['h57',Number(ultrasrt[0].h57)]);
        ultrasrtarr.push(['h60',Number(ultrasrt[0].h60)]);
        ultrasrtarr.push(['h63',Number(ultrasrt[0].h63)]);
        ultrasrtarr.push(['h66',Number(ultrasrt[0].h66)]);
        ultrasrtarr.push(['h69',Number(ultrasrt[0].h69)]);
        ultrasrtarr.push(['h72',Number(ultrasrt[0].h72)]);
        ultrasrtarr.push(['h75',Number(ultrasrt[0].h75)]);
        ultrasrtarr.push(['h78',Number(ultrasrt[0].h78)]);


        console.log(ultrasrtarr);
        var data = google.visualization.arrayToDataTable(ultrasrtarr);

        var options = {
            chart: {
                title: '대기확산지수',
                subtitle: '3h~78h 대기확산지수',
            }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));


    })

}
google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawcolumnChart2);

var foodurl = 'http://apis.data.go.kr/1360000/HealthWthrIdxServiceV2/getFoodPoisoningIdxV2'; /*URL*/
var foodqueryParams = '?' + encodeURIComponent('serviceKey') + '='+servicekey; /*Service Key*/
foodqueryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
foodqueryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
foodqueryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /**/
foodqueryParams += '&' + encodeURIComponent('areaNo') + '=' + encodeURIComponent('1100000000'); /**/
foodqueryParams += '&' + encodeURIComponent('time') + '=' + encodeURIComponent(getdate()); /**/

var coldurl = 'http://apis.data.go.kr/1360000/HealthWthrIdxServiceV2/getColdIdxV2'; /*URL*/
var coldqueryParams = '?' + encodeURIComponent('serviceKey') + '='+servicekey; /*Service Key*/
coldqueryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
coldqueryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
coldqueryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /**/
coldqueryParams += '&' + encodeURIComponent('areaNo') + '=' + encodeURIComponent('1100000000'); /**/
coldqueryParams += '&' + encodeURIComponent('time') + '=' + encodeURIComponent(getdate()); /**/

var uvurl = 'http://apis.data.go.kr/1360000/LivingWthrIdxServiceV2/getUVIdxV2'; /*URL*/
var uvqueryParams = '?' + encodeURIComponent('serviceKey') + '='+servicekey; /*Service Key*/
uvqueryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
uvqueryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
uvqueryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('JSON'); /**/
uvqueryParams += '&' + encodeURIComponent('areaNo') + '=' + encodeURIComponent('1100000000'); /**/
uvqueryParams += '&' + encodeURIComponent('time') + '=' + encodeURIComponent(getdate()); /**/
console.log(foodurl+foodqueryParams);
console.log(coldurl+coldqueryParams);
console.log(uvurl+uvqueryParams);

var food = null;
var cold = null;
var uv = null;
$.ajaxSetup({
    async: false
});

$.getJSON(foodurl+foodqueryParams,function (jsondata){
    console.log(jsondata);
    food = jsondata.response.body.items.item;
})

$.getJSON(coldurl+coldqueryParams,function (jsondata) {
    console.log(jsondata);
    cold = jsondata.response.body.items.item;
})

$.getJSON(uvurl+uvqueryParams, function (jsondata) {
    console.log(jsondata);
    uv = jsondata.response.body.items.item;
})

function getdate() {
    let d = new Date();
    let year = (d.getFullYear()).toString();
    let date = (d.getDate()).toString();
    let hour = (d.getHours()-1).toString();
    let minute = (d.getMinutes()).toString();
    let month = (d.getMonth()+1).toString();
    if(month.length == 1){
        month = "0"+month;
    }
    if(date.length == 1){
        date = "0"+date;
    }

    if(hour.length == 1){
        hour = "0"+hour;
    }

    if(minute.length == 1){
        minute = "0"+minute;
    }

    return year+month+date+hour;
}
function drawcolumnChart2() {

    let d = new Date();
    let year = (d.getFullYear()).toString();
    let date = (d.getDate()).toString();
    let hour = (d.getHours()-1).toString();
    let minute = (d.getMinutes()).toString();
    let month = (d.getMonth()+1).toString();
    if(month.length == 1){
        month = "0"+month;
    }
    if(date.length == 1){
        date = "0"+date;
    }

    if(hour.length == 1){
        hour = "0"+hour;
    }

    if(minute.length == 1){
        minute = "0"+minute;
    }
    // var url = 'http://apis.data.go.kr/1360000/HealthWthrIdxServiceV2/getAsthmaIdxV2'; /*URL*/
    // var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+'서비스키'; /*Service Key*/
    // queryParams += '&' + encodeURIComponent('serviceKey') + '=' + encodeURIComponent('인증키 (URL Encode)'); /**/
    // queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
    // queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
    // queryParams += '&' + encodeURIComponent('dataType') + '=' + encodeURIComponent('XML'); /**/
    // queryParams += '&' + encodeURIComponent('areaNo') + '=' + encodeURIComponent('1100000000'); /**/
    // queryParams += '&' + encodeURIComponent('time') + '=' + encodeURIComponent('2021070618'); /**/

    /*
    http://apis.data.go.kr/1360000/HealthWthrIdxServiceV2/getFoodPoisoningIdxV2?
    serviceKey=f3tTrUF%2FeWMAdERZkFf9HDRn4WO%2BbNxAvV9gV%2Bzh8bU9rF5ZzQM1OjEX0%2FByTTUh41uaPOU%2FgruxtRJwsOEA4w%3D%3D
    &numOfRows=10
    &pageNo=1
    &dataType=JSON
    &areaNo=1100000000
    &time=2021093018
     */
    /*
    http://apis.data.go.kr/1360000/HealthWthrIdxServiceV2/getColdIdxV2
     */
    /*
    http://apis.data.go.kr/1360000/LivingWthrIdxServiceV2/getUVIdxV2?
    serviceKey=f3tTrUF%2FeWMAdERZkFf9HDRn4WO%2BbNxAvV9gV%2Bzh8bU9rF5ZzQM1OjEX0%2FByTTUh41uaPOU%2FgruxtRJwsOEA4w%3D%3D
    &pageNo=1
    &numOfRows=10
    &dataType=JSON
    &areaNo=1100000000
    &time=2021100118
     */

    let dataarr = [];
/*
today":"6",
"tomorrow":"6",
"dayaftertomorrow":"5",
 */
    console.log(food);
    console.log(uv);
    console.log(cold)

    dataarr.push(['시기','식중독지수','자외선지수','감기가능지수']);
    dataarr.push(['오늘',Number(food[0].today),Number(uv[0].today),cold[0].today]);
    dataarr.push(['내일',food[0].tomorrow, uv[0].tomorrow, cold[0].tomorrow]);
    dataarr.push(['모레',food[0].dayaftertomorrow,uv[0].dayaftertomorrow,cold[0].dayaftertomorrow]);

    var data = google.visualization.arrayToDataTable(dataarr);

    var options = {
        chart: {
            title: '종합',
            subtitle: '종합',
        }
    };

    var chart = new google.charts.Bar(document.getElementById('columnchart_material1'));

    chart.draw(data, google.charts.Bar.convertOptions(options));

}