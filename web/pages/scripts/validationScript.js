function formValidation(formName) {
    var isFormValid = true;
    var form = document.getElementById(formName);
    for(var i = 0; i< form.elements.length-1; i++){
        var el = form.elements[i];
        if(el.value == null || el.value == ""){
            isFormValid = false;
            var textMassage = "The field can't be empty. Please, fill and retry.";
            var errorDiv = document.getElementById(el.name);
            if(errorDiv!=null && errorDiv!=undefined){
                errorDiv.innerHTML = textMassage;
            }
        }
    }
    return isFormValid;
}

function passwordValid(pass){
    var matches = pass.match(/(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/g);
    return (matches != null);
}
function loginValid(log){
    var matches = log.match(/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/g);
    return (matches != null);
}

function setTextToElement(elementId,text) {
    document.getElementById(elementId).innerHTML += "<div>" + text + "</div>";
}

function loginFormValid() {
    var validation = true;
    if(!passwordValid(document.getElementById("loginForm").password.value)){
        validation = false;
        var passwordErrorsMassage = "Password is too short (minimum is 6 characters), needs at least one uppercase letter and one number";
        setTextToElement("passwordErrors",passwordErrorsMassage);
    }

    if(!loginValid(document.getElementById("loginForm").login.value)){
        validation = false;
        var loginErrorsMassage = "Login is too short (minimum is 2 characters), first symbol should be a letter";
        setTextToElement("loginErrors",loginErrorsMassage);
    }
    return validation;
}

var checkErrorsLoginsRequest = function(request)
{
    var outData = JSON.parse(request.responseText);
    if(outData.loginErrorsMassage!=undefined) {
        setTextToElement("loginErrors",outData.loginErrorsMassage);
    }
    if(outData.passwordErrorsMassage!=undefined){
        setTextToElement("passwordErrors",outData.passwordErrorsMassage);
    }
}

var checkErrorsRequest = function(request)
{
    var outData = JSON.parse(request.responseText);
    setJson(request.responseText);
}