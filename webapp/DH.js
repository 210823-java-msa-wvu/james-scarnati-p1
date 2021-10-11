document.getElementById('Req').addEventListener("click", getReqs);


function CheckDeny(val){
 var element=document.getElementById('deny');
 if(val=='true')
   element.style.display='block';
 else
   element.style.display='none';
}

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
                        "<th>Event Location</th><th>Work-Related Reason</th>"+
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
                           var location;
                           var reason;
                           for (var i = 0; i < users.length; i++) {


                              id = users[i]['id'];
                              employeeId = users[i]['employeeId'];
                              requestDate = users[i]['requestDate'];
                              CourseStart = users[i]['courseStart'];
                              passingGrade = users[i]['passingGrade'];
                              courseCost = users[i]['courseCost'];
                              hours = users[i]['hours']
                              location = users[i]['location']
                              reason = users[i]['reason']
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
                              "</td><td>"+location+
                              "</td><td>"+reason+
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
      ajaxRequest.open('GET', 'http://localhost:8080/P1/req');
      ajaxRequest.send();
    }
    document.getElementById('App').addEventListener("click", getApp);
    function getApp() {


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
                            "<th>Supervisor Approval/th><th>Denial Reason</th><th>Dept. Head Approval</th>"+
                            "<th>BenCo Approval</th><th>Info. Req. (Employee)</th><th>Info. Req. (Supervisor)</th>"
                            +"<th>Info. Req. (Dept. Head)</th><th>Employee Info</th><th>Supervisor Info</th><th>Dept. Head Info</th><th>Denied</th></tr>";
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

                               }
                             var finalTable =  table+tr+tableEnd;
                document.getElementById("apps").innerHTML = finalTable;
                document.getElementById("update").hidden = false;
                document.getElementById("Info").style.display = 'block';
              }
              }
              }
              else{
                console.log("Status error: " + ajaxRequest.status);
              }
            }



          }
          ajaxRequest.open('POST', 'http://localhost:8080/P1/req');
          ajaxRequest.send();

        }

    document.getElementById('updateReq').addEventListener("click", updateReq);

    function updateReq(){

      var reqId= document.getElementById('reqId').value;
      var employeeInfoReq= document.getElementById('employee').value;
      var dsInfoReq= document.getElementById('ds').value;
      var approve= document.getElementById('approve').value;
      var deny= document.getElementById('deny').value;
      var reason = document.getElementById('dreason').value;

    let changeReq ={
    id : reqId,
    dhApproval : approve,
    infoRequestDs : dsInfoReq,
    infoRequestE : employeeInfoReq,
    dsDenialReason : reason,
    denied : deny

    }
    console.log(changeReq);
    console.log(JSON.stringify(changeReq))
    var ajaxRequest = new XMLHttpRequest();
         ajaxRequest.onreadystatechange = function(){

         if(ajaxRequest.readyState == 4){
                       //the request is completed, now check its status
                       if(ajaxRequest.status == 204){
                         let r = this.responseText;
                         console.log(r);
                         alert("Request Updated!");
                         }
                         else{
                         alert("Request Failed!");
                         }


}
else{
                console.log("Status error: " + ajaxRequest.status);
              }


}
ajaxRequest.open('PUT', 'http://localhost:8080/P1/dh');

ajaxRequest.setRequestHeader('Content-Type', 'application/json');

ajaxRequest.send(JSON.stringify(changeReq));




    }
     document.getElementById('reqInfo').addEventListener("click", reqInfo);

           function reqInfo(){

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
                                alert("Info Set!");
                                }
                                else{
                                alert("Request Failed!");
                                }


       }
       else{
                       console.log("Status error: " + ajaxRequest.status);
                     }


       }
       ajaxRequest.open('POST', 'http://localhost:8080/P1/dh');

       ajaxRequest.setRequestHeader('Content-Type', 'application/json');

       ajaxRequest.send(JSON.stringify(inf));




           }