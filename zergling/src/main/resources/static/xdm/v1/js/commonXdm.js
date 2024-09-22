//데이터삭제 js
document.getElementById("deleteBtn").onclick = del;
document.getElementById("ueleteBtn").onclick = uel;
var form = document.querySelector("form[name=form]");
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

//