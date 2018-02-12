var selectedItemId = -1;


function sendRequest(methodName, url, args, handler,contentType)
{
    var request = createRequest();
    if (!request)
    {
        return;
    }
    //Set handler
    request.onreadystatechange = function()
    {
        //If the data exchange is complete
        if (request.readyState == 4)
        {
            //Set request to handler
            handler(request);
        }
    }

    //Check is it GET-request
    if (methodName.toLowerCase() == "get" && args.length > 0)
        url += "?" + args;
    //Open connection
    request.open(methodName, url, true);

    //Check is it GET-request
    if (methodName.toLowerCase() == "post")
    {
        //Set up request header
        request.setRequestHeader("Content-Type",contentType);

        //Send request with parameters
        request.send(args);
    }
    else
    {
        //is it GET-request
        //Send request with no parameters (null)
        request.send(null);
    }
}




function createRequest()
{
    var request = false;
    if (window.XMLHttpRequest)
    {
        //Safari, Konqueror
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        //Internet explorer
        try
        {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (CatchException)
        {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    return request;
}


function formToJson(formName) {
    var form = document.getElementById(formName);
    var jsonData="{";
    var i = 0;
    for(i =0; i< form.elements.length-2; i++){
        jsonData+='"'+form.elements[i].name+'"'+':'+'"'+form.elements[i].value+'",';
    }
    i=form.elements.length-2;
    if(i >= 0)
        jsonData+='"'+form.elements[i].name+'"'+':'+'"'+form.elements[i].value+'"';
    jsonData+='}';
    return jsonData;
}

function jsonToForm(formName,jsonData) {
    var form = document.getElementById(formName);
    JSON.parse(jsonData, function(key, value) {
        var element = form[key];
        if(element!=null){
            element.value = value;
        }
        return value;
    });
}
function setJson(jsonData) {
    JSON.parse(jsonData, function(key, value) {
        var element = document.getElementById(key);
        if(element != null && element != undefined){
            element.innerHTML = value;
        }
        return value;
    });
}

// Read a page's GET URL variables and return them as an associative array.
function getUrlParameters()
{
    var parameters = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        parameters.push(hash[0]);
        parameters[hash[0]] = hash[1];
    }
    return parameters;
}