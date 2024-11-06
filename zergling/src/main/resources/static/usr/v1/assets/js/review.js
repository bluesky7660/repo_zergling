const scoreCounts = {
    '10': 0,
    '7.5': 0,
    '5': 0,
    '2.5': 0,
    '0':0,
    total: 0,
    count: 0
};
const tagCounts = {
    "고마워요": 0,
    "최고예요": 0,
    "공감돼요": 0,
    "재밌어요": 0,
    "힐링돼요": 0
    
};
function rvCloverImg(reviews) {
    reviews.forEach(function(review) {
        const reviewNum = parseFloat(review.querySelector(".user_review_num").textContent);
        // const reviewTagN = review.querySelector(".user_select_tag").textContent; // 태그 이름
        const reviewTag = review.querySelector(".user_select_tag").textContent;
        const reviewClover = review.querySelector(".clover_box");
        let i;
         // 불투명도 설정
        switch (reviewNum) {
            case 10:
                reviewClover.querySelectorAll("li").forEach(function(item) {
                    item.style.opacity = "1";
                });
                break;
            case 7.5:
                i=0;
                reviewClover.querySelectorAll("li").forEach(function(item) {
                    item.style.opacity = i > 2 ? "0.3" : "1";
                    i++;
                });
                break;
            case 5:
                i = 0;
                reviewClover.querySelectorAll("li").forEach(function(item) {
                    item.style.opacity = i > 1 ? "0.3" : "1";
                    i++;
                });
                break;
            case 2.5:
                i = 0;
                reviewClover.querySelectorAll("li").forEach(function(item) {
                    item.style.opacity = i > 0 ? "0.3" : "1";
                    i++;
                });
                break;
            default:
                reviewClover.querySelectorAll("li").forEach(function(item) {
                    item.style.opacity = "0.3";
                });
                break;
        }

        // // 점수 카운트
        // if (scoreCounts.hasOwnProperty(reviewNum)) {
        //     scoreCounts[reviewNum]++;
        // }

        // // 총점과 리뷰 수
        // scoreCounts.total += reviewNum;
        // scoreCounts.count++;

        // // 태그 카운트
        
        // if (tagCounts.hasOwnProperty(reviewTag)) {
        //     tagCounts[reviewTag]++;
        // }
    });
}
function updatePagination(thisPage, totalPages) {
    // 페이징 UI 업데이트 로직
    const paginationList = document.querySelector('.dataTable-pagination-list');
    paginationList.innerHTML = ''; // 기존 페이징 요소 초기화

    // 첫 페이지 버튼
    const firstPageItem = document.createElement('li');
    firstPageItem.className = 'paginate_button page-item' + (thisPage > 1 ? '' : ' disabled');
    firstPageItem.innerHTML = `
        <a class="page-link" onclick="goList(1)" href="javascript:void(0)">
            <span aria-hidden="true"><i class="fa-solid fa-angles-left"></i></span>
        </a>
    `;
    paginationList.appendChild(firstPageItem);

    // 이전 페이지 버튼
    const previousPageItem = document.createElement('li');
    previousPageItem.className = 'paginate_button previous page-item' + (thisPage > 1 ? '' : ' disabled');
    previousPageItem.innerHTML = `
        <a class="page-link" onclick="goList(${thisPage - 1})" href="javascript:void(0)">
            <span aria-hidden="true"><i class="fa-solid fa-chevron-left"></i></span>
        </a>
    `;
    paginationList.appendChild(previousPageItem);

    // 페이지 번호 생성
    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li');
        pageItem.className = 'paginate_button page-item' + (i === thisPage ? ' active' : '');
        pageItem.innerHTML = `
            <a class="page-link" onclick="goList(${i})" href="javascript:void(0)">${i}</a>
        `;
        paginationList.appendChild(pageItem);
    }

    // 다음 페이지 버튼
    const nextPageItem = document.createElement('li');
    nextPageItem.className = 'paginate_button next page-item' + (thisPage < totalPages ? '' : ' disabled');
    nextPageItem.innerHTML = `
        <a class="page-link" onclick="goList(${thisPage + 1})" href="javascript:void(0)">
            <span aria-hidden="true"><i class="fa-solid fa-chevron-right"></i></span>
        </a>
    `;
    paginationList.appendChild(nextPageItem);

    // 마지막 페이지 버튼
    const lastPageItem = document.createElement('li');
    lastPageItem.className = 'paginate_button page-item' + (thisPage < totalPages ? '' : ' disabled');
    lastPageItem.innerHTML = `
        <a class="page-link" onclick="goList(${totalPages})" href="javascript:void(0)">
            <span aria-hidden="true"><i class="fa-solid fa-angles-right"></i></span>
        </a>
    `;
    paginationList.appendChild(lastPageItem);
}

function displayTagDistribution(tagCounts, mostSelectedTag, reviewCount) {
    // const tagDistributionContainer = document.querySelector(".tag_distribution");
    // const tagDistributionContainer = document.querySelector(".review_tags_box");
    console.log("tagCounts:",tagCounts);
    console.log("reviewCount:",reviewCount);
    
    const tagDistributionContainer = document.querySelector("#book_review .review_tags_area");
    const topTagDistributionContainer = document.querySelector(".prod_detail_view .review_tags_area");
    if(reviewCount != 0){
        tagDistributionContainer.innerHTML = ''; // 기존 내용 초기화
        topTagDistributionContainer.innerHTML = '';
        const tagtotal = `
            <div class="review_total_tag_area">
                <div class="most_tag_box">
                    <p>
                        <span class="most_tag_parcent"></span>의 구매자가<br>
                        <span class="most_tag"></span> 라고 응답했어요
                    </p>
                </div>
                <div class="review_tags_box">
                </div>
            </div>`;
        // tagDistributionContainer.appendChild(tagtotal);  
        tagDistributionContainer.insertAdjacentHTML('beforeend', tagtotal);   
        
        // 태그 비율 계산 및 진행 바 설정
        Object.keys(tagCounts).forEach(tag => {
            const tagPercentage = ((tagCounts[tag] / reviewCount) * 100).toFixed(1);

            // 새로운 태그 항목 생성
            const tagItemHTML = `
                <div class="review_tag_item">
                    <p class="tag_percent"><span>${tagPercentage}</span>%</p>
                    <div class="score_bar progress">
                        <div class="progress-bar" role="progressbar" style="height: ${tagPercentage}%" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <div class="tagName">
                        <p>${tag}</p>
                    </div>
                </div>
            `;
            // `review_tags_box`에 새 태그 항목 추가
            document.querySelector(".review_tags_box").insertAdjacentHTML('beforeend', tagItemHTML);
        });
        const topMostTagHTML = `
            <div class="most_tag_box">
                <p>
                    <span class="most_tag_parcent"></span>의 구매자가<br>
                    <span class="most_tag"></span> 라고 응답했어요
                </p>
            </div>
        `;
        topTagDistributionContainer.insertAdjacentHTML('beforeend', topMostTagHTML); 
    }
    
    // // 태그 분포 표시
    // Object.entries(tagCounts).forEach(([tag, count]) => {
    //     const tagItem = document.createElement("div");
    //     tagItem.className = "tag_item";
    //     tagItem.innerHTML = `${tag}: ${count}`;
    //     tagDistributionContainer.appendChild(tagItem);
    // });
    // 태그 비율을 표시할 영역
    // const tagNames = document.querySelectorAll(".tagName p");
    // const tagpercent = tagNames.p .querySelectorAll(".tag_percent p");
    // tagDistributionContainer.innerHTML = ''; // 기존 내용 초기화
    // let mostSelectedTag = Object.keys(tagCounts).reduce((a, b) => tagCounts[a] > tagCounts[b] ? a : b);
    

    // const tagPercentages = {};
    // for (let tag in tagCounts) {
    //     tagPercentages[tag] = ((tagCounts[tag] / reviewCount) * 100).toFixed(1);
    // }
    // for (const tag in tagPercentages) {
    //     const tagPercentage = tagPercentages[tag];
    //     // console.log(tag+": "+tagPercentage);
    //     for (const tagName of tagNames) {
    //         const tagParent  = tagName.parentElement.parentElement;
    //         // console.log(tagParent);
    //         const tagpercent = tagParent.querySelector(".tag_percent span");
    //         const progressBar = tagParent.querySelector(".progress-bar");
    //         // console.log(progressBar);
    //         const name = tagName.textContent
    //         if(tag == name){
    //             tagpercent.textContent = tagPercentage;
                
    //             progressBar.style.height = tagPercentage+"%";
    //             if(tag == mostSelectedTag){
    //                 tagpercent.style.color = "rgba(80, 85, 177, 0.8)";
    //                 tagpercent.style.fontWeight = "bold";
    //                 document.querySelectorAll(".most_tag_parcent").forEach(function(tag) {
    //                     tag.textContent = tagPercentage+"%";
    //                 });
    //                 tagName.style.color = "rgba(80, 85, 177, 0.8)";
    //                 tagName.style.fontWeight = "bold";
    //                 progressBar.style.backgroundColor = "rgba(80, 85, 177, 0.8)";
    //             }
    //             else{
    //                 tagpercent.style.color = "rgb(119, 119, 119)";
    //                 tagpercent.style.fontWeight = "400";
    //                 tagName.style.color = "rgb(119, 119, 119)";
    //                 tagName.style.fontWeight = "400";
    //                 progressBar.style.backgroundColor = "rgb(170, 170, 170)";
    //             }
    //         }
            
    //     }
        
    // }
    // 태그별 진행 바와 색상 업데이트
    const tagPercentages = {};
    for (let tag in tagCounts) {
        tagPercentages[tag] = ((tagCounts[tag] / reviewCount) * 100).toFixed(1);
    }

    document.querySelectorAll(".review_tag_item").forEach(item => {
        const tagNameElement = item.querySelector(".tagName p");
        const tagPercentElement = item.querySelector(".tag_percent span");
        const progressBar = item.querySelector(".progress-bar");
        const tagName = tagNameElement.textContent;

        const tagPercentage = tagPercentages[tagName];
        tagPercentElement.textContent = tagPercentage;
        progressBar.style.height = tagPercentage + "%";

        // 가장 많이 선택된 태그 강조
        if (tagName === mostSelectedTag) {
            tagPercentElement.style.color = "rgba(80, 85, 177, 0.8)";
            tagPercentElement.style.fontWeight = "bold";
            tagNameElement.style.color = "rgba(80, 85, 177, 0.8)";
            tagNameElement.style.fontWeight = "bold";
            progressBar.style.backgroundColor = "rgba(80, 85, 177, 0.8)";

            // mostSelectedTag 비율 업데이트
            document.querySelectorAll(".most_tag_parcent").forEach(tag => {
                tag.textContent = tagPercentage + "%";
            });
            document.querySelectorAll(".most_tag").forEach(tag => {
                tag.textContent = mostSelectedTag;
            });
        } else {
            tagPercentElement.style.color = "rgb(119, 119, 119)";
            tagPercentElement.style.fontWeight = "400";
            tagNameElement.style.color = "rgb(119, 119, 119)";
            tagNameElement.style.fontWeight = "400";
            progressBar.style.backgroundColor = "rgb(170, 170, 170)";
        }
    });

    
    // 가장 많이 선택된 태그 표시
    // const mostSelectedTagContainer = document.querySelector(".most_selected_tag");
    // mostSelectedTagContainer.innerHTML = ''; // 기존 내용 초기화
    const selectedTagElement = document.querySelectorAll(" .most_tag");
    if(selectedTagElement){
        if(mostSelectedTag != null){
            selectedTagElement.forEach(function(tag) {
                tag.textContent = mostSelectedTag; 
            })
        }
    }
    // mostSelectedTagContainer.innerHTML = `가장 많이 선택된 태그: ${mostSelectedTag}`;
}
function calculateReviewAverageScore(averageScore, scoreCounts, reviewCount) {
    
    console.log("scoreCounts:",scoreCounts);
    const percentageResults = new Map([
        ['10.0', Math.floor((scoreCounts["10.0"] || 0) / reviewCount * 100)],
        ['7.5', Math.floor((scoreCounts["7.5"] || 0) / reviewCount * 100)],
        ['5.0', Math.floor((scoreCounts["5.0"] || 0) / reviewCount * 100)],
        ['2.5', Math.floor((scoreCounts["2.5"] || 0) / reviewCount * 100)],
        ['0.0', Math.floor((scoreCounts["0.0"] || 0) / reviewCount * 100)]
    ]);

    console.log("percentageResults",percentageResults);
    // HTML 업데이트 로직 (기존의 `reivewAverageScore` 내용 활용)
    const scoreDistributionContainer = document.querySelector(".scroll_box");
    scoreDistributionContainer.innerHTML = '';

    percentageResults.forEach((percentage, score) => {
        const liElement = document.createElement("li");
        liElement.classList.add("score" + score);
        console.log("percentage:"+percentage);
        if(isNaN(percentage)||percentage==null){
            percentage = 0;
        }
        
        // 클로버 박스 생성
        const cloverBox = document.createElement("ul");
        cloverBox.className = "clover_box d-flex justify-content-between";
        
        for (let i = 0; i < 4; i++) {
            const cloverItem = document.createElement("li");
            const cloverImg = document.createElement("img");
            cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
            cloverImg.alt = "";
            cloverItem.appendChild(cloverImg);
            cloverBox.appendChild(cloverItem);
        }
        
        liElement.appendChild(cloverBox);
        
        // 스코어 바 생성
        const scoreBar = document.createElement("div");
        scoreBar.className = "score_bar progress";

        const progressBar = document.createElement("div");
        progressBar.className = "progress-bar";
        progressBar.style.width = `${percentage}%`;
        progressBar.setAttribute("aria-valuenow", percentage);
        scoreBar.appendChild(progressBar);
        liElement.appendChild(scoreBar);
        
        // 비율 출력
        const ratingP = document.createElement("div");
        const pElement = document.createElement("p");
        pElement.innerHTML = `<span>${percentage}</span> %`;
        ratingP.classList.add("ratingP");
        ratingP.appendChild(pElement);
        liElement.appendChild(ratingP);
        
        scoreDistributionContainer.appendChild(liElement);
    });
    
    // 평균 점수와 클로버 표시
    const ratingTotal = document.querySelector(".rating_total");
    ratingTotal.innerHTML = ''; // 기존 내용 초기화
    


    // 클로버 박스 생성
    const cloverBox = document.createElement("ul");
    const cloverBoxTop = document.createElement("ul");
    cloverBox.className = "clover_box d-flex justify-content-between gap-3";
    cloverBoxTop.className = "clover_box d-flex justify-content-between gap-3";

    const numAverageClover = Math.round((averageScore / 10) * 4); // 평균 점수에 따라 클로버 개수 계산

    for (let i = 0; i < 4; i++) {
        const cloverItem = document.createElement("li");
        const cloverImg = document.createElement("img");
        cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
        cloverImg.alt = "";
        if (i < numAverageClover) {
            cloverItem.classList.add("active"); // 평균 점수에 따라 활성화
        }
        cloverItem.appendChild(cloverImg);
        cloverBox.appendChild(cloverItem);
    }
    ratingTotal.appendChild(cloverBox);

    //상단리뷰점수
    const reviewTotalTopC = document.querySelector(".total_clover_box");
    reviewTotalTopC.innerHTML = ''; // 기존 내용 초기화

    for (let i = 0; i < 4; i++) {
        const cloverItem = document.createElement("li");
        const cloverImg = document.createElement("img");
        cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
        cloverImg.alt = "";
        if (i < numAverageClover) {
            cloverItem.classList.add("active"); // 평균 점수에 따라 활성화
        }
        cloverItem.appendChild(cloverImg);
        cloverBoxTop.appendChild(cloverItem);
    }
    reviewTotalTopC.appendChild(cloverBoxTop);

    const averageElement = document.createElement("div");
    const reviewTotalTopT = document.querySelector(".prod_detail_view .rating_number strong");
    reviewTotalTopT.textContent = averageScore.toFixed(1); // 평균 점수를 소수점 1자리로 표시
    averageElement.innerHTML = `<span class="rating_number"><strong>${averageScore.toFixed(1)}</strong></span> / <span>10</span>`;
    ratingTotal.appendChild(averageElement);
}
function loadReviewList(productSeq,rvSort) {
    console.log("rvSort2:"+rvSort);
    console.log("productSeq2:"+productSeq);
    return $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: "/RefreshReviews"
        ,contentType: "application/json"
        ,data: JSON.stringify({ product_seq: productSeq,rvSort : rvSort})
        ,success: function(response) {
            // const reviewContainer = $('#review_list>ul'); 
            // reviewContainer.empty();
            
            $('#review_list>ul').empty(); // 기존 리뷰 리스트 비우기
            console.log("List:",response.rvList);
            if (response.rvList != null && response.rvList.length > 0 && response.reviewCount !== 0) {
                $.each(response.rvList, function(index, review) {
                    // 삭제 버튼 조건부 표시
                    var deleteButton = (review.member_seq == response.sessSeqXdm) ? 
                    `<span class="review_delbtn">삭제</span>` : '';
                    // 리뷰 아이템 생성
                    var reviewItem = `
                        <li class="user_review_box">
                            <input type="hidden" class="rvSeq" name="rvSeq" value="${review.rvSeq}">
                            <div class="review_head">
                                <div>
                                    <div>
                                        <span class="user_id">${review.rvName}</span>
                                        <span >|</span>
                                        <span class="day">${new Date(review.rvRegDate).getFullYear()}.${(new Date(review.rvRegDate).getMonth() + 1).toString().padStart(2, '0')}.${new Date(review.rvRegDate).getDate().toString().padStart(2, '0')}</span>
                                        ${deleteButton}    <!-- 로그인한 사용자일 때만 삭제 버튼 표시 -->
                                    </div>
                                </div>
                                <div>
                                    <ul class="clover_box d-flex justify-content-between">
                                        ${'<li><img src="/usr/v1/assets/images/ico_klover_sm@2x.png" alt=""></li>'.repeat(4)}
                                    </ul>
                                    <p class="user_review_num">${review.rvScore}</p>
                                    <input type="hidden" class="rvSelectTag" name="rvSelectTag" value="${review.rvSelectTag}">
                                    <p class="user_select_tag">${review.rvSelectTagName}</p>
                                </div>
                            </div>
                            <div class="review_body">
                                <div class="review_content">
                                    ${review.rvContent}
                                </div>
                                <div class="review_footer">
                                    <div class="review_footer_etc">
                                        <div class="btn_like"><span>0</span></div>
                                        <div class="btn_reply">답글<span class="reply_num">0</span></div>
                                    </div>
                                </div>
                            </div>
                        </li>`;
                    $('#review_list>ul').append(reviewItem);
                });
                const averageScore = response.averageScore;
                const scoreCounts = response.scoreCounts;
                const reviewCount = response.reviewCount;
                const tagCounts = response.tagCounts;
                const mostSelectedTag = response.mostSelectedTag;
                const mostSelectedTagNumber = response.mostSelectedTagNumber;
                // const mostSelectedTag = response.mostSelectedTag;
                console.log("response:"+averageScore);
                // 평균 점수 및 점수 분포 계산
                calculateReviewAverageScore(averageScore, scoreCounts, reviewCount);
        
                // 태그 분포 및 태그 탑 계산
                displayTagDistribution(tagCounts, mostSelectedTag, reviewCount);
                updatePagination(response.thisPage, response.totalPages);
                const reviews = document.querySelectorAll(".user_review_box");
                rvCloverImg(reviews);
                console.log("reviewCount:"+reviewCount);
                const reviewCounts = document.querySelectorAll(".review_count");
                reviewCounts.forEach((element) => {
                    element.textContent = reviewCount; // reviewCount의 값으로 각 요소의 텍스트 설정
                });
                let review = document.querySelector(".book_review");
                let scrollElementOffsetTop = review.offsetTop;
                console.log("review:"+review.offsetTop);
                window.scrollTo({
                    top: scrollElementOffsetTop,
                    behavior: 'smooth'
                });
                rvdelete();
                console.log("scrollElementOffsetTop:"+scrollElementOffsetTop);
            } else {
                console.log("리뷰 없음");
                var notReview = `
                        <li class="notReview">
                            <p>작성된 리뷰가 없습니다.</p>
                        </li>`;
                $('#review_list>ul').append(notReview);
            }
        }
        ,error : function(jqXHR, textStatus, errorThrown){
            console.error('리뷰 목록 갱신 중 오류 발생');
        }
    });

}
function rvdelete() {
    const rvdelBtn = document.querySelectorAll(".review_delbtn");
    if(rvdelBtn){
        const params = new URLSearchParams(window.location.search);
        const productSeq = params.get('seq');
        // const reviewSortSelect = document.getElementById("review_sort");
        rvdelBtn.forEach(e => {
            e.addEventListener("click", function() {
                const rvdelBtnBox = e.closest("li");
                console.log(rvdelBtnBox);
                $.ajax({
                    async: true 
                    ,cache: false
                    ,type: "post"
                    ,url: "/reviewUelt"
                    ,data : { 
                        "rvSeq" : rvdelBtnBox.querySelector(".rvSeq").value
                    }
                    ,success: function(response) {
                        console.log("rvdelete실행");
                        if(response.rt == "success") {
                            loadReviewList(productSeq,$("#review_sort").val())
                            // loadReviewList(productSeq,$("#review_sort").val()).done(function () {
                            //     console.log("리뷰 리스트 업데이트 완료");
                            //     // 리뷰 리스트가 갱신된 후에 다시 이벤트 리스너 등록
                            //     rvdelete();
                            // });
                        } else {
                            alert("양식에 맞춰주세요.")
                            // userId.classList.add('is-invalid');
                            // userPassword.classList.add('is-invalid');
                            // feedbackChk.classList.add('is-invalid');
                            // document.getElementById("invalid-feedback").innerText = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                        }
                        console.log("끝");
                    }
                    ,error : function(jqXHR, textStatus, errorThrown){
                        alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                    }
                });
            })
        });
    }
}
$(document).ready(function(){
    const prodparams = new URLSearchParams(window.location.search);
    const prod_Seq = prodparams.get('seq');
    $.ajax({
        async: true 
        ,cache: false
        ,type: "post"
        ,url: '/reviewAverage'
        ,contentType: 'application/json'
        ,data: JSON.stringify({ 
            seq: prod_Seq })
        ,success: function(response) {
            const averageScore = response.averageScore;
            const scoreCounts = response.scoreCounts;
            const reviewCount = response.reviewCount;
            const tagCounts = response.tagCounts;
            const mostSelectedTag = response.mostSelectedTag;
            const mostSelectedTagNumber = response.mostSelectedTagNumber;
            // const mostSelectedTag = response.mostSelectedTag;
            console.log("response:"+averageScore);
            // 평균 점수 및 점수 분포 계산
            calculateReviewAverageScore(averageScore, scoreCounts, reviewCount);
    
            // 태그 분포 및 태그 탑 계산
            displayTagDistribution(tagCounts, mostSelectedTag, reviewCount);
        }
        ,error: function(xhr, status, error) {
            console.error("Error fetching review data:", error);
        }
    });
    // // .review_list 요소와 모든 span 요소를 선택합니다.
    // const reviewListElement = document.querySelector('.review_list>ul');
    // const spans = document.querySelectorAll('.review_count');

    // // .review_list의 직접 자식 <li> 요소만을 선택하여 개수를 셉니다.
    // function countDirectChildren(element) {
    //     // 자식 요소 중 <li>만 선택
    //     const directLiItems = Array.from(element.children).filter(child => child.tagName === 'LI');
    //     return directLiItems.length;
    // }

    // // .review_list 내부의 직접 자식 <li> 요소의 개수를 계산합니다.
    // const itemCount = countDirectChildren(reviewListElement);
    
    // // span 요소에 개수를 출력합니다.
    // spans.forEach(span => {
    //     span.textContent = itemCount;
    // });

    //리뷰점수
    // const reviews = document.querySelectorAll(".user_review_box");
    // reviews.forEach(function(reviews){
    //     const reviewNum = parseFloat(reviews.querySelector(".user_review_num").textContent);
    //     const reviewClover = reviews.querySelector(".clover_box");
    //     // console.log("reviews: "+reviews);
    //     // console.log("reviewNum: "+reviewNum);
    //     // console.log("reviewClover: "+reviewClover);
    //     switch (reviewNum) {
    //         case 10:
    //             reviewClover.querySelectorAll("li").forEach(function(item){
    //                 item.style.opacity = "1";
    //             });
                
    //             break;
    //         case 7.5:
    //             var i=0;
    //             reviewClover.querySelectorAll("li").forEach(function(item){
    //                 if(i>2){
    //                     item.style.opacity = "0.3";
    //                 }else{
    //                     item.style.opacity = "1";
    //                 }
    //                 i++
    //             });
                
    //             break;
    //         case 5:
    //             var i=0;
    //             reviewClover.querySelectorAll("li").forEach(function(item){
    //                 if(i>1){
    //                     item.style.opacity = "0.3";
    //                 }else{
    //                     item.style.opacity = "1";
    //                 }
    //                 i++
    //             });
                
    //             break;
    //         case 2.5:
    //             var i=0;
    //             reviewClover.querySelectorAll("li").forEach(function(item){
    //                 if(i>0){
    //                     item.style.opacity = "0.3";
    //                 }else{
    //                     item.style.opacity = "1";
    //                 }
    //                 i++
    //             });
                
    //             break;
        
    //         default:
    //             reviewClover.querySelectorAll("li").forEach(function(item){
    //                 item.style.opacity = "0.3";
    //             });
    //             break;
    //     }
    // })



    const reviews = document.querySelectorAll(".user_review_box");
    
    
    const totalReviews = reviews.length;
    if(reviews){
        rvCloverImg(reviews);
    }
    
    // function rvCloverImg(reviews) {
    //     reviews.forEach(function(review) {
    //         const reviewNum = parseFloat(review.querySelector(".user_review_num").textContent);
    //         // const reviewTagN = review.querySelector(".user_select_tag").textContent; // 태그 이름
    //         const reviewTag = review.querySelector(".user_select_tag").textContent;
    //         const reviewClover = review.querySelector(".clover_box");
    //         let i;
    //          // 불투명도 설정
    //         switch (reviewNum) {
    //             case 10:
    //                 reviewClover.querySelectorAll("li").forEach(function(item) {
    //                     item.style.opacity = "1";
    //                 });
    //                 break;
    //             case 7.5:
    //                 i=0;
    //                 reviewClover.querySelectorAll("li").forEach(function(item) {
    //                     item.style.opacity = i > 2 ? "0.3" : "1";
    //                     i++;
    //                 });
    //                 break;
    //             case 5:
    //                 i = 0;
    //                 reviewClover.querySelectorAll("li").forEach(function(item) {
    //                     item.style.opacity = i > 1 ? "0.3" : "1";
    //                     i++;
    //                 });
    //                 break;
    //             case 2.5:
    //                 i = 0;
    //                 reviewClover.querySelectorAll("li").forEach(function(item) {
    //                     item.style.opacity = i > 0 ? "0.3" : "1";
    //                     i++;
    //                 });
    //                 break;
    //             default:
    //                 reviewClover.querySelectorAll("li").forEach(function(item) {
    //                     item.style.opacity = "0.3";
    //                 });
    //                 break;
    //         }
    
    //         // 점수 카운트
    //         if (scoreCounts.hasOwnProperty(reviewNum)) {
    //             scoreCounts[reviewNum]++;
    //         }
    
    //         // 총점과 리뷰 수
    //         scoreCounts.total += reviewNum;
    //         scoreCounts.count++;
    
    //         // 태그 카운트
            
    //         if (tagCounts.hasOwnProperty(reviewTag)) {
    //             tagCounts[reviewTag]++;
    //         }
    //     });
    // }
    

    // 퍼센트 계산 및 평균 점수 출력
    // function reivewAverageScore() {
    //     var averageScore = scoreCounts.count > 0 ? (scoreCounts.total / scoreCounts.count).toFixed(1) : 0;
    //     // 점수와 비율을 배열에 저장합니다.
    //     // const orderedPercentageResults = {
    //     //     '10': Math.floor((scoreCounts['10'] / scoreCounts.count) * 100), // 문자열 키 사용
    //     //     '7.5': Math.floor((scoreCounts['7.5'] / scoreCounts.count) * 100), // 문자열 키 사용
    //     //     '5': Math.floor((scoreCounts['5'] / scoreCounts.count) * 100), // 문자열 키 사용
    //     //     '2.5': Math.floor((scoreCounts['2.5'] / scoreCounts.count) * 100) // 문자열 키 사용
    //     // };
    //     // // console.log(percentageResultsArray);
    //     // // percentageResultsArray.sort((a, b) => b.score - a.score);

    //     // // // 객체로 변환하여 원하는 형식으로 결과를 생성
    //     // // const percentageResults = {};
    //     // // percentageResultsArray.forEach(item => {
    //     // //     percentageResults[item.score] = parseFloat(item.percentage.toFixed(1)); // 소수점 한 자리로 변환
    //     // // });
    //     // const sortedResults = Object.entries(orderedPercentageResults).sort((a, b) => {
    //     //     return parseFloat(a[0]) - parseFloat(b[0]); // 문자열을 실수로 변환하여 정렬
    //     // });
        
    //     // const percentageResults = Object.fromEntries(sortedResults);
    //     // console.log(percentageResults);
    //     const percentageResults = new Map([
    //         ['10', Math.floor((scoreCounts[10] / scoreCounts.count) * 100)],
    //         ['7.5', Math.floor((scoreCounts[7.5] / scoreCounts.count) * 100)],
    //         ['5', Math.floor((scoreCounts[5] / scoreCounts.count) * 100)],
    //         ['2.5', Math.floor((scoreCounts[2.5] / scoreCounts.count) * 100)],
    //         ['0', Math.floor((scoreCounts[0] / scoreCounts.count) * 100)]
    //     ]);
    //     // 결과 출력
    //     console.log(percentageResults)

    //     // 점수 분포를 위한 HTML 생성   
    //     const scoreDistributionContainer = document.querySelector(".scroll_box");
    //     scoreDistributionContainer.innerHTML = ''; // 기존 내용 초기화

    //     // 각 점수에 대해 li 요소 생성
    //     percentageResults.forEach((percentage, score) => {
    //         // var percentage = percentageResults[score];
    //         const numClover = Math.round((percentage / 100) * 4); // 4개 클로버 중 몇 개를 보여줄지 계산
    //         // console.log("dsa:"+percentage);
    //         if (isNaN(percentage)) {
    //             percentage = 0;
    //             // console.log("percentage:"+percentage);
    //         }
    //         console.log(score+" :"+percentage);
    //         const liElement = document.createElement("li");
    //         liElement.classList.add("score"+score);
    //         // 클로버 박스 생성
    //         const cloverBox = document.createElement("ul");
    //         cloverBox.className = "clover_box d-flex justify-content-between";
            
    //         for (let i = 0; i < 4; i++) {
    //             const cloverItem = document.createElement("li");
    //             const cloverImg = document.createElement("img");
    //             cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
    //             cloverImg.alt = "";
    //             cloverItem.appendChild(cloverImg);
    //             cloverBox.appendChild(cloverItem);
    //         }
            
    //         liElement.appendChild(cloverBox);
            
    //         // 스코어 바 생성
    //         const scoreBar = document.createElement("div");
    //         scoreBar.className = "score_bar progress";

    //         const progressBar = document.createElement("div");
    //         progressBar.className = "progress-bar";
    //         progressBar.setAttribute("role", "progressbar");
    //         progressBar.style.width = `${percentage}%`;
    //         progressBar.setAttribute("aria-valuenow", percentage);
    //         progressBar.setAttribute("aria-valuemin", "0");
    //         progressBar.setAttribute("aria-valuemax", "100");

    //         scoreBar.appendChild(progressBar);
    //         liElement.appendChild(scoreBar);
            
    //         // 비율 출력
    //         const ratingP = document.createElement("div");
    //         ratingP.className = "ratingP";
    //         const pElement = document.createElement("p");
    //         pElement.innerHTML = `<span>${percentage}</span> %`;
    //         ratingP.appendChild(pElement);
            
    //         liElement.appendChild(ratingP);
            
    //         // 최종적으로 scoreDistributionContainer에 추가
    //         scoreDistributionContainer.appendChild(liElement);
    //     })
    //     // for (const score in percentageResults) {
    //     //     var percentage = percentageResults[score];
    //     //     const numClover = Math.round((percentage / 100) * 4); // 4개 클로버 중 몇 개를 보여줄지 계산
    //     //     // console.log("dsa:"+percentage);
    //     //     if (isNaN(percentage)) {
    //     //         percentage = 0;
    //     //         // console.log("percentage:"+percentage);
    //     //     }
    //     //     console.log(score+" :"+percentage);
    //     //     const liElement = document.createElement("li");

    //     //     // 클로버 박스 생성
    //     //     const cloverBox = document.createElement("ul");
    //     //     cloverBox.className = "clover_box d-flex justify-content-between";
            
    //     //     for (let i = 0; i < 4; i++) {
    //     //         const cloverItem = document.createElement("li");
    //     //         const cloverImg = document.createElement("img");
    //     //         cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
    //     //         cloverImg.alt = "";
    //     //         cloverItem.appendChild(cloverImg);
    //     //         cloverBox.appendChild(cloverItem);
    //     //     }
            
    //     //     liElement.appendChild(cloverBox);
            
    //     //     // 스코어 바 생성
    //     //     const scoreBar = document.createElement("div");
    //     //     scoreBar.className = "score_bar progress";

    //     //     const progressBar = document.createElement("div");
    //     //     progressBar.className = "progress-bar";
    //     //     progressBar.setAttribute("role", "progressbar");
    //     //     progressBar.style.width = `${percentage}%`;
    //     //     progressBar.setAttribute("aria-valuenow", percentage);
    //     //     progressBar.setAttribute("aria-valuemin", "0");
    //     //     progressBar.setAttribute("aria-valuemax", "100");

    //     //     scoreBar.appendChild(progressBar);
    //     //     liElement.appendChild(scoreBar);
            
    //     //     // 비율 출력
    //     //     const ratingP = document.createElement("div");
    //     //     ratingP.className = "ratingP";
    //     //     const pElement = document.createElement("p");
    //     //     pElement.innerHTML = `<span>${percentage}</span> %`;
    //     //     ratingP.appendChild(pElement);
            
    //     //     liElement.appendChild(ratingP);
            
    //     //     // 최종적으로 scoreDistributionContainer에 추가
    //     //     scoreDistributionContainer.appendChild(liElement);
    //     // }

        
    //     const tagPercentages = {};
    //     for (let tag in tagCounts) {
    //         tagPercentages[tag] = ((tagCounts[tag] / totalReviews) * 100);
    //     }

    //     // 평균 점수와 클로버 표시
    //     const ratingTotal = document.querySelector(".rating_total");
    //     ratingTotal.innerHTML = ''; // 기존 내용 초기화

    //     // 클로버 박스 생성
    //     const cloverBox = document.createElement("ul");
    //     const cloverBoxTop = document.createElement("ul");
    //     cloverBox.className = "clover_box d-flex justify-content-between gap-3";
    //     cloverBoxTop.className = "clover_box d-flex justify-content-between gap-3";

    //     const numAverageClover = Math.round((averageScore / 10) * 4); // 평균 점수에 따라 클로버 개수 계산

    //     for (let i = 0; i < 4; i++) {
    //         const cloverItem = document.createElement("li");
    //         const cloverImg = document.createElement("img");
    //         cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
    //         cloverImg.alt = "";
    //         if (i < numAverageClover) {
    //             cloverItem.classList.add("active"); // 평균 점수에 따라 활성화
    //         }
    //         cloverItem.appendChild(cloverImg);
    //         cloverBox.appendChild(cloverItem);
    //     }

    //     // 클로버 박스 추가
    //     ratingTotal.appendChild(cloverBox);

    //     for (let i = 0; i < 4; i++) {
    //         const cloverItem = document.createElement("li");
    //         const cloverImg = document.createElement("img");
    //         cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
    //         cloverImg.alt = "";
    //         if (i < numAverageClover) {
    //             cloverItem.classList.add("active"); // 평균 점수에 따라 활성화
    //         }
    //         cloverItem.appendChild(cloverImg);
    //         cloverBoxTop.appendChild(cloverItem);
    //     }
    //     const reviewTotalTop = document.querySelector(".prod_detail_view ");
    //     const reviewTotalTopC = reviewTotalTop.querySelector(".total_clover_box");
    //     reviewTotalTopC.innerHTML = ''; // 기존 내용 초기화
    //     reviewTotalTopC.appendChild(cloverBoxTop);

    //     // 평균 점수 출력
    //     const averageElement = document.createElement("div");
    //     const reviewTotalTopT = reviewTotalTop.querySelector(".rating_number strong");
    //     // console.log("averageScore: "+averageScore);
    //     if(averageScore == 0){
    //         averageScore = "0.0";
    //     }
    //     reviewTotalTopT.textContent = averageScore;
    //     averageElement.innerHTML = `<span class="rating_number"><strong>${averageScore}</strong></span> / <span>10</span>`;
    //     ratingTotal.appendChild(averageElement);

    //     // 태그 비율을 표시할 영역
    //     // const tagDistributionContainer = document.querySelector(".tag_distribution_container");
    //     const tagDistributionContainer = document.querySelector(".review_tags_box");
    //     const tagNames = document.querySelectorAll(".tagName p");
    //     // const tagpercent = tagNames.p .querySelectorAll(".tag_percent p");
    //     // tagDistributionContainer.innerHTML = ''; // 기존 내용 초기화
    //     let mostSelectedTag = Object.keys(tagCounts).reduce((a, b) => tagCounts[a] > tagCounts[b] ? a : b);

    //     for (const tag in tagPercentages) {
    //         const tagPercentage = tagPercentages[tag];
    //         // console.log(tag+": "+tagPercentage);
    //         for (const tagName of tagNames) {
    //             const tagParent  = tagName.parentElement.parentElement;
    //             // console.log(tagParent);
    //             const tagpercent = tagParent.querySelector(".tag_percent span");
    //             const progressBar = tagParent.querySelector(".progress-bar");
    //             // console.log(progressBar);
    //             const name = tagName.textContent
    //             if(tag == name){
    //                 tagpercent.textContent = tagPercentage;
                    
    //                 progressBar.style.height = tagPercentage+"%";
    //                 if(tag == mostSelectedTag){
    //                     tagpercent.parentElement.style.color = "rgba(80, 85, 177, 0.8)";
    //                     tagpercent.parentElement.style.fontWeight = "bold";
    //                     document.querySelectorAll(".most_tag_parcent").forEach(function(tag) {
    //                         tag.textContent = tagPercentage+"%";
    //                     });
    //                     tagName.style.color = "rgba(80, 85, 177, 0.8)";
    //                     tagName.style.fontWeight = "bold";
    //                     progressBar.style.backgroundColor = "rgba(80, 85, 177, 0.8)";
    //                 }
    //             }
                
    //         }
            
    //     }
        
    //     const selectedTagElement = document.querySelectorAll(" .most_tag");
    //     if(selectedTagElement){
    //         if(mostSelectedTag != null){
    //             selectedTagElement.forEach(function(tag) {
    //                 tag.textContent = mostSelectedTag; 
    //             })
    //         }
    //     }
    //     // 결과 출력
    //     console.log("카운트:", scoreCounts.count);
    //     console.log("토탈:", scoreCounts.total);
    //     console.log("점수 분포:", percentageResults);
    //     console.log("평균 점수:", averageScore);
    //     console.log("태그 분포:", tagCounts);
    //     console.log("태그 탑:", mostSelectedTag);
    // }
    // reivewAverageScore();   
    
    

    
    

    // // 태그 분포를 위한 HTML 생성
    // const tagDistributionContainer = document.querySelector(".tag_distribution_container");
    // tagDistributionContainer.innerHTML = ''; // 기존 내용 초기화

    // // 각 태그에 대해 li 요소 생성
    // for (const tag in tagCounts) {
    //     const count = tagCounts[tag];

    //     const liElement = document.createElement("li");
    //     liElement.textContent = `${tag}: ${count}개`;
        
    //     // 최종적으로 tagDistributionContainer에 추가
    //     tagDistributionContainer.appendChild(liElement);
    // }

    
    // const reviews = document.querySelectorAll(".user_review_box");
    // const scoreCounts = {
    //     10: 0,
    //     7.5: 0,
    //     5: 0,
    //     2.5: 0,
    //     total: 0,
    //     count: 0
    // };

    // const tagCounts = {}; // 태그 카운트 객체

    // reviews.forEach(function(review) {
    //     const reviewNum = parseFloat(review.querySelector(".user_review_num").textContent);
    //     const reviewTagN = review.querySelector(".user_select_tag").textContent; // 태그 이름

    //     // 점수 카운트
    //     if (scoreCounts.hasOwnProperty(reviewNum)) {
    //         scoreCounts[reviewNum]++;
    //     }

    //     // 총점과 리뷰 수
    //     scoreCounts.total += reviewNum;
    //     scoreCounts.count++;

    //     // 태그 카운트
    //     if (!tagCounts[reviewTagN]) {
    //         tagCounts[reviewTagN] = 0; // 태그 초기화
    //     }
    //     tagCounts[reviewTagN]++; // 태그 카운트 증가
    // });

    // // 퍼센트 계산 및 평균 점수 출력
    

    // const averageScore = scoreCounts.count > 0 ? (scoreCounts.total / scoreCounts.count).toFixed(2) : 0;
    
    // const percentageResults = {
    //     10: Math.floor((scoreCounts[10] / scoreCounts.count) * 100), // 소수점 버리기
    //     7.5: Math.floor((scoreCounts[7.5] / scoreCounts.count) * 100), // 소수점 버리기
    //     5: Math.floor((scoreCounts[5] / scoreCounts.count) * 100), // 소수점 버리기
    //     2.5: Math.floor((scoreCounts[2.5] / scoreCounts.count) * 100) // 소수점 버리기
    // };


    // // 점수 분포를 위한 HTML 생성
    // const scoreDistributionContainer = document.querySelector(".scroll_box");
    // scoreDistributionContainer.innerHTML = ''; // 기존 내용 초기화

    // // 각 리뷰 점수에 대해 li 요소 생성
    // for (const score in percentageResults) {
    //     const percentage = percentageResults[score];
    //     const numClover = Math.round((percentage / 100) * 4); // 4개 클로버 중 몇 개를 보여줄지 계산

    //     const liElement = document.createElement("li");

    //     // 클로버 박스 생성
    //     const cloverBox = document.createElement("ul");
    //     cloverBox.className = "clover_box d-flex justify-content-between gap-3";
        
    //     for (let i = 0; i < 4; i++) {
    //         const cloverItem = document.createElement("li");
    //         const cloverImg = document.createElement("img");
    //         cloverImg.src = "/usr/v1/assets/images/ico_klover_sm@2x.png";
    //         cloverImg.alt = "";
    //         cloverItem.appendChild(cloverImg);
    //         cloverBox.appendChild(cloverItem);
    //     }
        
    //     liElement.appendChild(cloverBox);
        
    //     // 스코어 바 생성
    //     const scoreBar = document.createElement("div");
    //     scoreBar.className = "score_bar progress";

    //     const progressBar = document.createElement("div");
    //     progressBar.className = "progress-bar";
    //     progressBar.setAttribute("role", "progressbar");
    //     progressBar.style.width = `${percentage}%`;
    //     progressBar.setAttribute("aria-valuenow", percentage);
    //     progressBar.setAttribute("aria-valuemin", "0");
    //     progressBar.setAttribute("aria-valuemax", "100");

    //     scoreBar.appendChild(progressBar);
    //     liElement.appendChild(scoreBar);
        
    //     // 비율 출력
    //     const ratingP = document.createElement("div");
    //     ratingP.className = "ratingP";
    //     const pElement = document.createElement("p");
    //     pElement.innerHTML = `<span>${percentage}</span>%`;
    //     ratingP.appendChild(pElement);
        
    //     liElement.appendChild(ratingP);
        
    //     // 최종적으로 scoreDistributionContainer에 추가
    //     scoreDistributionContainer.appendChild(liElement);
    // }

    // // 태그 분포를 위한 HTML 생성
    // const tagDistributionContainer = document.querySelector(".tag_distribution_container");
    // tagDistributionContainer.innerHTML = ''; // 기존 내용 초기화

    // // 각 태그에 대해 li 요소 생성
    // for (const tag in tagCounts) {
    //     const count = tagCounts[tag];

    //     const liElement = document.createElement("li");
    //     liElement.textContent = `${tag}: ${count}개`;
        
    //     // 최종적으로 tagDistributionContainer에 추가
    //     tagDistributionContainer.appendChild(liElement);
    // }

    // // 결과 출력
    // console.log("점수 분포:", percentageResults);
    // console.log("평균 점수:", averageScore);
    // console.log("태그 분포:", tagCounts);

    
    // const rvtaginput = document.querySelector
    
    const rvBtn = document.getElementById("rvBtn");
    const userId = document.getElementById("userSeq");
    if(rvBtn){
        const params = new URLSearchParams(window.location.search);
        const productSeq = params.get('seq');
        const currentUrl = window.location.href; 

       
            
        rvBtn.addEventListener("click", function() {
            if (!userId || !userId.value) {  // 로그인 확인
                // 로그인 페이지로 리다이렉트하면서 현재 페이지 URL을 쿼리 매개변수로 추가
                // alert("리뷰를 작성하려면 로그인이 필요합니다.");
                // window.location.href = "/login?redirect=" + encodeURIComponent(currentUrl);
                window.location.href = "/login?redirect=review";
            } else {
                // console.log("태그:" +$("input[name='rvSelectTag']:checked").val());
                // console.log("글:" +$("#rvContent").val());
                if(($("input[name='rvSelectTag']:checked").val()== null)||($("#rvContent").val()==''||$("#rvContent").val()==null)){
                    if($("input[name='rvSelectTag']:checked").val()== null){
                        alert("감성태그를 선택해주세요.")
                    }else if(($("#rvContent").val()=='')||$("#rvContent").val()==null){
                        alert("리뷰를 제대로 작성 해주세요.")
                    }
                }
                else {   
                    $.ajax({
                        async: true 
                        ,cache: false
                        ,type: "post"
                        ,url: "/reviewInst"
                        ,data : { 
                            "member_seq" : $("#userSeq").val()
                            ,"product_seq" : productSeq 
                            ,"rvSelectTag":$("input[name='rvSelectTag']:checked").val()
                            ,"rvContent":$("#rvContent").val()
                            ,"rvScore":$("#rvUsrScore").val()
                        }
                        ,success: function(response) {
                            if(response.rt == "success") {
                                loadReviewList(productSeq,$("#review_sort").val());
                                
                            } else {
                                alert("양식에 맞춰주세요.")
                                // userId.classList.add('is-invalid');
                                // userPassword.classList.add('is-invalid');
                                // feedbackChk.classList.add('is-invalid');
                                // document.getElementById("invalid-feedback").innerText = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                            }
                        }
                        ,error : function(jqXHR, textStatus, errorThrown){
                            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                        }
                    });
                }
            }
        });
        
    }
    rvdelete();
    
});
