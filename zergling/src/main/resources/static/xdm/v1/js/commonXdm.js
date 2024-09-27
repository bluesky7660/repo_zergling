
window.addEventListener('load', function() {
    //데이터삭제 js
    
    var form = document.querySelector("form");
    if(document.getElementById("ueleteBtn")||document.getElementById("deleteBtn")){
        var currentAction = form.action;
        var newAction = currentAction.slice(0, -4);
        function del() {
            form.action = newAction + 'Delt';	
            form.submit();
        }
        function uel() {
            form.action = newAction + 'Uelt';
            form.submit();
        }
        document.getElementById("deleteBtn").onclick = del;
        document.getElementById("ueleteBtn").onclick = uel;
    }
    

    //밸리데이션
    document.getElementById("btnSubmit").onclick = function (){
        const form = document.querySelector("form");
        const formUrl = form.action;
        var objs = document.querySelectorAll(".validate-this");
        var i= 0;
        var validateChk = false;
        for(let element of objs){
            var objValue = element.value.trim();
            if (objValue == "" || objValue == null) {
                const feedback = element.parentElement.querySelector(".invalid-feedback");
                var waring = feedback.textContent.trim();
                alert(waring);
                if(i==0){
                    element.focus();
                }
                i++;
                validateChk = false;
                element.classList.add('is-invalid');
            } else {
                //by pass
                validateChk = true;
                element.classList.remove('is-invalid');
                element.classList.add('is-valid');
            }
        };
        if(validateChk == false){
            alert("검사실패");
            return false;
        }
        alert("통과!");
        form.action = formUrl;
        form.submit();
    }

});