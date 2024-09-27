 //카카오 우편번호
 
 //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
 function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }

            // 위도 및 경도 좌푯값 구하기
            const geocoder = new kakao.maps.services.Geocoder();
            var x = 0;
            var y = 0;
            
            geocoder.addressSearch(data.roadAddress, (result, status) => {
                if (status === kakao.maps.services.Status.OK) {
                    console.log('위도 : ' + result[0].y);
                    console.log('경도 : ' + result[0].x);
                    x = result[0].x;
                    y = result[0].y;
                    console.log("안의 x: "+ x +", y: "+y);
                    document.getElementById("def_longitude").value = x;
                    document.getElementById("def_latitude").value = y;

                    //지도api
                    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
                    container.style.width = "100%";
                    container.style.height = "200px";
                    container.style.border = "1px solid #000";
                    var options = { //지도를 생성할 때 필요한 기본 옵션
                        center: new kakao.maps.LatLng(y, x), //지도의 중심좌표.
                        level: 3 //지도의 레벨(확대, 축소 정도)
                    };

                    // 지도를 생성구문 
                    var map = new kakao.maps.Map(container, options); 

                    // 지도에 마커를 생성하고 표시한다
                    var marker = new kakao.maps.Marker({
                        position: new kakao.maps.LatLng(y, x), // 마커의 좌표
                        map: map // 마커를 표시할 지도 객체
                    });
                }
            });
            console.log("밖의 x: "+ x +", y: "+y);
        }
    }).open();
}