$(document).ready(function(){
    // 요소 선택


    const totalCash = document.querySelector('.cash_point_total span');
    const useCashPoints = document.querySelectorAll(".use_number input");
    const availables = document.querySelectorAll(".available span");
    const prodTotalPrice = document.querySelector('.prod_total_price span');
    const regularPrice = document.querySelector('.regular_price span');
    const salePrice = document.querySelector('.sale_price span');
    const orderTotalPrice = document.querySelector('.order_total_price span');
    const pendingPoints = document.querySelector('.pending_points span');
    const pointPrice = document.querySelector('.point_price span');

    // 문자열에서 쉼표를 제거하고 숫자로 변환하는 함수
    function parseCurrency(value) {
        return parseInt(value.replace(/,/g, ''), 10); // 쉼표를 제거하고 숫자로 변환
    }

    // 숫자를 쉼표 포함 형식으로 변환하는 함수
    function formatCurrency(value) {
        return value.toLocaleString();
    }

    // 숫자가 유효한지 검사하는 함수 (10의 배수인지 확인)
    function isValidValue(value) {
        return value % 10 === 0;
    }

    // 입력값을 10의 배수로 내림 처리하는 함수
    function roundToNearestTen(value) {
        return Math.floor(value / 10) * 10;
    }

    // 총합을 계산하여 totalCash와 pointPrice에 출력하는 함수
    // function updateTotalCash() {
    //     let total = 0;

    //     useCashPoints.forEach((input, index) => {
    //         const maxAvailable = parseCurrency(availables[index].textContent); // 보유한 최대 값
    //         let value = parseInt(input.value) || 0; // 입력 값이 숫자가 아닌 경우 0으로 처리

    //         // 최소값을 0으로 설정하고 10의 배수로 내림 처리
    //         if (value < 0) {
    //             value = 0;
    //         } else {
    //             // value = roundToNearestTen(value); // 10의 배수로 내림 처리
    //         }

    //         // 최대 값을 초과하지 않도록 설정
    //         if (value > maxAvailable) {
    //             value = maxAvailable;
    //         }

    //         input.value = value; // 입력 필드에 유효한 값 설정
    //         total += value;
    //     });

    //     // 총합을 쉼표가 포함된 형식으로 totalCash와 pointPrice 요소에 출력
    //     const formattedTotal = formatCurrency(total);
    //     totalCash.textContent = formattedTotal;
    //     pointPrice.textContent = formattedTotal; // pointPrice에 총합을 표시

    //     calculatePrices(); // 총합 업데이트 후 가격 계산
    // }

    // 모든 캐시 또는 포인트를 사용할 때 호출되는 함수
    // function useAllCashOrPoints(index) {
    //     const maxAvailable = parseCurrency(availables[index].textContent); // 보유한 최대 값
    //     useCashPoints[index].value = maxAvailable; // 입력 필드에 최대 값을 쉼표 없이 설정
    //     updateTotalCash(); // 총합 업데이트
    // }

    // // 두 input 요소에 이벤트 리스너를 추가하여 값이 변경될 때마다 총합을 업데이트
    // useCashPoints.forEach(input => {
    //     input.addEventListener('input', () => {
    //         // 입력값이 유효한지 확인하고 총합을 업데이트
    //         let numericValue = parseCurrency(input.value);
    //         numericValue = numericValue >= 0 ? numericValue : 0; // 최소값을 0으로 설정
    //         // numericValue = roundToNearestTen(numericValue); // 10의 배수로 내림 처리
    //         const maxAvailable = parseCurrency(availables[Array.from(useCashPoints).indexOf(input)].textContent); // 보유한 최대 값
    //         if (numericValue > maxAvailable) {
    //             numericValue = maxAvailable;
    //         }
    //         input.value = numericValue;
    //         updateTotalCash();
    //     });
    // });

    // 각 "use_all" 버튼에 이벤트 리스너 추가
    // const useAllButtons = document.querySelectorAll('.use_all');
    // useAllButtons.forEach((button, index) => {
    //     button.addEventListener('click', () => useAllCashOrPoints(index));
    // });

    // 할인 계산, 포인트 적용 후 최종 금액 및 적립 포인트 계산 함수
    function calculatePrices() {
        const originalPrice = parseCurrency(regularPrice.textContent);// 상품 가격
        const discount = Math.floor(originalPrice * 0.1); // 10% 할인 계산
        const discountedPrice = originalPrice - discount; // 할인된 가격

        // const pointsApplied = parseCurrency(useCashPoints[1].value) || 0; // 두 번째 input에서 포인트 값 가져오기
        // const cashApplied = parseCurrency(useCashPoints[0].value) || 0; // 첫 번째 input에서 캐시 값 가져오기

        // 최종 결제 금액 계산 (할인된 가격에서 포인트와 캐시를 차감)
        // const finalPrice = discountedPrice - pointsApplied - cashApplied;
        const finalPrice = discountedPrice;

        // 적립 포인트 계산 (최종 결제 금액의 5%를 10단위로 내림)
        let earnedPoints = finalPrice * 0.05;
        earnedPoints = Math.floor(earnedPoints / 10) * 10; // 10단위로 내림 처리

        // 값을 DOM에 업데이트
        prodTotalPrice.textContent = formatCurrency(originalPrice); // 상품 가격을 prod_total_price로 출력
        salePrice.textContent = formatCurrency(discount); // 할인가격
        orderTotalPrice.textContent = formatCurrency(finalPrice); // 최종 결제 금액
        pendingPoints.textContent = formatCurrency(earnedPoints); // 적립 포인트
    }

    //직접입력
    const deliveryRequest = document.getElementById("deliveryRequest");
    const directInputBox = document.getElementById("directInput_box");
    
    deliveryRequest.addEventListener('change', function(){
        console.log(this.value);
        if (this.value == 4) {
            const directInput = document.createElement('input');
            directInput.id = "directInput";
            directInput.name = "";
            directInput.type = "text";
            directInput.placeholder = "배송에 대한 요청사항을 적어주세요.";
            directInput.classList.add("form-control");
            directInputBox.classList.add("active");
            directInputBox.appendChild(directInput);
        }else{
            directInputBox.classList.remove("active");
            directInputBox.children[0].remove();
        }
    });
    
    //
    const AddrChangeBtn = document.getElementById("addressSelected");
    if(AddrChangeBtn){
        AddrChangeBtn.addEventListener('change', function(){
            const form = document.querySelector("form");
            const checked = document.querySelector('input[name="isSelected"]');
            checked.value = true;
            const isSelected = checked.value;
            sessionStorage.setItem("isSelected", isSelected);
            const selectedProduct = document.querySelector('select[id="addressSelected"]').value;
            sessionStorage.setItem("selectedProduct", selectedProduct);
            form.action = "product_buy";
            form.submit();
            // $.ajax({
            //     async: true 
            //     ,cache: false
            //     ,type: "post"
            //     /* ,dataType:"json" */
            //     // ,url: "/v1/infra/member/loginUsrProc"
            //     ,url: "/buyAddressChange"
            //     /* ,data : $("#formLogin").serialize() */
            //     ,data : { "daSeq" : $("#addressSelected").val().trim(), "seq" : $("#prod_seq").val() }//, "autoLogin" : $("#autoLogin").is(":checked")}
            //     ,success: function(response) {
            //         if(response.rt == "success") {
            //             alert("성공");
            //             // 성공적으로 응답을 받았을 때
            //             const user = response.data; // 업데이트된 사용자 정보
            //             console.log(user);
                        
            //             // 사용자 정보가 유효한지 확인
            //             if (user) {
            //                 document.querySelector(".address_name").innerText = user.addressName || '주소 없음';
            //                 console.log(user.RecipientName);
            //                 document.querySelector(".rec_name").innerText = user.recipientName || '수신자 없음';
    
            //                 // 전화번호 형식 변경
            //                 const phoneNumber = user.recipientPhone;
            //                 let formattedPhone = '';
    
            //                 if (phoneNumber) {
            //                     formattedPhone = phoneNumber.startsWith('02') 
            //                         ? `${phoneNumber.substring(0, 2)}-${phoneNumber.substring(2, 6)}-${phoneNumber.substring(6)}`
            //                         : `${phoneNumber.substring(0, 3)}-${phoneNumber.substring(3, 7)}-${phoneNumber.substring(7)}`;
            //                 } else {
            //                     formattedPhone = '전화번호 없음'; // 또는 다른 적절한 기본값
            //                 }
            //                 console.log(user.recipientName);
    
            //                 document.querySelector(".rec_phone").innerText = formattedPhone;
    
            //                 // 나머지 주소 정보 업데이트
            //                 document.querySelector(".post_number").innerText = user.daZonecode || '우편번호 없음';
            //                 document.querySelector(".ship_address").innerText = user.daRoadAddress || '도로명 주소 없음';
            //                 document.querySelector(".daExtraAddress").innerText = user.daExtraAddress || '추가 주소 없음';
            //                 document.querySelector(".detail_address").innerText = user.daDetailAddress || '상세 주소 없음';
            //             }
                        
            //         } else {
            //             alert("실패");
            //         }
            //     }
            //     ,error : function(jqXHR, textStatus, errorThrown){
            //         alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
            //     }
            // });
        });
    }

    

    // 

    // 페이지 로드 시 초기 계산 수행
    // updateTotalCash();
    calculatePrices();
    
})