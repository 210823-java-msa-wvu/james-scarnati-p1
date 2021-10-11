           document.getElementById('req').addEventListener("click", checkInfo);

                      function checkInfo(){

                        var date = document.getElementById('cDate').value;
                        var cost = document.getElementById('cCost').value;
                        var grade = document.getElementById('pGrade').value;
                        var type = document.getElementById('course').value;
                        var hours = document.getElementById('hours').value;
                        var location = document.getElementById('location').value;
                        var reason = document.getElementById('reason').value;

                      let inf ={
                        courseStart: date,
                        courseCost: cost,
                        passingGrade: grade,
                        courseType: type,
                        hours: hours,
                        location: location,
                        reason: reason
                      }

                      console.log(inf);

                      var ajaxRequest = new XMLHttpRequest();
                           ajaxRequest.onreadystatechange = function(){

                           if(ajaxRequest.readyState == 4){
                                         //the request is completed, now check its status
                                         if(ajaxRequest.status == 204){
                                           let r = this.responseText;
                                           console.log(r);
                                           alert("Request Created!");
                                           }
                                           else{
                                           alert("Request Failed!");
                                           }


                  }
                  else{
                                  console.log("Status: " + ajaxRequest.status);

                                }


                  }
                  ajaxRequest.open('POST', 'http://localhost:8080/P1/requests');

                  ajaxRequest.setRequestHeader('Content-Type', 'application/json');

                  ajaxRequest.send(JSON.stringify(inf));




                      }