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
            $('#channel_link').attr('href',channelInfo.channelUrl);
            $('#channel_Name').text(channelInfo.ycName);
            $('#channel_subscribersCount').text('구독자: ' + channelInfo.subscribersCount+'명');
            $('#channel_videosCount').text('총 영상 수: ' + channelInfo.videosCount);
            $('#channel_thumbnail').attr('src', channelInfo.thumbnailUrl);

            // 동영상 목록 표시
            let videos = data.latestVideos;
            let videoHtml = '';
            videos.forEach(function(video) {
                videoHtml += '<div class="video_item">';
                videoHtml += '<a href="' + video.videoUrl + '" target="_blank">';
                videoHtml += '<div class="video_thumbnail">';
                videoHtml += '<div class="img_box">';
                videoHtml += '<img src="' + video.thumbnailUrl + '" alt="' + video.title + '">';
                videoHtml += '</div>';
                videoHtml += '</div>';
                // video title hidden
                videoHtml += '<div class="video_detail">';
                videoHtml += '<h4>' + video.title + '</h4>';
                videoHtml += '<p>채널: ' + video.channelTitle + '</p>';
                videoHtml += '<p>조회수: ' + video.viewCount + '회</p>';
                videoHtml += '<p>좋아요: ' + video.likeCount + '</p>';
                videoHtml += '<p>댓글 수: ' + video.commentCount + '</p>';
                videoHtml += '</div>';
                videoHtml += '</a>';
                videoHtml += '</div>';
            });
            $('#video-list').html(videoHtml);
        }
    });
}