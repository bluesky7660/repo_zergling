function updateValue(input) {
    let value = input.value.replace(/[^0-9]/g, ''); // 숫자 외 문자를 제거
    if (value) {
        input.value = new Intl.NumberFormat('ko-KR').format(value); // 천 단위 구분 기호 추가
    }
}
$(document).ready(function(){
    //양방향 range

    // // 입력 요소와 슬라이더의 Thumb 및 범위 요소를 가져옵니다.
    // const leftInput = document.getElementById("price_range1");
    // const rightInput = document.getElementById("price_range2");
    // const leftInput2 = document.getElementById("price_range3");
    // const rightInput2 = document.getElementById("price_range4");

    // const leftThumb = document.querySelector(".price_range_box .range-control > .thumb.left");
    // const rightThumb = document.querySelector(".price_range_box .range-control > .thumb.right");
    // const rangeFill = document.querySelector(".price_range_box .range-control > .range");

    // const leftThumb2 = document.querySelector(".review_range_box .range-control > .thumb.left");
    // const rightThumb2 = document.querySelector(".review_range_box .range-control > .thumb.right");
    // const rangeFill2 = document.querySelector(".review_range_box .range-control > .range");

    // // 왼쪽 슬라이더의 값을 설정하고 관련된 Thumb와 범위를 업데이트합니다.
    // const updateLeftValue = () => {
    //     const _this = leftInput; // 현재 왼쪽 입력 요소를 참조합니다.
    //     const [min, max] = [parseInt(_this.min), parseInt(_this.max)]; // 입력 범위의 최소값과 최대값을 파싱합니다.
        
    //     // 오른쪽 슬라이더의 값보다 10 작은 값으로 설정하여 교차되지 않도록 합니다.
    //     _this.value = Math.min(parseInt(_this.value), parseInt(rightInput.value) - 9500);
        
    //     // 입력 값의 퍼센트를 계산하여 Thumb와 범위의 위치를 설정합니다.
    //     const percent = ((_this.value - min) / (max - min)) * 100;
    //     leftThumb.style.left = percent + "%"; // 왼쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //     rangeFill.style.left = percent + "%"; // 왼쪽 범위의 위치를 퍼센트로 설정합니다.
    // };

    // //리뷰 leftrange
    // const updateLeftValue2 = () => {
    //     const _this = leftInput2; // 현재 왼쪽 입력 요소를 참조합니다.
    //     const [min, max] = [parseInt(_this.min), parseInt(_this.max)]; // 입력 범위의 최소값과 최대값을 파싱합니다.
        
    //     // 오른쪽 슬라이더의 값보다 10 작은 값으로 설정하여 교차되지 않도록 합니다.
    //     _this.value = Math.min(parseInt(_this.value), parseInt(rightInput2.value) - 1);
        
    //     // 입력 값의 퍼센트를 계산하여 Thumb와 범위의 위치를 설정합니다.
    //     const percent = ((_this.value - min) / (max - min)) * 100;
    //     leftThumb2.style.left = percent + "%"; // 왼쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //     rangeFill2.style.left = percent + "%"; // 왼쪽 범위의 위치를 퍼센트로 설정합니다.
    // };

    // // 오른쪽 슬라이더의 값을 설정하고 관련된 Thumb와 범위를 업데이트합니다.
    // const updateRightValue = () => {
    //     const _this = rightInput; // 현재 오른쪽 입력 요소를 참조합니다.
    //     const [min, max] = [parseInt(_this.min), parseInt(_this.max)]; // 입력 범위의 최소값과 최대값을 파싱합니다.
        
    //     // 왼쪽 슬라이더의 값보다 1 큰 값으로 설정하여 교차되지 않도록 합니다.
    //     _this.value = Math.max(parseInt(_this.value), parseInt(leftInput.value) + 9500);
        
    //     // 입력 값의 퍼센트를 계산하여 Thumb와 범위의 위치를 설정합니다.
    //     const percent = (((_this.value - min) / (max - min)) * 100);
    //     console.log(percent);
    //     console.log(_this.value);
    //     console.log("최소" + min);
    //     console.log("최대" + max);
    //     console.log("(((_this.value - min) / (max - min)) * 100 )" + (((_this.value - min) / (max - min)) * 100 ));
    //     console.log("(_this.value - min)" + (_this.value - min));
    //     console.log("(max - min)" + (max - min));
    //     console.log("(_this.value - 1) + 10: " + ((_this.value - 1) + 10));
    //     // if (_this.value == 100) {
    //     //     rightThumb.style.right = 101 - percent + "%"; // 오른쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //     //     rangeFill.style.right = 101 - percent + "%"; // 오른쪽 범위의 위치를 퍼센트로 설정합니다.
    //     // }else{
    //     //     rightThumb.style.right = 100 - percent + "%"; // 오른쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //     //     rangeFill.style.right = 100 - percent + "%"; // 오른쪽 범위의 위치를 퍼센트로 설정합니다.
    //     // }
    //     rightThumb.style.right = 100 - percent + "%"; // 오른쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //     rangeFill.style.right = 100 - percent + "%"; // 오른쪽 범위의 위치를 퍼센트로 설정합니다.
    // };

    // //리뷰 rightrange
    // const updateRightValue2 = () => {
    //     const _this = rightInput2; // 현재 오른쪽 입력 요소를 참조합니다.
    //     const [min, max] = [parseInt(_this.min), parseInt(_this.max)]; // 입력 범위의 최소값과 최대값을 파싱합니다.
        
    //     // 왼쪽 슬라이더의 값보다 1 큰 값으로 설정하여 교차되지 않도록 합니다.
    //     _this.value = Math.max(parseInt(_this.value), parseInt(leftInput2.value) + 1);
        
    //     // 입력 값의 퍼센트를 계산하여 Thumb와 범위의 위치를 설정합니다.
    //     const percent = Math.floor((((_this.value - min) / (max - min)) * 100 ) - (_this.value - 1) + 10);
    //     console.log(percent);
    //     console.log(_this.value);
    //     console.log("최소" + min);
    //     console.log("최대" + max);
    //     console.log("(((_this.value - min) / (max - min)) * 100 )" + (((_this.value - min) / (max - min)) * 100 ));
    //     console.log("(_this.value - min)" + (_this.value - min));
    //     console.log("(max - min)" + (max - min));
    //     console.log("(_this.value - 1) + 10: " + ((_this.value - 1) + 10));
    //     if (_this.value == 10) {
    //         rightThumb2.style.right = 101 - percent + "%"; // 오른쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //         rangeFill2.style.right = 101 - percent + "%"; // 오른쪽 범위의 위치를 퍼센트로 설정합니다.
    //     }else{
    //         rightThumb2.style.right = 100 - percent + "%"; // 오른쪽 Thumb의 위치를 퍼센트로 설정합니다.
    //         rangeFill2.style.right = 100 - percent + "%"; // 오른쪽 범위의 위치를 퍼센트로 설정합니다.
    //     }
    //     console.log("rangeFill2.style.right : " + rightThumb2.style.right);
        
    // };
    // // 왼쪽 입력 요소와 오른쪽 입력 요소에 입력 이벤트 리스너를 추가합니다.
    // leftInput.addEventListener("input", updateLeftValue);
    // rightInput.addEventListener("input", updateRightValue);
    // leftInput2.addEventListener("input", updateLeftValue2);
    // rightInput2.addEventListener("input", updateRightValue2);

    

    //  /*range출력 */
    //  const rangeInputs = document.querySelectorAll('input[type="range"].value-range');
    //  const numberInputs = document.querySelectorAll('input[type="number"].value_display');
    //  const numberLeft = document.querySelector(".price_range1");
    //  const numberRight = document.querySelector(".price_range2");
    //  const numberLeft2 = document.querySelector(".price_range3");
    //  const numberRight2 = document.querySelector(".price_range4");
 
    // //  rangeInputs.forEach((input, index) => {
    // //      input.addEventListener('input', function() {
    // //          numberInputs[index].textContent = input.value;
    // //      });
    // //  });
 
    //  rangeInputs.forEach((rangeInput, index) => {
    //      const numberInput = numberInputs[index];
         
    //      rangeInput.addEventListener('input', function() {
    //          numberInput.value = rangeInput.value;
    //      });
    //      numberInput.addEventListener('input', function() {
    //         //  console.log(numberInput[1].value);
    //         //  console.log(numberInput[2].value);
    //          console.log(numberInput.value);
    //          console.log(rangeInput.value);
    //          console.log("최소: "+numberLeft.value);
    //          console.log("최대: "+ numberRight.value );
    //          if(numberInput === numberLeft){
    //             if(parseInt(numberInput.value) >= (parseInt(numberRight.value)- 5500)){
    //                 console.log("최소값이 너무커");
    //                 numberInput.value = (parseInt(numberRight.value) - 5500);
                    
    //             }else if(parseInt(numberInput.value) < 500){
                    
    //                 console.log("최소값이 낫음")
    //                 numberInput.value = 500;
    //             }
    //             // updateLeftValue();
    //             // updateRightValue(); 
    //             console.log("오른쪽: "+rightThumb.style.right);
                
    //          }else if(numberInput === numberRight){
    //             if(parseInt(numberInput.value) <= (parseInt(numberLeft.value)+ 5500)){
                    
    //                 console.log("최대값이 너무작아")
    //                 numberInput.value = (parseInt(numberLeft.value) + 5500);
                    
    //             }else if(parseInt(numberInput.value) > 50000){
                    
    //                 console.log("최대값이 초과")
    //                 numberInput.value = 50000;
    //             }
    //             // updateLeftValue();
    //             // updateRightValue(); 
    //             // console.log("왼쪽: "+leftThumb.style.left);
    //          }
    //          rangeInput.value = numberInput.value;
    //          console.log(typeof(rangeInput.value));
    //          console.log(numberInput + " | " + rangeInput.value);
    //      });
         
    //  });
    
     
    //가격 양방향 range
    var slider1 = document.getElementById('price-slider');
    const min_price_h = document.getElementById("min_price_h");
    const max_price_h = document.getElementById("max_price_h");
    
    var minPriceInput = document.getElementById('min-price');
    var maxPriceInput = document.getElementById('max-price');
    minPriceInput.value = min_price_h.value;
    maxPriceInput.value = max_price_h.value;
    var margin = 5000;
    var defaultMinValue = 0;
    var defaultMaxValue = 50000;

     noUiSlider.create(slider1, {
         start: [parseFloat(minPriceInput.value) || 0, parseFloat(maxPriceInput.value) || 50000], // 초기값
         connect: true,
         range: {
             'min': 0,
             'max': 50000
         },
         step: 1, // 스텝 간격
         margin: margin, // 두 핸들 간의 간격을 300으로 설정
         format: {
             to: function (value) {
                //  return value.toFixed(0); // 소수점 없이 정수로 표시
                return new Intl.NumberFormat('ko-KR').format(value);
             },
             from: function (value) {
                 return Number(value); // 문자열을 숫자로 변환
             }
         }
     });

     // 슬라이더의 값을 input 필드에 반영
     slider1.noUiSlider.on('update', function (values) {
         minPriceInput.value = values[0];
         maxPriceInput.value = values[1];
         console.log("noUiSlider minValue:"+values[0]);
         console.log("noUiSlider maxValue:"+values[1]);
         min_price_h.value = values[0].replace(/,/g, '');;
         max_price_h.value = values[1].replace(/,/g, '');;
     });

     // input 필드의 값을 슬라이더에 반영
     minPriceInput.addEventListener('change', function () {
        
        // console.log("this.value:"+this.value);
        const baseMinValue = this.value.replace(/,/g, '');
        
        // console.log("baseMinValue:"+baseMinValue);
         var minValue = parseFloat(baseMinValue);
         min_price_h.value = minValue;
         console.log("baseMinValue:"+minValue);
        const baseMaxValue = maxPriceInput.value.replace(/,/g, '');
         var maxValue = parseFloat(baseMaxValue);
         console.log("baseMaxValue:"+maxValue);
         if (maxValue - minValue < margin) {
             if(minValue == maxPriceInput.max){
                 minPriceInput.value = maxPriceInput.max - margin;
                 
                 
             }else {
                 maxValue = minValue + margin;
                 maxPriceInput.value = maxValue;
                 console.log("maxValue"+maxValue);
                //  max_price_h.value = maxValue;
             }  
         }
         
         slider1.noUiSlider.set([minValue, null]);
         console.log("maxValue"+maxValue);
          console.log("maxPriceInput.max:"+maxPriceInput.max);
         console.log("minValue.maxPriceInput.max"+maxPriceInput.max - margin);
        //  min_price_h.value = maxPriceInput.max - margin;
        //  max_price_h.value = maxValue;
     });

     maxPriceInput.addEventListener('change', function () {
        const baseMaxValue = this.value.replace(/,/g, '');
         var maxValue = parseFloat(baseMaxValue);
         const baseMinValue = minPriceInput.value.replace(/,/g, '');
        //  max_price_h.value = maxValue;
         var minValue = parseFloat(baseMinValue);
         if (maxValue - minValue < margin) {
            console.log("maxValue"+maxValue);
             minValue = maxValue - margin;
             minPriceInput.value = minValue;
             
            
         }else if(maxValue>50000){
            maxValue = 50000;
            
            
         }
         slider1.noUiSlider.set([null, maxValue]);
         console.log("minValue"+minValue);
         console.log("maxValue"+maxValue);
        //  min_price_h.value = minValue;
        //  max_price_h.value = maxValue;
     });


     //리뷰 양방향 range

     var slider2 = document.getElementById('review-slider');
     var minPriceInput2 = document.getElementById('min-review');
     var maxPriceInput2 = document.getElementById('max-review');
     var margin2 = 1;

     noUiSlider.create(slider2, {
         start: [parseFloat(minPriceInput2.value) || 0, parseFloat(maxPriceInput2.value) || 10], // 초기값
         connect: true,
         range: {
             'min': 0,
             'max': 10
         },
         step: 1, // 스텝 간격
         margin: margin2, // 두 핸들 간의 간격을 300으로 설정
         format: {
             to: function (value) {
                 return value.toFixed(0); // 소수점 없이 정수로 표시
             },
             from: function (value) {
                 return Number(value); // 문자열을 숫자로 변환
             }
         }
     });

     // 슬라이더의 값을 input 필드에 반영
     slider2.noUiSlider.on('update', function (values) {
         minPriceInput2.value = values[0];
         maxPriceInput2.value = values[1];
     });

     // input 필드의 값을 슬라이더에 반영
     minPriceInput2.addEventListener('change', function () {
         var minValue = parseFloat(this.value);
         var maxValue = parseFloat(maxPriceInput2.value);
         if (maxValue - minValue < margin2) {
             if(minValue == maxPriceInput2.max){
                 minPriceInput2.value = maxPriceInput2.max - margin2;
             }else {
                 maxValue = minValue + margin2;
                 maxPriceInput2.value = maxValue;
             }  
         }
         slider2.noUiSlider.set([this.value, null]);
     });

     maxPriceInput2.addEventListener('change', function () {
         var maxValue = parseFloat(this.value);
         var minValue = parseFloat(minPriceInput2.value);
         if (maxValue - minValue < margin2) {
             minValue = maxValue - margin2;
             minPriceInput2.value = minValue;
         }
         slider2.noUiSlider.set([null, this.value]);
     });

     document.getElementById('resetBtn').addEventListener('click', function() {
        slider1.noUiSlider.set([defaultMinValue, defaultMaxValue]); // 슬라이더를 초기값으로 설정
        minPriceInput.value = defaultMinValue;
        maxPriceInput.value = defaultMaxValue;
        // document.getElementById('searchForm').reset(); // 폼 리셋
    });


});
    