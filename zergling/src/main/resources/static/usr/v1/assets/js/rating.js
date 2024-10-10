document.addEventListener('DOMContentLoaded', function () {
    /*별점 js */
    
    $("#rating-input").rating({
        language: 'ko',
        theme: 'krajee-gly',
        clearCaption: '<strong class="val gray_color" aria-hidden="true">0</strong> <span class="total" aria-hidden="true">/ 10</span></span>',
        stars: 4,
        min: 0,
        max: 10,
        step: 2.5,
        starCaptions: function (rating) {
            var strCaption;
            strCaption ='<strong class=green_color>' + rating + '</strong> <span>/ 10</span>';
            if($(this).data('caption') !== undefined) {
                strCaption += '<span class="val" aria-hidden="true">' + rating + '</span>' + '<span class="total" aria-hidden="true">' + 10 + '</span>';

            }else{
                // return rating + ' / 10';
            }
            return strCaption;
        }
        // filledStar: '<span></span>', // 커스텀 아이콘 사용
        // emptyStar: '<span></span>'   // 커스텀 아이콘 사용
    }).on('rating:change', function(event, value) {
        console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        console.log(value);
        // 평점 값이 변경될 때마다 input 요소의 value 값을 업데이트
        $("#rvUsrScore").val(value);
    });
});