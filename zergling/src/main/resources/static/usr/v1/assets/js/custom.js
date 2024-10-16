document.addEventListener('DOMContentLoaded', function () {
    //
    const userId = document.getElementById("userId");
    const userPassword = document.getElementById("userPassword");
    // var feedbackText = [];
    const feedbackList = document.querySelectorAll(".invalid-feedback");
    if(feedbackList){
        feedbackList.forEach(element => {
            
        });
    }
    const form = document.querySelector("form");
    var formUrl;
    if(form){
        formUrl = form.action;
    }
    
    

    //정규식
    var idRegExp = /^[a-zA-Z]{1}[a-zA-Z0-9]{4,14}$/;
    var passwordRegExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    var krAlphaNumRegExp = /^[ㄱ-ㅎ가-힣A-Za-z0-9]+$/;
    var krNameRegExp = /^[가-힣]{2,4}$/;
    var alphaNumRegExp = /^[a-zA-Z0-9]+$/;
    var numericRegExp = /^[0-9]+$/;
    var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    var birthRegExp =  /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
    var phoneRegExp = /^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$/;
    
    //정규식text
    const codeRegExpText = "이름은 한글, 영대소문자, 숫자만 입력해 주세요";
    const inputNullText = "내용을 적어주세요.";
    const selectNullText = "내용을 선택해주세요.";
    const alphaNumRegExpText = "영대소문자, 숫자만 입력해 주세요";
    const numRegExpText = "정수형 숫자만 입력해 주세요";
    const idRegExpText = "아이디는 첫글자는 영문자로 시작하며, 5~15자의 영대소문자와 숫자만 포함해야 합니다.";
    const pwRegExpText = "비밀번호는 8~15자 사이여야 하며, 최소 1개의 숫자, 영문자, 특수문자를 포함해야 합니다.";
    const pwCkRegExpText = "비밀번호가 일치하지 않습니다."
    const emailRegExpText = "이메일 형식에 따라 정확히 입력해주세요";
    const telRegExpText = "정확한 핸드폰번호를 입력해주세요: - 제외";
    const birthRegExpText = "정확한 생년월일 8자리를 입력해주세요";
    const nameRegExpText = "한글만 입력해주세요.";
    const genderRegExpText = "성별을 선택해주세요.";

    //로그인
    const loginBtn = document.querySelector("#loginBtn");
    console.log("loginBtn: "+loginBtn);
    if(loginBtn){
        const feedbackChk = document.querySelector(".invalid-child");
        const feedback = document.querySelector(".invalid-feedback");
        loginBtn.addEventListener("click", function(event) {
            // const loginBoxParent = element.closest('.login-box');
            event.preventDefault();

            let idValid = true;
            let pwValid = true;
            let idValue = userId.value.trim();
            let PasswordValue = userPassword.value.trim();
    
            // 아이디 필드 체크
            if (idValue == "" || idValue == null) {
                feedback.textContent = "아이디를 입력해 주세요.";
                userId.classList.add('is-invalid');
                feedbackChk.classList.add('is-invalid');
                idValid = false;
            } else {
                
                if(!idRegExp.test(idValue)){
                    userId.classList.add('is-invalid');
                    userPassword.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    feedback.textContent = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                    idValid = false; 
                    return false;
                }else{
                    userId.classList.remove('is-invalid');
                    feedbackChk.classList.remove('is-invalid');
                }
                
            }
    
            // 비밀번호 필드 체크
            if (PasswordValue == "" || PasswordValue == null) {
                feedback.textContent = "비밀번호를 입력해 주세요.";
                userPassword.classList.add('is-invalid');
                feedbackChk.classList.add('is-invalid');
                pwValid = false;
            } else {
                userPassword.classList.remove('is-invalid');
                feedbackChk.classList.remove('is-invalid');
            }
    
            // 아이디와 비밀번호 모두 입력하지 않은 경우 처리
            if (!idValid && !pwValid) {
                feedback.textContent = "아이디와 비밀번호를 입력해 주세요.";
            }
    
            // 둘 다 중에 하나라도 유효하지 않으면 submit 방지
            if (!idValid || !pwValid) {
                feedbackChk.classList.add('is-invalid');
                return false;
            }
            
            // let token = grecaptcha.getResponse();
            // console.log("토큰:",token);
            // if (!token) {
            //     feedback.textContent = "reCAPTCHA를 확인해 주세요.";
            //     return false;
            // }

            userId.classList.remove('is-invalid');
            userPassword.classList.remove('is-invalid');
            feedbackChk.classList.remove('is-invalid');
            feedback.textContent = "";
            
            //ajax 로그인
            $.ajax({
                async: true 
                ,cache: false
                ,type: "post"
                /* ,dataType:"json" */
                ,url: "/loginUsrProc"
                /* ,data : $("#formLogin").serialize() */
                ,data : { 
                    "userId" : $("#userId").val().trim(),
                    "userPassword" : $("#userPassword").val(),
                    // token: token,
                    recaptchaAction:"login",
                    captchaCode: $("#captchaCode").val(),
                }//, "autoLogin" : $("#autoLogin").is(":checked")}
                ,success: function(response) {
                    if(response.rt == "success") {
                        userId.classList.remove('is-invalid');
                        userPassword.classList.remove('is-invalid');
                        feedbackChk.classList.remove('is-invalid');
                        // console.log("response:"+response.rt);
                        // if(response.rtp == "buy"){
                        //     location.href = "product_buy";
                        // }else{
                            
                        // }
                        window.location.href = response.redirectUrl;
                        // location.href = "index";
                        
                    } else {
                        userId.classList.add('is-invalid');
                        userPassword.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                        document.getElementById("invalid-feedback").innerText = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                    }
                }
                ,error : function(jqXHR, textStatus, errorThrown){
                    alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                }
            });
        })
    }
    //엔터 로그인
    if(document.getElementById("loginform")){
        document.getElementById("loginform").addEventListener("keydown", function(event) {
            const feedbackChk = document.querySelector(".invalid-child");
            const feedback = document.querySelector(".invalid-feedback");
            if (event.key === "Enter") {
                let idValid = true;
                let pwValid = true;
                let idValue = userId.value.trim();
                let PasswordValue = userPassword.value.trim();
        
                // 아이디 필드 체크
                if (idValue == "" || idValue == null) {
                    feedback.textContent = "아이디를 입력해 주세요.";
                    userId.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    idValid = false;
                } else {
                    
                    if(!idRegExp.test(idValue)){
                        userId.classList.add('is-invalid');
                        userPassword.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                        feedback.textContent = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                        idValid = false; 
                        return false;
                    }else{
                        userId.classList.remove('is-invalid');
                        feedbackChk.classList.remove('is-invalid');
                    }
                    
                }
        
                // 비밀번호 필드 체크
                if (PasswordValue == "" || PasswordValue == null) {
                    feedback.textContent = "비밀번호를 입력해 주세요.";
                    userPassword.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                    pwValid = false;
                } else {
                    userPassword.classList.remove('is-invalid');
                    feedbackChk.classList.remove('is-invalid');
                }
        
                // 아이디와 비밀번호 모두 입력하지 않은 경우 처리
                if (!idValid && !pwValid) {
                    feedback.textContent = "아이디와 비밀번호를 입력해 주세요.";
                }
        
                // 둘 다 중에 하나라도 유효하지 않으면 submit 방지
                if (!idValid || !pwValid) {
                    feedbackChk.classList.add('is-invalid');
                    return false;
                }
                
                userId.classList.remove('is-invalid');
                userPassword.classList.remove('is-invalid');
                feedbackChk.classList.remove('is-invalid');
                feedback.textContent = "";
        
                //ajax 로그인
                $.ajax({
                    async: true 
                    ,cache: false
                    ,type: "post"
                    /* ,dataType:"json" */
                    ,url: "/loginUsrProc"
                    /* ,data : $("#formLogin").serialize() */
                    ,data : { "userId" : $("#userId").val().trim(), "userPassword" : $("#userPassword").val() }//, "autoLogin" : $("#autoLogin").is(":checked")}
                    ,success: function(response) {
                        if(response.rt == "success") {
                            userId.classList.remove('is-invalid');
                            userPassword.classList.remove('is-invalid');
                            feedbackChk.classList.remove('is-invalid');
                            // console.log("response:"+response.rt);
                            // if(response.rtp == "buy"){
                            //     location.href = "product_buy";
                            // }else{
                                
                            // }
                            window.location.href = response.redirectUrl;
                            // location.href = "index";
                            
                        } else {
                            userId.classList.add('is-invalid');
                            userPassword.classList.add('is-invalid');
                            feedbackChk.classList.add('is-invalid');
                            document.getElementById("invalid-feedback").innerText = "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.";
                        }
                    }
                    ,error : function(jqXHR, textStatus, errorThrown){
                        alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                    }
                });
            }
        })
    }
    

    //로그아웃
    const logoutBtn = document.querySelectorAll(".logoutBtn");
    console.log("logoutBtn: "+logoutBtn);
    if (logoutBtn) {
        logoutBtn.forEach(element => {
            element.onclick = function (){
                //ajax 로그아웃
                $.ajax({
                    async: true 
                    ,cache: false
                    ,type: "post"
                    /* ,dataType:"json" */
                    ,url: "/logoutUsrProc"
                    /* ,data : $("#formLogin").serialize() */
                    // ,data : { "userId" : $("#userId").val(), "userPassword" : $("#userPassword").val() }//, "autoLogin" : $("#autoLogin").is(":checked")}
                    ,success: function(response) {
                        location.href = "index";
                    }
                    ,error : function(jqXHR, textStatus, errorThrown){
                        alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                    }
                });
            }
        });
    }

    //밸리데이션
    // console.log(feedbackText); 
    function RegExps(element,objValue,feedback) {
        console.log("태그: "+element.id);
        console.log("클래스: "+element.classList);
        
            if (element.classList.contains('valid-korean-alpha-num')) {
                console.log("특문빼고");
                
                if(!krAlphaNumRegExp.test(objValue)){
                    feedback.textContent = codeRegExpText;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            } else if (element.classList.contains('valid-alpha-num')) {
                console.log("한글빼고");
                
                if(!alphaNumRegExp.test(objValue)){
                    feedback.textContent = alphaNumRegExpText;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            } else if (element.classList.contains('valid-numeric')) {
                console.log("숫자만");
                
                if(!numericRegExp.test(objValue)){
                    feedback.textContent = numRegExpText;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            }
            else if (element.classList.contains('valid-email')) {
                console.log("이메일만");
                
                //정규식
                //https://choijying21.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-%EC%9E%90%EC%A3%BC-%EC%93%B0%EB%8A%94-%EC%A0%95%EA%B7%9C%EC%8B%9D-%EB%AA%A8%EC%9D%8C-%EC%9D%B4%EB%A9%94%EC%9D%BC-%ED%95%B8%EB%93%9C%ED%8F%B0-%EC%A3%BC%EB%AF%BC%EB%B2%88%ED%98%B8-%EB%93%B1
    
                if(!emailRegExp.test(objValue)){
                    var text = "이메일 형식에 따라 정확히 입력해주세요";
                    feedback.textContent = text;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            } else if (element.classList.contains('valid-birth-num')) {
                console.log("생년월일만");
                
        //출처: https://choijying21.tistory.com/entry/자바스크립트-자주-쓰는-정규식-모음-이메일-핸드폰-주민번호-등 [JDevelog:티스토리]
                console.log("생일: "+objValue);
                if(!birthRegExp.test(objValue)){
                    var text = "정확한 생년월일 8자리를 입력해주세요";
                    feedback.textContent = text;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            }
            else if (element.classList.contains('valid-id')) {
                console.log("아이디형식");
                if(!idRegExp.test(objValue)){
                    var text = "아이디는 첫글자는 영문자로 시작하며, 5~15자의 영대소문자와 숫자만 포함해야 합니다.";
                    feedback.textContent = text;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            }
            else if (element.classList.contains('valid-password')) {
                console.log("비밀번호형식");

                if(!passwordRegExp.test(objValue)){
                    var text = "비밀번호는 8~15자 사이여야 하며, 최소 1개의 숫자, 영문자, 특수문자를 포함해야 합니다.";
                    feedback.textContent = text;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    
                    return true;
                }
            }
            else if (element.classList.contains('valid-passwordCk')) {
                console.log("비밀번호체크형식");
                const pw = document.querySelector(".valid-password");
                if(!(pw.value == objValue)){
                    feedback.textContent = pwCkRegExpText;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    
                    return true;
                }
            }
            else if (element.classList.contains('valid-phone-num')) {
                console.log("핸드폰번호 숫자만");
                
                if(!phoneRegExp.test(objValue)){
                    var text = "정확한 핸드폰번호를 입력해주세요: - 제외";
                    feedback.textContent = text;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            }else if (element.classList.contains('valid-user-name')) {
                console.log("한글만");
                
                if(!krNameRegExp.test(objValue)){
                    var text = "최소 2자 이상, 최대 4자의 한글만 입력해주세요";
                    feedback.textContent = text;
                    element.focus();
                    return false;
                } else {
        // 	    	by pass
                    return true;
                }
            }
            else{
                console.log("else 통과");
                return true;
            }
    }
    //제출버튼
    const btnSubmit = document.getElementById("btnSubmit");
    if (btnSubmit) {
        btnSubmit.onclick = function (){
            
            var objs = document.querySelectorAll(".validate-this");
            var i= 0;
            var validateChk = [];
            for(let element of objs){
                var objValue = element.value.trim();
                // const elementBox = element.parentElement;
                let elementBox;
                if(element.closest("td")){
                    elementBox = element.closest("td");
                }else if (element.closest("li")) {
                    elementBox = element.closest("li");
                }
                const invalidBoxChk = elementBox.querySelector(".invalid-box");
                if(invalidBoxChk){
                    elementBox.querySelector(".invalid-box").remove();
                }
                // console.log("elementBox:" +elementBox.outerHTML);
                const feedbackBox = document.createElement("div");
                const feedbackChild = document.createElement("div");
                const feedbackinner = document.createElement("div");
                feedbackBox.className  = "invalid-box mb-3";
                feedbackChild.className  = "invalid-child";
                feedbackinner.className  = "invalid-feedback";
                feedbackinner.id  = "invalid-feedback";
                feedbackBox.appendChild(feedbackChild);
                feedbackBox.appendChild(feedbackinner);
                elementBox.appendChild(feedbackBox);
                const feedbackChk = elementBox.querySelector(".invalid-child");
                const feedback = elementBox.querySelector(".invalid-feedback");
                if (objValue == "" || objValue == null) {
                    // var waring = feedback.textContent.trim();
                    if(element.tagName ==='INPUT'){
                        // console.log("INPUT1");
                        if(element.classList.contains('valid-birth-num')){
                            feedback.textContent = birthRegExpText;
                            // console.log("INPUT2");
                        }else{
                            feedback.textContent = inputNullText;
                            // console.log("INPUT3");
                        }
                        // alert(inputNullText);
                    }else if(element.tagName ==='SELECT'){
                        // console.log("SELECT1");
                        if(element.classList.contains('valid-gender')){
                            feedback.textContent = genderRegExpText;
                            // console.log("SELECT2");
                        }else{
                            feedback.textContent = selectNullText;
                            // console.log("SELECT3");
                        }
                        // alert(selectNullText);
                    }
                    
                    
                    if(i==0){
                        element.focus();
                    }
                    
                    validateChk[i] = false;
                    i++;
                    element.classList.add('is-invalid');
                    feedbackChk.classList.add('is-invalid');
                } else {
                    //by pass
                    var val  = RegExps(element,objValue,feedback);
                    console.log("체크");
                    if(val == true){
                        validateChk[i] = true;
                        i++;
                        element.classList.remove('is-invalid');
                        element.classList.add('is-valid');
                        feedbackChk.classList.remove('is-invalid');
                    }else{
                        if(i==0){
                            element.focus();
                        }
                        
                        validateChk[i] = false;
                        i++;
                        element.classList.add('is-invalid');
                        feedbackChk.classList.add('is-invalid');
                    }
                    
                }
            };
            console.log("validateChk: "+validateChk);
            if(validateChk.includes(false)){
                // alert("검사실패");
                return false;
            }
            // alert("통과!");
            form.action = formUrl;
            form.submit();
            
        }
    }

    //비밀번호 on/off
    const togglePasswordElements = document.querySelectorAll('.toggle-password');
    const passwordInputs = document.querySelectorAll('.login_password,.signup_password');
    if(passwordInputs){
        togglePasswordElements.forEach(function (togglePassword, index) {
            togglePassword.addEventListener('click', function () {
                // 현재 입력 필드의 type 속성 값을 확인하고, toggle
                const passwordInput = passwordInputs[index];
                const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
                passwordInput.setAttribute('type', type);
                
                // 버튼 아이콘 변경
                this.style.backgroundImage = type === 'password' 
                    ? "url('/usr/v1/assets/images/ico_eye@2x.png')" 
                    : "url('/usr/v1/assets/images/ico_eye_active@2x.png')";
            });
        });
    }
    
    

    //탭 메뉴
    const tabButtons = document.querySelectorAll('.tab_button');
    const tabContents = document.querySelectorAll('.tab_content');
    const tabBox = document.querySelector('.type_tab');

    tabButtons.forEach(button => {
        button.addEventListener('click', function () {
            // 모든 탭 버튼에서 'active' 클래스를 제거
            tabButtons.forEach(btn => btn.classList.remove('active'));

            // 모든 탭 콘텐츠에서 'active' 클래스를 제거
            tabContents.forEach(content => content.classList.remove('active'));

            // 클릭된 버튼에 'active' 클래스를 추가
            this.classList.add('active');

            // 해당 콘텐츠를 표시
            const target = this.getAttribute('data-target');
            document.getElementById(target).classList.add('active');
        });
    });

    // 기본적으로 첫 번째 탭과 콘텐츠를 활성화
    // if () {
        
    // }
    if (tabButtons.length > 0) {
        tabButtons[0].classList.add('active');
        tabContents[0].classList.add('active');
    }

    /*스크롤 헤더*/
    /*탑버튼 */
    if(document.querySelector(".top_btn")){
        document.querySelector(".top_btn").textContent="TOP";
        var prevScrollpos = window.scrollY; 
        // console.log(window.scrollY); 
        if(prevScrollpos >= 50){
            document.querySelector(".top_btn").style.display = "flex";
        }else{
            document.querySelector(".top_btn").style.display = "none";
        }

        window.addEventListener('scroll',  function() { 
            var currentScrollpos = window.scrollY;
            // console.log("sksksksks" + currentScrollpos);
            if(currentScrollpos >= 50| prevScrollpos >= 50){
                document.querySelector(".top_btn").style.display = "flex";
            }else{
                document.querySelector(".top_btn").style.display = "none";
            }
            prevScrollpos = currentScrollpos; 
        });
        document.querySelector(".top_btn").addEventListener('click', function() {
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        });
    }
    
    

      
    //컨텐츠접고 펼치키
    const toggleButtons = document.querySelectorAll('.collapse_btn');
    const contents = document.querySelectorAll('.info_text');
    // console.log(toggleButtons);
    // console.log(contents);
    
    contents.forEach((content) => {
        content.classList.add('hidden');
    });
    toggleButtons.forEach((btuton) => {
        btuton.classList.add('hidden');
    });

    toggleButtons.forEach((button, index) => {
        button.addEventListener('click', function() {
            const content = contents[index];
            if (content.classList.contains('hidden')) {
                content.classList.remove('hidden');
                button.classList.remove('hidden');
                button.textContent = '접기';
                
            } else {
                content.classList.add('hidden');
                button.classList.add('hidden');
                button.textContent = '펼치기';
            }
        });
    });
    
    // 초기 상태에서 각 콘텐츠의 길이 체크
    contents.forEach((content, index) => {
        const lineHeight = parseInt(window.getComputedStyle(content).lineHeight);
        if (content.scrollHeight > lineHeight * 4) { // 4줄 이상일 경우
            toggleButtons[index].classList.remove('hidden'); // 버튼 보이기
        } else {
            toggleButtons[index].remove(); // 버튼 제거
        }
    });


    /*탭 선택시 영역으로 화면 스크롤 */
    let tabs = document.querySelectorAll(".detail_tabs");
    let review = document.querySelector(".book_review");
    let information = document.querySelector(".prod_detail_info_wrap");
    // let qna = document.querySelector(".qna");
    // let exchange = document.querySelector(".exchange");
    let scrollElementOffsetTop;
    for (let i = 0; i < tabs.length; i++) {
        tabs[i].addEventListener('click', () => {
            switch (i) {
                case 0:
                    scrollElementOffsetTop = information.offsetTop - 90;
                    // console.log(this);
                    // tabs[0].classList.add("active");
                    // tabs[1].classList.remove("active");
                    // tabs[2].classList.remove("active");
                    // tabs[3].classList.remove("active");
                    break;
                case 1:
                    scrollElementOffsetTop = review.offsetTop;
                    // tabs[0].classList.remove("active");
                    // tabs[1].classList.add("active");
                    // tabs[2].classList.remove("active");
                    // tabs[3].classList.remove("active");
                    break;
                case 2:
                    scrollElementOffsetTop = qna.offsetTop;
                    // tabs[0].classList.remove("active");
                    // tabs[1].classList.remove("active");
                    // tabs[2].classList.add("active");
                    // tabs[3].classList.remove("active");
                    break;
                case 3:
                    scrollElementOffsetTop = exchange.offsetTop;
                    // tabs[0].classList.remove("active");
                    // tabs[1].classList.remove("active");
                    // tabs[2].classList.remove("active");
                    // tabs[3].classList.add("active");
                    break;
            }
            scrollElementOffsetTop = scrollElementOffsetTop - 90;
            window.scrollTo({
                top: scrollElementOffsetTop,
                behavior: 'smooth'
            });
        });   
    }

    //헤더 사이트맵?열고 닫기

    // const anbBtn = document.querySelector(".gnb_wrapper .circle_btn");
    const anbBtn = document.querySelector(".circle_btn");
    // const anbBtnInner = document.querySelector(".gnb_wrapper .circle_btn i");
    const anbBtnInner = document.querySelector(".circle_btn i");
    const anbWrapper = document.querySelector(".anb_wrapper");
    if(anbBtn){
        anbBtn.addEventListener('click',function() {
        
            //anbWrapper.classList.toggle("active");
            if (anbWrapper.classList.contains('active')) {
                anbBtnInner.classList.remove('fa-xmark');
                anbBtnInner.classList.add('fa-bars');
                anbBtn.style.backgroundColor = "#fff";
                anbBtnInner.style.color = "#000";
                anbWrapper.classList.remove('active');
                setTimeout(() => {
                    anbWrapper.style.display = 'none'; // opacity 애니메이션이 끝난 후에 display 변경
                    console.log("none");
                }, 100); // transition 시간이 0.5초이므로 동일한 시간으로 설정
                
               
            } else {
                console.log("block");
                anbWrapper.style.display = 'block'; // display를 block으로 설정한 후
                anbBtn.style.backgroundColor = "#000";
                anbBtnInner.style.color = "#fff";
                
                setTimeout(() => {
                    anbWrapper.classList.add('active'); // 활성화하여 opacity를 1로
                    console.log("active");
                }, 10); // display가 적용된 후 약간의 지연을 주어 opacity 변화
                anbBtnInner.classList.remove('fa-bars');
                anbBtnInner.classList.add('fa-xmark');
            }
        });
    }
    

    //페이지네이션 
    goList = function (thisPage) {
        console.log("값: "+thisPage);
        document.querySelector("input[name=thisPage]").value = thisPage;
        console.log("thisPage값: "+document.querySelector("input[name=thisPage]").value);
        form.action = url;
        form.submit();
    }
    
    
    /*숫자 +- 버튼 , 계산*/
    let value =  Number(1);
    // let quantity = [0,0,0,1];
    let quantity = 1;

    // 숫자 값을 표시할 요소 선택
    let numberInput = document.querySelector('.number-input input');
    // 올리기 버튼과 내리기 버튼 요소 선택
    let increaseButton = document.querySelector('.increase-button');
    let decreaseButton = document.querySelector('.decrease-button');

    if(increaseButton&&decreaseButton&&numberInput){
        // 올리기 버튼 클릭 이벤트 처리
        increaseButton.addEventListener('click', () => {
            value = Number(numberInput.value);
            
            value++;
            if (value > 99) {
                value = 99;
            }
            // quantity = value;
            numberInput.value = value;
        });


        // 내리기 버튼 클릭 이벤트 처리
        decreaseButton.addEventListener('click', () => {
            value = Number(numberInput.value);
            value--;
            if (value < 1) {
                value = 1;
            }
            // quantity = value;
            numberInput.value = value;
        });

        /* 숫자가 아니거 1 미만 일때 */
        numberInput.addEventListener('input',function() {
            let value = parseInt(this.value);
            console.log("this:"+this.value);
            console.log(value);
            if (value < 1) {
                this.value = 1;
                value = 1;
            }
            else if(isNaN(value)){
                console.log("isNaN");
                this.value = 1;
                value = 1;
            }
            if (value > 99) {
                this.value = 99;
                value = 99;
            }
            numberInput.value = value;
        });
    }
    
    //양방향 range

    
    
    // 슬라이더와 출력 입력 필드를 참조하는 변수들
    // const inputLeft = document.getElementById("price_range1"); // 왼쪽 슬라이더
    // const inputRight = document.getElementById("price_range2"); // 오른쪽 슬라이더

    // const thumbLeft = document.querySelector(".thumb.left"); // 왼쪽 핸들
    // const thumbRight = document.querySelector(".thumb.right"); // 오른쪽 핸들

    // const range = document.querySelector(".range"); // 슬라이더의 범위 배경

    // const minValueDisplay = document.getElementById("minValue"); // 왼쪽 출력 입력 필드
    // const maxValueDisplay = document.getElementById("maxValue"); // 오른쪽 출력 입력 필드

    // // 슬라이더와 출력 입력 필드를 동기화하는 함수
    // const updateSlider = () => {
    //     // 슬라이더의 값을 가져오고 숫자로 변환
    //     const leftValue = +inputLeft.value; // 왼쪽 슬라이더의 값
    //     const rightValue = +inputRight.value; // 오른쪽 슬라이더의 값

    //     // 왼쪽 슬라이더의 백분율 위치 계산
    //     const minPercent = ((leftValue - +inputLeft.min) / (+inputLeft.max - +inputLeft.min)) * 100;
    //     // 오른쪽 슬라이더의 백분율 위치 계산
    //     const maxPercent = ((rightValue - +inputRight.min) / (+inputRight.max - +inputRight.min)) * 100;

    //     // 왼쪽 핸들의 위치를 설정
    //     thumbLeft.style.left = `${minPercent}%`;
    //     // 오른쪽 핸들의 위치를 설정
    //     thumbRight.style.right = `${100 - maxPercent}%`;

    //     // 슬라이더의 범위 배경의 위치를 설정
    //     range.style.left = `${minPercent}%`;
    //     range.style.right = `${100 - maxPercent}%`;

    //     // 출력 입력 필드에 슬라이더의 값을 표시
    //     minValueDisplay.value = leftValue;
    //     maxValueDisplay.value = rightValue;
    // };

    // // 왼쪽 슬라이더의 값이 변경되었을 때 호출되는 함수
    // const setLeftValue = (e) => {
    //     const { value } = e.target; // 이벤트 타겟에서 값을 가져옴

    //     // 오른쪽 슬라이더와의 최소 거리(10)를 유지하며 값 조정
    //     if (+inputRight.value - +value < 1) {
    //         inputLeft.value = +inputRight.value - 1; // 최소값을 조정
    //     }
    //     updateSlider(); // 슬라이더 및 출력 입력 필드를 업데이트
    // };

    // // 오른쪽 슬라이더의 값이 변경되었을 때 호출되는 함수
    // const setRightValue = (e) => {
    //     const { value } = e.target; // 이벤트 타겟에서 값을 가져옴

    //     // 왼쪽 슬라이더와의 최소 거리(10)를 유지하며 값 조정
    //     if (+value - +inputLeft.value < 10) {
    //         inputRight.value = +inputLeft.value + 10; // 최대값을 조정
    //     }
    //     updateSlider(); // 슬라이더 및 출력 입력 필드를 업데이트
    // };

    // // 출력 입력 필드의 값이 변경되었을 때 호출되는 함수
    // const handleInputChange = (e) => {
    //     const { id, value } = e.target; // 이벤트 타겟에서 id와 값을 가져옴
    //     if (id === "minValue") {
    //         // 최소값 입력 필드가 변경되었을 때
    //         inputLeft.value = Math.min(Math.max(+value, +inputLeft.min), +inputRight.value - 10); // 최소값을 설정
    //         updateSlider(); // 슬라이더 및 출력 입력 필드를 업데이트
    //     } else if (id === "maxValue") {
    //         // 최대값 입력 필드가 변경되었을 때
    //         inputRight.value = Math.max(Math.min(+value, +inputRight.max), +inputLeft.value + 10); // 최대값을 설정
    //         updateSlider(); // 슬라이더 및 출력 입력 필드를 업데이트
    //     }
    // };

    // // 이벤트 리스너를 추가하여 슬라이더와 출력 입력 필드의 변경을 처리합니다
    // if (inputLeft && inputRight) {
    //     inputLeft.addEventListener("input", setLeftValue); // 왼쪽 슬라이더의 값 변경 시 처리
    //     inputRight.addEventListener("input", setRightValue); // 오른쪽 슬라이더의 값 변경 시 처리
    // }

    // minValueDisplay.addEventListener("input", handleInputChange); // 최소값 출력 입력 필드의 값 변경 시 처리
    // maxValueDisplay.addEventListener("input", handleInputChange); // 최대값 출력 입력 필드의 값 변경 시 처리

    // // 페이지 로드 시 슬라이더와 출력 입력 필드를 동기화합니다
    // updateSlider();

    //양방향 range

    // 입력 요소와 슬라이더의 Thumb 및 범위 요소를 가져옵니다.
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
    // 왼쪽 입력 요소와 오른쪽 입력 요소에 입력 이벤트 리스너를 추가합니다.
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
    //             console.log("왼쪽: "+leftThumb.style.left);
    //          }
    //          rangeInput.value = numberInput.value;
    //          console.log(typeof(rangeInput.value));
    //          console.log(numberInput + " | " + rangeInput.value);
    //      });
         
    //  });
     

    //  var slider = document.getElementById('review-slider');
    //  var minPriceInput = document.getElementById('min-price');
    //  var maxPriceInput = document.getElementById('max-price');
    //  var margin = 1;

    //  noUiSlider.create(slider, {
    //      start: [0, 10], // 초기값
    //      connect: true,
    //      range: {
    //          'min': 0,
    //          'max': 10
    //      },
    //      step: 1, // 스텝 간격
    //      margin: margin, // 두 핸들 간의 간격을 300으로 설정
    //      format: {
    //          to: function (value) {
    //              return value.toFixed(0); // 소수점 없이 정수로 표시
    //          },
    //          from: function (value) {
    //              return Number(value); // 문자열을 숫자로 변환
    //          }
    //      }
    //  });

    //  // 슬라이더의 값을 input 필드에 반영
    //  slider.noUiSlider.on('update', function (values) {
    //      minPriceInput.value = values[0];
    //      maxPriceInput.value = values[1];
    //  });

    //  // input 필드의 값을 슬라이더에 반영
    //  minPriceInput.addEventListener('change', function () {
    //      var minValue = parseFloat(this.value);
    //      var maxValue = parseFloat(maxPriceInput.value);
    //      if (maxValue - minValue < margin) {
    //          if(minValue == maxPriceInput.max){
    //              minPriceInput.value = maxPriceInput.max - margin;
    //          }else {
    //              maxValue = minValue + margin;
    //              maxPriceInput.value = maxValue;
    //          }  
    //      }
    //      slider.noUiSlider.set([this.value, null]);
    //  });

    //  maxPriceInput.addEventListener('change', function () {
    //      var maxValue = parseFloat(this.value);
    //      var minValue = parseFloat(minPriceInput.value);
    //      if (maxValue - minValue < margin) {
    //          minValue = maxValue - margin;
    //          minPriceInput.value = minValue;
    //      }
    //      slider.noUiSlider.set([null, this.value]);
    //  });




    //input연결
    // const totalCash = document.querySelector('.cash_point_total span');
    // const useCashPoints = document.querySelectorAll(".use_number input");
    // const availables = document.querySelectorAll(".available span");

    // // 쉼표를 제거한 후 숫자로 변환하는 유틸리티 함수
    // function parseCurrency(value) {
    //     return parseInt(value.replace(/,/g, ''), 10); // 쉼표를 제거하고 숫자로 변환
    // }

    // // 값을 합산하여 totalCash에 출력하는 함수
    // function updateTotalCash() {
    //     let total = 0;

    //     // 각 input 요소의 값을 더함
    //     useCashPoints.forEach((input, index)=> {
    //         const maxAvailable = parseCurrency(availables[index].textContent);
    //         if (parseInt(input.value) > maxAvailable) {
    //             input.value = maxAvailable;
    //         }

    //         total += parseInt(input.value) || 0; // 입력 값이 숫자가 아닌 경우 0으로 처리
    //     });
    //     // 총합을 totalCash 요소에 출력
    //     totalCash.textContent = total.toLocaleString();
    // }

    // // 모든 캐시 또는 포인트를 사용할 때 호출되는 함수
    // function useAllCashOrPoints(index) {
    //     const maxAvailable = parseCurrency(availables[index].textContent); // 보유한 최대 값
    //     useCashPoints[index].value = maxAvailable; // 해당 입력 필드에 최대 값을 설정
    //     updateTotalCash(); // 총합 업데이트
    // }

    // // 각 "use_all" 버튼에 이벤트 리스너 추가
    // const useAllButtons = document.querySelectorAll('.use_all');
    // useAllButtons.forEach((button, index) => {
    //     button.addEventListener('click', () => useAllCashOrPoints(index));
    // });

    // // 두 input 요소에 이벤트 리스너를 추가하여 값이 변경될 때마다 합계를 업데이트
    // useCashPoints.forEach(input => {
    //     input.addEventListener('input', updateTotalCash);
    // });
    // updateTotalCash();
    // // 입력 시 쉼표 형식을 적용하고, 총합 업데이트
    // // useCashPoints.forEach(input => {
    // //     input.addEventListener('input', () => {
    // //         let value = parseCurrency(input.value) || 0; // 입력 값에서 쉼표를 제거하고 숫자로 변환
    // //         input.value = formatCurrency(value); // 쉼표 형식을 다시 적용
    // //         console.log
    // //         updateTotalCash(); // 총합 업데이트
    // //     });
    // // });

    // // 초기 합계를 계산
    


    // //자동 가격 계산//

    // // 각 요소를 선택
    // const prodTotalPrice = document.querySelector('.prod_total_price span');
    // const salePrice = document.querySelector('.sale_price span');
    // const pointPrice = document.querySelector('.point_price span');
    // const orderTotalPrice = document.querySelector('.order_total_price span');
    // const pendingPoints = document.querySelector('.pending_points span');

    // // 문자열을 숫자로 변환하는 함수 (쉼표 제거)
    // function parseCurrency(value) {
    //     return parseInt(value.replace(/,/g, ''), 10);
    // }

    // // 숫자를 쉼표 포함 형식으로 변환하는 함수
    // function formatCurrency(value) {
    //     return value.toLocaleString();
    // }

    // // 할인 계산, 포인트 계산, 최종 금액 계산, 적립 포인트 계산
    // function calculatePrices() {
    //     const originalPrice = parseCurrency(prodTotalPrice.textContent); // 상품 가격
    //     const discount = Math.floor(originalPrice * 0.1); // 10% 할인 계산
    //     const discountedPrice = originalPrice - discount; // 할인된 가격
    //     const pointsApplied = parseCurrency(totalCash.textContent); // 사용된 포인트 (캐시)
    //     const finalPrice = discountedPrice - pointsApplied; // 최종 결제 금액
        
    //     // 적립 포인트 (최종 결제 금액의 5%)
    //     let earnedPoints = finalPrice * 0.05;
    //     earnedPoints = Math.floor(earnedPoints / 10) * 10; // 10단위로 내림 처리

    //     // 값을 DOM에 업데이트
    //     salePrice.textContent = formatCurrency(discount); // 할인 적용 후 가격
    //     orderTotalPrice.textContent = formatCurrency(finalPrice); // 최종 결제 금액
    //     pendingPoints.textContent = formatCurrency(earnedPoints); // 적립 포인트
    // }
    // // 페이지 로드 시 가격 계산
    // calculatePrices();
    
    // // 요소 선택
    // const totalCash = document.querySelector('.cash_point_total span');
    // const useCashPoints = document.querySelectorAll(".use_number input");
    // const availables = document.querySelectorAll(".available span");
    // const prodTotalPrice = document.querySelector('.prod_total_price span');
    // const salePrice = document.querySelector('.sale_price span');
    // const orderTotalPrice = document.querySelector('.order_total_price span');
    // const pendingPoints = document.querySelector('.pending_points span');
    // const pointPrice = document.querySelector('.point_price span');

    // // 문자열에서 쉼표를 제거하고 숫자로 변환하는 함수
    // function parseCurrency(value) {
    //     return parseInt(value.replace(/,/g, ''), 10); // 쉼표를 제거하고 숫자로 변환
    // }

    // // 숫자를 쉼표 포함 형식으로 변환하는 함수
    // function formatCurrency(value) {
    //     return value.toLocaleString();
    // }

    // // 숫자가 유효한지 검사하는 함수 (10의 배수인지 확인)
    // function isValidValue(value) {
    //     return value % 10 === 0;
    // }

    // // 입력값을 10의 배수로 내림 처리하는 함수
    // function roundToNearestTen(value) {
    //     return Math.floor(value / 10) * 10;
    // }

    // // 총합을 계산하여 totalCash와 pointPrice에 출력하는 함수
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

    // // 모든 캐시 또는 포인트를 사용할 때 호출되는 함수
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

    // // 각 "use_all" 버튼에 이벤트 리스너 추가
    // const useAllButtons = document.querySelectorAll('.use_all');
    // useAllButtons.forEach((button, index) => {
    //     button.addEventListener('click', () => useAllCashOrPoints(index));
    // });

    // // 할인 계산, 포인트 적용 후 최종 금액 및 적립 포인트 계산 함수
    // function calculatePrices() {
    //     const originalPrice = parseCurrency(prodTotalPrice.textContent); // 상품 가격
    //     const discount = Math.floor(originalPrice * 0.1); // 10% 할인 계산
    //     const discountedPrice = originalPrice - discount; // 할인된 가격

    //     const pointsApplied = parseCurrency(useCashPoints[1].value) || 0; // 두 번째 input에서 포인트 값 가져오기
    //     const cashApplied = parseCurrency(useCashPoints[0].value) || 0; // 첫 번째 input에서 캐시 값 가져오기

    //     // 최종 결제 금액 계산 (할인된 가격에서 포인트와 캐시를 차감)
    //     const finalPrice = discountedPrice - pointsApplied - cashApplied;

    //     // 적립 포인트 계산 (최종 결제 금액의 5%를 10단위로 내림)
    //     let earnedPoints = finalPrice * 0.05;
    //     earnedPoints = Math.floor(earnedPoints / 10) * 10; // 10단위로 내림 처리

    //     // 값을 DOM에 업데이트
    //     salePrice.textContent = formatCurrency(discount); // 할인가격
    //     orderTotalPrice.textContent = formatCurrency(finalPrice); // 최종 결제 금액
    //     pendingPoints.textContent = formatCurrency(earnedPoints); // 적립 포인트
    // }

    // // 페이지 로드 시 초기 계산 수행
    // updateTotalCash();

    //하트
    const heartIcons = document.querySelectorAll('.heart_icon');
    
    heartIcons.forEach(heartIcon => {
        const img = heartIcon.querySelector('img');
        if (img) {
            img.remove();
        }
        heartIcon.addEventListener('click', function() {
            this.classList.toggle('active');
        });
    });
   
    

    

    // //바로구매링크
    // const directBuyBtn = document.querySelectorAll('.direct_buy_btn');

    // directBuyBtn.forEach((item,index)=>{
    //     item.addEventListener('click',function(){
    //         window.location.href = 'product_buy.html';
    //     });
        
    // });    
});