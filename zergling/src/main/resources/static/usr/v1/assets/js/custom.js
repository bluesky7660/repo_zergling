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
    const form = document.querySelector("form:not(#total_search_form)");
    var formUrl;
    if(form){
        console.log(form);
        formUrl = form.action;
    }
    
    

    //정규식
    var idRegExp = /^[a-zA-Z]{1}[a-zA-Z0-9]{4,14}$/;
    var passwordRegExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    var krAlphaNumRegExp = /^[ㄱ-ㅎ가-힣A-Za-z0-9]+$/;
    var krNameRegExp = /^[가-힣]{2,4}$/;
    var alphaNumRegExp = /^[a-zA-Z0-9]+$/;
    var nonkrRegExp=/^[a-zA-Z0-9!@#$%^&*()_+={}\[\]:;"'<>,.?/~`-]*$/;

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
                    ,data : { 
                        "userId" : $("#userId").val().trim(),
                         "userPassword" : $("#userPassword").val(),
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
            }else if (element.classList.contains('valid-nonkr')) {
                console.log("한글만뺀");
                
                if(!nonkrRegExp.test(objValue)){
                    var text = "영대소문자, 숫자, 특수문자 입력해주세요";
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

    //회원가입
    const signupBtn = document.getElementById("signupBtn");
    if (signupBtn) {
        signupBtn.onclick = function (){
            
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
            // form.action = formUrl;
            // form.submit();

            //ajax 
            $.ajax({
                async: true 
                ,cache: false
                ,type: "post"
                /* ,dataType:"json" */
                ,url: formUrl
                /* ,data : $("#formLogin").serialize() */
                ,data : { 
                    "userId" : $("#userId").val().trim(),
                    "name" : $("#userName").val().trim(),
                    "userPassword" : $("#signup_password").val(),
                    "email" : $("#userEmail").val().trim(),
                    "phoneNum" : $("#userTel").val().trim(),
                    "gender" : $("#user_gender").val().trim(),
                    "dateOfBirth" : $("#signup_dateOfBirth").val().trim(),
                    captchaCode: $("#captchaCode").val(),
                }//, "autoLogin" : $("#autoLogin").is(":checked")}
                ,success: function(response) {
                    if(response.rt == "success") {
                        // $("#userId").classList.remove('is-invalid');
                        // $("#userName").classList.remove('is-invalid');
                        // $("#signup_password").classList.remove('is-invalid');
                        // $("#signup_password_check").classList.remove('is-invalid');
                        // $("#userEmail").classList.remove('is-invalid');
                        // $("#userTel").classList.remove('is-invalid');
                        // $("#user_gender").classList.remove('is-invalid');
                        // $("#signup_dateOfBirth").classList.remove('is-invalid');
                        // feedbackChk.classList.remove('is-invalid');

                        window.location.href = "/login";
                        // location.href = "index";
                        
                    } else {

                        // const feedbackHtml = `
                        //     <div class="invalid-box mb-3">
                        //         <div class="invalid-child"></div>
                        //         <div id="invalid-feedback" class="invalid-feedback"></div>
                        //     </div>`
                        // ;
                        // console.log("테스트");
                        // $(".captcha_area").append(feedbackHtml);
                        console.log("테스트2");
                        $("#captchaCode").addClass('is-invalid');
                        $(".captcha_area .invalid-feedback").css("display", "block").text("캡차 검증에 실패했습니다.");

                    }
                }
                ,error : function(jqXHR, textStatus, errorThrown){
                    alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
                }
            });
            
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
    toggleButtons.forEach((button) => {
        button.classList.add('hidden');
    });

    toggleButtons.forEach((button, index) => {
        button.addEventListener('click', function() {
            // const content = contents[index];
            const contentTop =button.parentElement;
            const content = button.parentElement.querySelector('.info_text');
            const contentTopPosition = contentTop.getBoundingClientRect().top + window.scrollY;  // contentTop의 위치(스크롤 오프셋 포함)

            // contentTop을 화면 상단으로 스크롤 후 100px 위로 조정
            window.scrollTo({
                top: contentTopPosition - 120,  // 100px 위로
                behavior: 'smooth'  // 부드러운 스크롤
            });
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
        const button = content.parentElement.querySelector('.collapse_btn');
        if(content.classList.contains("info_desc")){
            if (content.scrollHeight > lineHeight * 4) { // 4줄 이상일 경우
                // toggleButtons[index].classList.remove('hidden'); // 버튼 보이기
                console.log("4줄이상");
            } else {
                console.log("4줄미만");
                content.parentElement.parentElement.querySelector('.collapse_btn').remove(); // 버튼 제거
            }
        }else{
            if (content.scrollHeight > lineHeight * 4) { // 4줄 이상일 경우
                // toggleButtons[index].classList.remove('hidden'); // 버튼 보이기
                console.log("4줄이상");
            } else {
                console.log("4줄미만");
                button.remove(); // 버튼 제거
            }
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

    
    //통합검색
    const search = document.querySelector("#total_search_form")
    const totalshBtn = document.querySelector("#total_search_btn");
    if(totalshBtn){
        totalshBtn.addEventListener('click', function () {
            search.action = "product_list";
            search.submit();
        })
    }
    
    const bookRvBox = document.getElementById("book_review_area");
    if(bookRvBox){
        $('#loadingSpinner').show();
        searchVideos();
    }

    // //바로구매링크
    // const directBuyBtn = document.querySelectorAll('.direct_buy_btn');

    // directBuyBtn.forEach((item,index)=>{
    //     item.addEventListener('click',function(){
    //         window.location.href = 'product_buy.html';
    //     });
        
    // });    
});