
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    let today = [month, day, year].join('-');
    return today;
}


function jsonDeCookifier(name){

    let cookies = document.cookie;

    if(cookies.length != 0){

        let cookieArray = cookies.split("=");
           console.log(cookieArray);
        for(var i = 0; i < cookieArray.length - 1; i += 2){

        let cookieName = cookieArray[i];

            if(cookieName == name){

                let uglyCookie = cookieArray[i + 1];
                let lessUglyCookie = uglyCookie.replace(/\\/g,'');
                let prettyCookie = lessUglyCookie.slice(1, lessUglyCookie.length - 1);

                let yoJson = JSON.parse(prettyCookie);

                return yoJson;
            }
        }
    }else return null;
 }
let visitorInfo = jsonDeCookifier("user");
//console.log(visitorInfo);
var title = visitorInfo["title"];
window.onload = function(){
    let fName = visitorInfo["fName"];
    let title = visitorInfo["title"];
    if(title == "Benefits Coordinator"){
     document.getElementById("BenCo").style.display = 'block';
      }
    else if(title == "Direct Supervisor"){
     document.getElementById("DS").style.display = 'block';
      }
    else if(title == "Department Head"){
     document.getElementById("DH").style.display = 'block';
      }
      else if(title == "Employee"){
           document.getElementById("E").style.display = 'block';
            }
      else if(title == "Direct Manager"){
            document.getElementById("DM").style.display = 'block';
             }
    let date = new Date;
    document.getElementById("Name").innerHTML = "Welcome " + fName  + "!";
    document.getElementById("date").innerHTML = "<h3>Today's date is " + formatDate(date)  + "</h3>";

}

document.getElementById('myReq').addEventListener("click", getReqs);


function getReqs() {


 var ajaxRequest = new XMLHttpRequest();
      ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4){
          //the request is completed, now check its status
          if(ajaxRequest.status == 200){
            ajaxRequest.onload = function () {
                       var users = JSON.parse(ajaxRequest.responseText);
                        console.log(users);
                        {
                        var table ="<table border='1'><tr><th>Request Id</th>" +
                        "<th>Employee Id</th><th>Date of Request</th><th>Course Start Date</th>"+
                        "<th>Passing Grade</th><th>Cost of Course</th><th>Work Hours Needed</th>"+
                        "<th>Amount to Reimburse</th><th>Type of Course</th><th>Grade</th>"
                        +"<th>Approved</th><th>Denied</th><th>Pass or Fail</th><th>Supervisor Check</th>"+
                        "<th>File</th><th>Urgent</th></tr>";
                       var tr = "";
                       var tableEnd = "</table>";

                           var id;
                           var employeeId;
                           var requestDate;
                           var CourseStart;
                           var passingGrade;
                           var courseCost;
                           var reimbursementAmount;
                           var courseType;
                           var approval;
                           var pass;
                           var denial;
                           var dsCheck;
                           var grade;
                           var file;
                           var urgent;
                           var hours;
                           for (var i = 0; i < users.length; i++) {


                              id = users[i]['id'];
                              employeeId = users[i]['employeeId'];
                              requestDate = users[i]['requestDate'];
                              CourseStart = users[i]['courseStart'];
                              passingGrade = users[i]['passingGrade'];
                              courseCost = users[i]['courseCost'];
                              hours = users[i]['hours']
                              reimbursementAmount = users[i]['reimbursementAmount'];
                              courseType = users[i]['courseType'];
                              grade = users[i]['grade']
                              pass = users[i]['pass']
                              approval = users[i]['approval'];
                              denial = users[i]['denial'];
                              dsCheck = users[i]['dsCheck'];
                              file = users[i]['file'];
                              urgent = users[i]['urgent']

                              tr += "<tr><td>"+id+
                              "</td><td>"+employeeId+
                              "</td><td>"+requestDate+
                              "</td><td>"+CourseStart+
                              "</td><td>"+passingGrade+
                              "</td><td>"+courseCost+
                              "</td><td>"+hours+
                              "</td><td>"+reimbursementAmount+
                              "</td><td>"+courseType+
                              "</td><td>"+grade+
                              "</td><td>"+approval+
                              "</td><td>"+denial+
                              "</td><td>"+pass+
                              "</td><td>"+dsCheck+
                              "</td><td>"+file+
                              "</td><td>"+urgent+
                              "</td></tr>";

                           }
                         var finalTable =  table+tr+tableEnd;
            document.getElementById("table").innerHTML = finalTable;
          }
          }
          }
          else{
            console.log("Status error: " + ajaxRequest.status);
          }
        }



      }
      ajaxRequest.open('GET', 'http://localhost:8080/P1/welcomepage');
      ajaxRequest.send();
    }

document.getElementById('myApp').addEventListener("click", getApp);
function getApp() {


 var ajaxRequest = new XMLHttpRequest();
      ajaxRequest.onreadystatechange = function(){

        if(ajaxRequest.readyState == 4){
          //the request is completed, now check its status
          if(ajaxRequest.status == 200){
            ajaxRequest.onload = function () {
                       var users = JSON.parse(ajaxRequest.responseText);
                        console.log(users);

                                                    var table ="<table border='1'><tr><th>Request Id</th>" +
                                                    "<th>Supervisor Approval/th><th>Denial Reason</th><th>Dept. Head Approval</th>"+
                                                    "<th>BenCo Approval</th><th>Info. Req. (Employee)</th><th>Info. Req. (Supervisor)</th>"
                                                    +"<th>Info. Req. (Dept. Head)</th><th>Employee Info</th><th>Supervisor Info</th><th>Dept. Head Info</th>"+
                                                    "<th>Denied</th></tr>";
                                                   var tr = "";
                                                   var tableEnd = "</table>";

                                                       var id;
                                                       var dsApproval;
                                                       var DenialReason;
                                                       var dhApproval;
                                                       var bcApproval;
                                                       var infoRequestE;
                                                       var infoRequestDs;
                                                       var infoRequestDh;
                                                       var employeeInfo;
                                                       var dsInfo;
                                                       var dhInfo;
                                                       var denied;

                                                       for (var i = 0; i < users.length; i++) {


                                                          id = users[i]['id'];
                                                          dsApproval = users[i]['dsApproval'];
                                                          DenialReason = users[i]['DenialReason'];
                                                          dhApproval = users[i]['dhApproval'];
                                                          bcApproval = users[i]['bcApproval'];
                                                          infoRequestE = users[i]['infoRequestE'];
                                                          infoRequestDs = users[i]['infoRequestDs']
                                                          infoRequestDh = users[i]['infoRequestDh'];
                                                          employeeInfo = users[i]['employeeInfo'];
                                                          dsInfo = users[i]['dsInfo'];
                                                          dhInfo = users[i]['dhInfo'];
                                                          denied = users[i]['denied'];


                                                          tr += "<tr><td>"+id+
                                                          "</td><td>"+dsApproval+
                                                          "</td><td>"+DenialReason+
                                                          "</td><td>"+dhApproval+
                                                          "</td><td>"+bcApproval+
                                                          "</td><td>"+infoRequestE+
                                                          "</td><td>"+infoRequestDs+
                                                          "</td><td>"+infoRequestDh+
                                                          "</td><td>"+employeeInfo+
                                                          "</td><td>"+dsInfo+
                                                          "</td><td>"+dhInfo+
                                                          "</td><td>"+denied+
                                                          "</td></tr>";



                                    document.getElementById("Info").style.display = 'block';
                                    document.getElementById("Info").style.display = 'block';


                           }
                         var finalTable =  table+tr+tableEnd;
            document.getElementById("app").innerHTML = finalTable;

          }
          }
          else{
            console.log("Status error: " + ajaxRequest.status);
          }
        }



      }
      ajaxRequest.open('POST', 'http://localhost:8080/P1/welcomepage');
      ajaxRequest.send();
    }

   document.getElementById('reqInfo').addEventListener("click", reqInfo);

       function reqInfo(){
         var title = visitorInfo["title"];
         var rid = document.getElementById('rid').value;
         var info = document.getElementById('rInfo').value

       let inf ={
         id: rid,
         info: info
       }

       console.log(inf);

       var ajaxRequest = new XMLHttpRequest();
            ajaxRequest.onreadystatechange = function(){

            if(ajaxRequest.readyState == 4){
                          //the request is completed, now check its status
                          if(ajaxRequest.status == 204){
                            let r = this.responseText;
                            console.log(r);
                            alert("Info Updated!");
                            }
                            else{
                            alert("Request Failed!");
                            }


   }
   else{
                   console.log("Status error: " + ajaxRequest.status);
                 }


   }
   ajaxRequest.open('PUT', 'http://localhost:8080/P1/welcomepage');

   ajaxRequest.setRequestHeader('Content-Type', 'application/json');

   ajaxRequest.send(JSON.stringify(inf));




       }

       document.getElementById('reqFile').addEventListener("click", reqFile);

              function reqFile(){

                var rid = document.getElementById('reqId').value;
                var info = document.getElementById('file').value

              let inf ={
                id: rid,
                info: info
              }

              console.log(inf);

              var ajaxRequest = new XMLHttpRequest();
                   ajaxRequest.onreadystatechange = function(){

                   if(ajaxRequest.readyState == 4){
                                 //the request is completed, now check its status
                                 if(ajaxRequest.status == 204){
                                   let r = this.responseText;
                                   console.log(r);
                                   alert("File added!");
                                   }
                                   else{
                                   alert("Request Failed!");
                                   }


          }
          else{
                          console.log("Status error: " + ajaxRequest.status);
                        }


          }
          ajaxRequest.open('PUT', 'http://localhost:8080/P1/welcomepage2');

          ajaxRequest.setRequestHeader('Content-Type', 'application/json');

          ajaxRequest.send(JSON.stringify(inf));




              }

document.getElementById('myFunds').addEventListener("click", getFunds);


function getFunds() {


        var ajaxRequest = new XMLHttpRequest();
             ajaxRequest.onreadystatechange = function(){

               if(ajaxRequest.readyState == 4){
                 //the request is completed, now check its status
                 if(ajaxRequest.status == 200){
                   ajaxRequest.onload = function () {
                              var user = JSON.parse(ajaxRequest.responseText);
                               console.log(user);
                               {

                                    var starting = user["startingAmount"];
                                    var pending = user["pendingAmount"];
                                    var used = user["approvedAmount"];
                                    var final = starting - (pending + used)

                                    var string = "Starting Balance: $"+starting +
                                    ". Pending Balance: $" + pending +
                                    ". Approved Funds: $"+ used +
                                    ". Remaining Balance: $"+ final + ".";
                                    }
                   document.getElementById("funds").innerHTML = string;
                   }
                   }
                   }
               else{
                     console.log("Status error: " + ajaxRequest.status);

               }
               }
                     ajaxRequest.open('GET', 'http://localhost:8080/P1/welcomepage2');
                     ajaxRequest.send();
               }


