$(document).ready(function(){
    // .review_list 요소와 모든 span 요소를 선택합니다.
    const reviewListElement = document.querySelector('.review_list>ul');
    const spans = document.querySelectorAll('.review_count');

    // .review_list의 직접 자식 <li> 요소만을 선택하여 개수를 셉니다.
    function countDirectChildren(element) {
        // 자식 요소 중 <li>만 선택
        const directLiItems = Array.from(element.children).filter(child => child.tagName === 'LI');
        return directLiItems.length;
    }

    // .review_list 내부의 직접 자식 <li> 요소의 개수를 계산합니다.
    const itemCount = countDirectChildren(reviewListElement);
    
    // span 요소에 개수를 출력합니다.
    spans.forEach(span => {
        span.textContent = itemCount;
    });

});