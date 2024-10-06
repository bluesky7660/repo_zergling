$(document).ready(function(){
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
    const scoreCounts = {
        10: 0,
        7.5: 0,
        5: 0,
        2.5: 0,
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
    const totalReviews = reviews.length;

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

        // 점수 카운트
        if (scoreCounts.hasOwnProperty(reviewNum)) {
            scoreCounts[reviewNum]++;
        }

        // 총점과 리뷰 수
        scoreCounts.total += reviewNum;
        scoreCounts.count++;

        // 태그 카운트
        
        if (tagCounts.hasOwnProperty(reviewTag)) {
            tagCounts[reviewTag]++;
        }
    });

    // 퍼센트 계산 및 평균 점수 출력
    var averageScore = scoreCounts.count > 0 ? (scoreCounts.total / scoreCounts.count).toFixed(1) : 0;
    const percentageResults = {
        10: Math.floor((scoreCounts[10] / scoreCounts.count) * 100), // 소수점 버리기
        7.5: Math.floor((scoreCounts[7.5] / scoreCounts.count) * 100), // 소수점 버리기
        5: Math.floor((scoreCounts[5] / scoreCounts.count) * 100), // 소수점 버리기
        2.5: Math.floor((scoreCounts[2.5] / scoreCounts.count) * 100) // 소수점 버리기
    };

    // 점수 분포를 위한 HTML 생성
    const scoreDistributionContainer = document.querySelector(".scroll_box");
    scoreDistributionContainer.innerHTML = ''; // 기존 내용 초기화

    // 각 점수에 대해 li 요소 생성
    for (const score in percentageResults) {
        var percentage = percentageResults[score];
        const numClover = Math.round((percentage / 100) * 4); // 4개 클로버 중 몇 개를 보여줄지 계산
        console.log("dsa:"+percentage);
        if (isNaN(percentage)) {
            percentage = 0;
            console.log("percentage:"+percentage);
        }
        console.log("percentage2:"+percentage);
        const liElement = document.createElement("li");

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
        progressBar.setAttribute("role", "progressbar");
        progressBar.style.width = `${percentage}%`;
        progressBar.setAttribute("aria-valuenow", percentage);
        progressBar.setAttribute("aria-valuemin", "0");
        progressBar.setAttribute("aria-valuemax", "100");

        scoreBar.appendChild(progressBar);
        liElement.appendChild(scoreBar);
        
        // 비율 출력
        const ratingP = document.createElement("div");
        ratingP.className = "ratingP";
        const pElement = document.createElement("p");
        pElement.innerHTML = `<span>${percentage}</span> %`;
        ratingP.appendChild(pElement);
        
        liElement.appendChild(ratingP);
        
        // 최종적으로 scoreDistributionContainer에 추가
        scoreDistributionContainer.appendChild(liElement);
    }

    
    const tagPercentages = {};
    for (let tag in tagCounts) {
        tagPercentages[tag] = ((tagCounts[tag] / totalReviews) * 100);
    }

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

    // 클로버 박스 추가
    ratingTotal.appendChild(cloverBox);

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
    const reviewTotalTop = document.querySelector(".prod_detail_view ");
    const reviewTotalTopC = reviewTotalTop.querySelector(".total_clover_box");
    reviewTotalTopC.innerHTML = ''; // 기존 내용 초기화
    reviewTotalTopC.appendChild(cloverBoxTop);

    // 평균 점수 출력
    const averageElement = document.createElement("div");
    const reviewTotalTopT = reviewTotalTop.querySelector(".rating_number strong");
    console.log("averageScore: "+averageScore);
    if(averageScore == 0){
        averageScore = "0.0";
    }
    reviewTotalTopT.textContent = averageScore;
    averageElement.innerHTML = `<span class="rating_number"><strong>${averageScore}</strong></span> / <span>10</span>`;
    ratingTotal.appendChild(averageElement);

    // 태그 비율을 표시할 영역
    // const tagDistributionContainer = document.querySelector(".tag_distribution_container");
    const tagDistributionContainer = document.querySelector(".review_tags_box");
    const tagNames = document.querySelectorAll(".tagName p");
    // const tagpercent = tagNames.p .querySelectorAll(".tag_percent p");
    // tagDistributionContainer.innerHTML = ''; // 기존 내용 초기화
    let mostSelectedTag = Object.keys(tagCounts).reduce((a, b) => tagCounts[a] > tagCounts[b] ? a : b);

    for (const tag in tagPercentages) {
        const tagPercentage = tagPercentages[tag];
        console.log(tag+": "+tagPercentage);
        for (const tagName of tagNames) {
            const tagParent  = tagName.parentElement.parentElement;
            console.log(tagParent);
            const tagpercent = tagParent.querySelector(".tag_percent span");
            const progressBar = tagParent.querySelector(".progress-bar");
            console.log(progressBar);
            const name = tagName.textContent
            if(tag == name){
                tagpercent.textContent = tagPercentage;
                
                progressBar.style.height = tagPercentage+"%";
                if(tag == mostSelectedTag){
                    tagpercent.parentElement.style.color = "rgba(80, 85, 177, 0.8)";
                    tagpercent.parentElement.style.fontWeight = "bold";
                    document.querySelectorAll(".most_tag_parcent").forEach(function(tag) {
                        tag.textContent = tagPercentage+"%";
                    });
                    tagName.style.color = "rgba(80, 85, 177, 0.8)";
                    tagName.style.fontWeight = "bold";
                    progressBar.style.backgroundColor = "rgba(80, 85, 177, 0.8)";
                }
            }
            
        }
        
    }
    
    const selectedTagElement = document.querySelectorAll(" .most_tag");
    if(selectedTagElement){
        if(mostSelectedTag != null){
            selectedTagElement.forEach(function(tag) {
                tag.textContent = mostSelectedTag; 
            })
        }
    }
    
    

    // 결과 출력
    console.log("점수 분포:", percentageResults);
    console.log("평균 점수:", averageScore);
    console.log("태그 분포:", tagCounts);
    console.log("태그 탑:", mostSelectedTag);

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

    
    

    

});