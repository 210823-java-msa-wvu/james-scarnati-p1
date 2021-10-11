(function () {
    var cookies = document.cookie.split("; ");
    for (var c = 0; c < cookies.length; c++) {
        var d = window.location.hostname.split(".");
        while (d.length > 0) {
            var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=';
            var p = location.pathname.split('/');
            document.cookie = cookieBase + '/';
            while (p.length > 0) {
                document.cookie = cookieBase + p.join('/');
                p.pop();
            };
            d.shift();
        }
    }
})();



 document.getElementById('login').addEventListener("click", login);

           function login(){

             var username = document.getElementById('username').value;
             var password = document.getElementById('password').value

           let inf ={
             username: username,
             password: password
           }

           console.log(inf);

           var ajaxRequest = new XMLHttpRequest();
                ajaxRequest.onreadystatechange = function(){

                if(ajaxRequest.readyState == 4){
                              //the request is completed, now check its status
                              if(ajaxRequest.status == 200){
                                let r = this.responseText;
                                console.log(r);
                                window.location.href = "http://localhost:8080/P1/welcomepage.html"
                                }

                                else{
                                alert("Login Failed!\nTry again!");
                                }


       }
       else{
                       console.log("Status error: " + ajaxRequest.status);
                     }


       }
       ajaxRequest.open('POST', 'http://localhost:8080/P1/login');

       ajaxRequest.setRequestHeader('Content-Type', 'application/json');

       ajaxRequest.send(JSON.stringify(inf));
       }