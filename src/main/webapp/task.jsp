<%--
  Created by IntelliJ IDEA.
  User: xXx
  Date: 10/15/2016
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<head>
  <title>To-Do-List</title>
  <link rel="stylesheet" type="text/css" href="css/styles/buttons.css">
  <link rel="stylesheet" type="text/css" href="css/styles/list.css">
  <script src="js/jquery-3.1.1.min.js"></script>
 </head>
<body>
<form action="index" method="post">
  <div class="container">
    <div class="to-do-list">
      <span style="visibility: hidden; background-color: #3cb0fd; border-radius: 10px;" id="warning" > Fill all field </span>
      <h2>Add new task</h2>

      <hr>

      <ul id="to-do-list">
        <li>
          <label for="name">Name</label>
          <input type="text" id="name" name="name" >
        </li>
        <li>
          <label for="time">Dead line</label>

          <input type="time" name="date" id="time" >
        </li>
        <li>
          <label for="priority">Priority</label>
          <input type="text" id="priority" name="priority" >
        </li>

      </ul>
      <input type="submit" value="Add" class="add-btn" id="submit" disabled>
      <script>
        document.getElementById('name').addEventListener('change', checkFields.bind(this));
        document.getElementById('time').addEventListener('change', checkFields.bind(this));
        document.getElementById('priority').addEventListener('change', checkFields.bind(this));

        function checkFields(){
          name = document.getElementById('name').value;
          time = document.getElementById('time').value;
          priority = document.getElementById('priority').value;

          warning = document.getElementById('warning');

          var isWarning = false;
          if(name.length === 0){
            warning.textContent = 'Enter name';
            isWarning = true;
          }

          if(time.length < 5){
            warning.textContent = 'Enter dead line';
            isWarning = true;
          }

          if(priority.length === 0){
            warning.textContent = 'Enter priority';
            isWarning = true;
          }

          if(isWarning){
            document.getElementById('warning').style.visibility='visible';
          } else{
            document.getElementById('warning').style.visibility='hidden';
            submit.removeAttribute('disabled')
          }
        }

      </script>
    </div>

  </div>
</form>
  <%--$.post(--%>
  <%--'http://localhost:8888/index',--%>
  <%--{--%>
  <%--name: ''+name,--%>
  <%--date: ''+time,--%>
  <%--priority: ''+priority--%>
  <%--},--%>
  <%--function( data ) {--%>
  <%--console.log(data);--%>
  <%--}--%>
  <%--)--%>


</body>
</html>
