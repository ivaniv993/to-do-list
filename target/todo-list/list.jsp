<%--
  Created by IntelliJ IDEA.
  User: xXx
  Date: 10/16/2016
  Time: 4:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>To-Do-List</title>
    <script src="js/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700">
    <link rel="stylesheet" type="text/css" href="css/styles/buttons.css">
    <link rel="stylesheet" type="text/css" href="css/styles/list.css">
</head>
<body>

    <a href="/" class="btn" >Back</a>
    <div id="parent">
        <div id="wide">
            <div class="container">
                <div class="to-do-list">

                    <h2>To do list</h2>

                    <hr>

                    <ul id="to-do-list"></ul>
                </div>
            </div>
        </div>

        <div id="narrow">
            <div class="container">
                <div class="to-do-list">

                    <h2>Finished list</h2>

                    <hr>

                    <ul id="finished-list"></ul>
                </div>
            </div>
        </div>
    </div>

    <script>
        $.getJSON('http://localhost:8888/list', function (data) {
            objectList = jQuery.parseJSON(JSON.stringify(data));
            console.log(objectList);
            todoList = document.getElementById('to-do-list');
            finishedList = document.getElementById('finished-list');
            objectList.forEach(
                    function(obj) {

                        var checkbox = document.createElement('input');
                        checkbox.type = "checkbox";
                        checkbox.id = obj.id;

                        var label = document.createElement('label');
                        label.for = obj.id;
                        label.appendChild(document.createTextNode(obj.name));

                        var label1 = document.createElement('label');
                        label1.for = obj.id;
                        label1.appendChild(document.createTextNode(obj.deadLine));
                        
                        var li = document.createElement("li");
                        li.appendChild(checkbox);
                        li.appendChild(label);
                        li.appendChild(label1);

                        dateObj = new Date(Date.parse(obj.deadLine));
                        currentDate = new Date();



                        if(obj.status=='FINISHED'){
                            finishedList.appendChild(li);
                            checkbox.checked = true;
                            checkbox.disabled = true;
                            return;
                        }  else{

                            if(obj.status=='OVERDUE'){
                                li.className += ' btn-overdue'
                            }

                            checkbox.addEventListener('change', statusChange.bind(this) )

                        }
                        todoList.appendChild(li);
                    });
        });

        function statusChange(obj){
            console.log(obj.target.id);
            $.post(
                    'http://localhost:8888/list',
                    {
                        id: ''+obj.target.id,
                    }
            )

            obj.target.disabled = true;

            finishedListUL = document.getElementById('finished-list');
            var items = document.getElementById('to-do-list').getElementsByTagName("li");

            for( i = 0; i < items.length; i ++){
                if( items[i].children[0].id === obj.target.id){
                    finishedListUL.appendChild(items[i]);
                    break;
                }
            }

        }

    </script>
</body>
</html>
