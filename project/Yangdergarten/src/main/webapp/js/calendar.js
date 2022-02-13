var calendar = null;
let origianlEvents

$(document).ready(function() {
	$.ajax({
		url: `${contextPath}/events`,
		type: "GET",
		contentType: 'application/json',
		success: function(events) {
			origianlEvents = events
			init(events)
		}
	})
});

function init(events) {
	var Calendar = FullCalendar.Calendar;
	var Draggable = FullCalendar.Draggable;

	var containerEl = document.getElementById('external-events');
	var calendarEl = document.getElementById('calendar');
	var checkbox = document.getElementById('drop-remove');

	// initialize the external events
	// -----------------------------------------------------------------

	new Draggable(containerEl, {
		itemSelector: '.fc-event',
		eventData: function(eventEl) {
			return {
				title: eventEl.innerText
			};
		}
	});

	// initialize the calendar
	// -----------------------------------------------------------------

	calendar = new Calendar(calendarEl, {
		themeSystem: 'bootstrap',
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,timeGridDay'
		},
		selectable: true,
		editable: true, //수정가능여부
		droppable: true, // this allows things to be dropped onto the calendar
		drop: function(info) {
			// is the "remove after drop" checkbox checked?
			if (checkbox.checked) {
				// if so, remove the element from the "Draggable Events" list
				info.draggedEl.parentNode.removeChild(info.draggedEl);
			}
		},
		events: events.map(({ title, startDate, endDate }) => ({ title, start: startDate, end: endDate })),
		locale: 'ko'
	});

	calendar.render();

	$('#saveBtn').click(function() {
		allSave();
	});

	$('#deleteBtn').click(function () {
		allDelete();
	});
	
	$('#newEvent').keyup(function(){
		let title = $('#newEvent').val();
		$('#newEventDiv').text(title);
	});
}
//1. 전체 이벤트 데이터를 추출		2. ajax로 서버에 전송하여 DB에 저장
function allSave() {
	const allEvent = calendar.getEvents();
	savedata(allEvent.map(event => ({
			title: event.title,
			startDate: moment(event.start).format('YYYY-MM-DD'),
			endDate: moment((event.end || event.start)).format('YYYY-MM-DD')
	})));
}

function savedata(jsondata) {
	$.ajax({
		type: 'POST',
		url: 'saveCalendar',
		contentType: 'application/json',
		data: JSON.stringify(jsondata),
		dataType: 'json',
		async: false,
		success: function(responsedata) {
			console.log(responsedata);
		}
	});
}


//1. 전체 이벤트 데이터 삭제		2. ajax로 서버에 sql전송하여 DB에 저장된 데이터도 모두 삭제
function allDelete(){
	calendar.removeAllEvents();
	
	$.ajax({
		type:'POST',
		url:'deleteCalendar',
		data: {"sql":"delete from CALENDAR"},
		dataType:'text',
		success: function(responsedata){
			console.log(responsedata);
		}
	});
}