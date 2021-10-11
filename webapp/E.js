document.getElementById('Req').addEventListener("click", getReqs);
document.getElementById('setG').addEventListener("click", setPass);



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

function setPass(){

      var id= document.getElementById('id').value;
      var grade= document.getElementById('grade').value;



    let inf ={
      id: id,
      grade: grade
    }
    console.log(inf);
    var ajaxRequest = new XMLHttpRequest();
         ajaxRequest.onreadystatechange = function(){

         if(ajaxRequest.readyState == 4){
                       //the request is completed, now check its status
                       if(ajaxRequest.status == 204){
                         let r = this.responseText;
                         console.log(r);
                           alert("Final Result Set!");

                         }
                         else{
                         alert("Request Failed!");
                         }


}
else{
                console.log("Status error: " + ajaxRequest.status);
              }


}
ajaxRequest.open('PUT', 'http://localhost:8080/P1/e');

ajaxRequest.setRequestHeader('Content-Type', 'application/json');

ajaxRequest.send(JSON.stringify(inf));




    }