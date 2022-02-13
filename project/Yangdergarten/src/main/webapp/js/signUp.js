let id_ch = false;
let name_ch = false;
let pw_ch = false;
let pwch_ch = false;
let phone_ch = false;

$('#id').keyup(function() {
	const regxid = /^[A-Za-z | 0-9]{3,15}$/; //id
	if ($('#id').val() == "") {
		$('.chid').text("아이디를 입력해주세요. ");
		id_ch = false;
	} else if (!regxid.test($('#id').val())) {
		$('.chid').text("3~15자 이내 영문자,숫자 입력해주세요. ");
		id_ch = false;
	} else {
		$('.chid').text("");
		id_ch = true;
	}
}); //ID 확인

$('#mname').keyup(function() {
	const regxname = /^[가-힣]{2,5}$/; //이름
	if ($('#mname').val() == "") {
		$('.chname').text("이름을 입력해주세요. ");
		name_ch = false;
	} else if (!regxname.test($('#mname').val())) {
		$('.chname').text("2~5자의 한글을 입력해주세요. ");
		name_ch = false;
	} else {
		$('.chname').text("");
		name_ch = true;
	}
}); //이름 확인

$('#pwd').keyup(function() {
			const regxpwd = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}$/; //pwd
			if ($('#pwd').val() == "") {
				$('.chpwd').text("패스워드를 입력해주세요. ");
				pwd_ch = false;
			} else if (!regxpwd.test($('#pwd').val())) {
				$('.chpwd').text("특수문자 최소 1개 포함한 영문과 숫자를 입력해주세요. ");
				pwd_ch = false;
			} else {
				$('.chpwd').text("");
				pwd_ch = true;
			}
		}); //패스워드 확인

$('#checkpwd').keyup(function() {
	if ($('#checkpwd').val() != $('#pwd').val()) {
		$('.chpwd_ch').text("입력한 패스워드가 일치하지 않습니다. ");
		pwdch_ch = false;
	} else {
		$('.chpwd_ch').text("");
		pwdch_ch = true;
	}
}); //패스워드 재확인

$('#phone').keyup(function() {
			const regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
			if ($('#phone').val() == "") {
				$('.chphone').text("아이디를 입력해주세요. ");
				phone_ch = false;
			} else if (!regxid.test($('#phone').val())) {
				$('.chphone').text("올바른 전화번호를 입력하세요. ");
				phone_ch = false;
			} else {
				$('.chphone').text("");
				phone_ch = true;
			}
			
			//ajax : 학부모 핸드폰 번호로 원아정보 불러오기
			let pnum = $('#phone').val()
			
			$.ajax({
				type:"GET",
				url:"StudentSearchByPhone",
				data:{"pnum":pnum},
				datatype:"JSON",
				success: function(responsedata){
					var data = JSON.parse(responsedata);
					console.log(responsedata);
					
					$.each(data, function(obj){
						
/*						$('#bList').append("<tr>"
											+"<th>"+원아번호+"<th>"
											+"<th>"+원아이름+"<th>"
											+"<th>"+반이름+"<th>"
											+"</tr>"
											+"<tr>"
											+"<td>"+obj.val().+"<td>");
*/					});
				}
			});
			
		});

	




