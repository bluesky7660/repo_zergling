function searchVideos() {
    $.ajax({
        type: 'GET',
        url: '/search',
        // data: { },
        success: function(response) {
            $('#loadingSpinner').hide();
            // HTML 요소 비우기
            console.log(response);
            $('#results').empty();
            var videoItems = Array.isArray(response) ? response : response.items;
            
            console.log("확인:",response.items);
            // 응답 데이터에서 동영상 정보 추가
            console.log("확인2:",videoItems);
            videoItems.forEach(function(item) {
                // <div class="video_item">
                //         <div class="video_thumbnail">
                //             <a href="${item.videoUrl}" target="_blank">
                //                 <img src="${item.thumbnailUrl}" alt="${item.title}">
                //             </a>
                //         </div>
                //         <!-- <h4>${item.title}</h4> -->
                //         <div class="video_detail">
                //             <h4><a href="${item.videoUrl}" target="_blank">${item.title}</a></h4>
                //             <p>채널: ${item.channelTitle}</p>
                //             <p>조회수: ${item.viewCount}회</p>
                //             <p>좋아요: ${item.likeCount}</p>
                //             <p>댓글 수: ${item.commentCount}</p>
                //         </div>
                //     </div>
                var videoHtml = `
                    <div class="video_item">
                        <a href="${item.videoUrl}" target="_blank">
                            <div class="video_thumbnail">
                                <div class="img_box">
                                    <img src="${item.thumbnailUrl}" alt="${item.title}">
                                </div>
                            </div>
                            <!-- <h4>${item.title}</h4> -->
                            <div class="video_detail">
                                <h4>${item.title}</h4>
                                <p>채널: ${item.channelTitle}</p>
                                <p>조회수: ${item.viewCount}회</p>
                                <p>좋아요: ${item.likeCount}</p>
                                <p>댓글 수: ${item.commentCount}</p>
                            </div>
                        </a>
                    </div>
                `;
                $('#book_review_area').append(videoHtml);
            });
        },
        error: function(error) {
            console.log("에러 발생:", error);
            $('#loadingSpinner').hide();
        }
    });
}
//채널 기본정보 로드
function loadChannels() {
    $.ajax({
        url: '/channels',
        method: 'GET',
        success: function(channels) {
            // let tabsHtml = '';
            // channels.forEach(function(channel, index) {
            //     // 각 채널 탭 생성
            //     tabsHtml += '<li><a href="#" class="channel-tab" data-channel-id="' + channel.ycId + '">' + channel.ycName + '</a></li>';
            // });
            // $('#channel-tabs').html(tabsHtml);

            console.log("channels:",channels);
            // 첫 번째 채널의 기본 정보 및 동영상 표시
            if (channels.length > 0) {
               
                loadChannelDetails(channels[0].ycId);
            }

            // 탭 클릭 이벤트 처리
            // $('.channel-tab').click(function(e) {
            //     console.log("channels");
            //     e.preventDefault();
            //     let ycId = $(this).data('channel-id');
            //     $('#loadingSpinner').show();
            //     loadChannelDetails(ycId);
            // });
        }
    });
}
//채널 정보 출력
function loadChannelDetails(channelId) {
    $.ajax({
        url: '/channel/details',
        method: 'GET',
        data: { channelId: channelId },
        success: function(data) {
            $('#loadingSpinner').hide();
            // 채널 정보 표시
            let channelInfo = data.channelInfo;
            console.log("channelId"+channelId);
            console.log("channelInfo:",channelInfo);
            const subscribersCount = channelInfo.subscribersCount;
            const videosCount = channelInfo.videosCount;

            // 구독자 수 포맷팅
            const formatSubscribers = (num) => {
                if (num >= 10000) {
                    const formattedNumber = (num / 10000);
                    if (formattedNumber % 1 === 0) {
                        return `${formattedNumber.toFixed(0)}만명`; // 정수일 경우
                    } else if (formattedNumber * 10 % 1 === 0) {
                        return `${formattedNumber.toFixed(1)}만명`; // 소수점 1자리일 경우
                    } else {
                        return `${formattedNumber.toFixed(2)}만명`; // 소수점 2자리일 경우
                    }
                } else {
                    return num + "명"; // 10,000명 미만일 경우
                }
            };

            // 영상 수 포맷팅
            const formatVideos = (num) => {
                return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            };
            $('#channel_info').empty();
            var channelHtml = `
                <a href="${channelInfo.channelUrl}" target="_blank" rel="noopener noreferrer" id="channel_link">
                    <div class="channel_thumbnail_box" id="channel_thumbnail_box">
                        <img class="channel_thumbnail" src="${channelInfo.thumbnailUrl}" alt="${channelInfo.ycName}썸네일">
                    </div>
                    <div class="channel_info_text" id="channel_info_text">
                        <h3 class="channel_Name" id="channel_Name">${channelInfo.ycName}</h3>
                        <p class="channel_subscribersCount" id="channel_subscribersCount">구독자: ${formatSubscribers(subscribersCount)}</p>
                        <p class="channel_videosCount" id="channel_videosCount">총 영상수:  ${formatVideos(videosCount)}개</p>
                        <p class="channels_description" id="channels_description">${channelInfo.channelsDescription}</p>
                    </div>
                </a>
            `;
            $('#channel_info').append(channelHtml);
            // $('#channel_link').attr('href',channelInfo.channelUrl);
            // $('#channel_Name').text(channelInfo.ycName);
            // $('#channel_subscribersCount').text('구독자: ' + channelInfo.subscribersCount+'명');
            // $('#channel_videosCount').text('총 영상 수: ' + channelInfo.videosCount);
            // $('#channel_thumbnail').attr('src', channelInfo.thumbnailUrl);

            // 동영상 목록 표시
            let videos = data.latestVideos;
            let videoHtml = '';
            videos.forEach(function(video) {
                videoHtml += '<div class="video_item">';
                videoHtml += '<a href="' + video.videoUrl + '" target="_blank">';
                videoHtml += '<div class="video_thumbnail">';
                videoHtml += '<div class="img_box">';
                videoHtml += '<img src="' + video.thumbnailUrl + '" alt="' + video.title + '썸네일">';
                videoHtml += '</div>';
                videoHtml += '</div>';
                videoHtml += '<div class="video_detail">';
                videoHtml += '<h4>' + video.title + '</h4>';
                // videoHtml += '<p>채널: ' + channelInfo.ycName + '</p>';
                videoHtml += '<p>조회수: ' + video.viewCount + '회</p>';
                videoHtml += '<p>좋아요: ' + video.likeCount + '</p>';
                // videoHtml += '<p>댓글 수: ' + video.commentCount + '</p>';
                videoHtml += '</div>';
                videoHtml += '</a>';
                videoHtml += '</div>';
            });
            $('#video_list').html(videoHtml);
            $('html, body').animate(
                {
                    scrollTop: $(".channels_box").offset().top - 120,
                },
                100 
            );
            $(".channels_box").addClass("show");
            $(".channels_video_list").addClass("show");
        }
    });
}
// YouTube 채널 검색 함수
function searchYouTubeChannel(query) {
    $.ajax({
        type: 'GET',
        url: '/searchChannel',
        data: { channelName: query }, // 검색어를 서버로 전송
        success: function(response) {
            // alert("확인");
            console.log("확인:",response);
             // JSON 문자열을 객체로 변환
            // const data = typeof response === "string" ? JSON.parse(response) : response;
            console.log("확인:",response.match);
            // const data = JSON.parse(response);
            if (response.match == false) {
                // 이름이 일치하지 않는 경우 모달을 띄움
                openModal(response.message);
            } else {
                // 이름이 일치하는 경우 결과를 처리
                const channelTabs = document.getElementById('channel-tabs');
                // 새 채널 정보 추가
                const newChannel = document.createElement('li');
                newChannel.innerHTML = `<a href="javascript:void(0);" class="channel-tab" data-channel-id="${response.ycId}">${response.ycName}</a>`;
                
                channelTabs.appendChild(newChannel);
                //탭 클릭 이벤트 처리
                // $('.channel-tab').click(function(e) {
                //     console.log("searchChannel");
                //     e.preventDefault();
                //     let ycId = $(this).data('channel-id');
                //     $('#loadingSpinner').show();
                //     loadChannelDetails(ycId);
                // });

                // alert("성공");
                // loadChannelDetails(channels[0].ycId);
                // displaySearchResult(response);
            }
            // displaySearchResults(response); //리스트
        },
        error: function(xhr, status, error) {
            console.error('AJAX 요청 중 오류 발생:', error);
        }
    });
}
// 검색 결과 표시 함수
function displaySearchResult(channel) {
    const resultsDiv = $('#searchResults');
    resultsDiv.empty(); // 기존 결과 초기화

    if (!channel) {
        resultsDiv.append('<p>결과가 없습니다.</p>');
        return;
    }

    const channelId = channel.ycId;
    const channelTitle = channel.ycName;
    const channelDescription = channel.channelsDescription;
    const channelThumbnail = channel.thumbnailUrl;
    const ucUrl = channel.channelUrl;

    const channelHtml = `
        <div class="channels_info" id="channel-${channelId}">
            <a href="${ucUrl}" target="_blank">
                <div class="channels_image">
                    <img src="${channelThumbnail}" alt="${channelTitle}">
                </div>
                <div class="channels_info_text">
                    <h3>${channelTitle}</h3>
                    <p>${channelDescription}</p>
                    <p>UC 주소: <a href="${ucUrl}" target="_blank">${ucUrl}</a></p>
                </div>
            </a>
        </div>
    `;
    resultsDiv.append(channelHtml); // 결과 추가
}
function displaySearchResults(channels) {
    const resultsDiv = $('#searchResults');
    resultsDiv.empty(); // 기존 결과 초기화

    if (channels.length === 0) {
        resultsDiv.append('<p>결과가 없습니다.</p>');
        return;
    }

    channels.forEach(channel => {
        const channelId = channel.ycId;
        const channelTitle = channel.ycName;
        const channelDescription = channel.channelsDescription;
        const channelThumbnail = channel.thumbnailUrl;
        const ucUrl = channel.channelUrl;

        const channelHtml = `
            <div class="channels_info" id="channel-${channelId}">
                <a href="${ucUrl}" target="_blank">
                    <div class="channels_image">
                        <img src="${channelThumbnail}" alt="${channelTitle}">
                    </div>
                    <div class="channels_info_text">
                        <h3>${channelTitle}</h3>
                        <p>${channelDescription}</p>
                        <p>UC 주소: <a href="${ucUrl}" target="_blank">${ucUrl}</a></p>
                    </div>
                </a>
            </div>
        `;
        resultsDiv.append(channelHtml); // 결과 추가
    });
}