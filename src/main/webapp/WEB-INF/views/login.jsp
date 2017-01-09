<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


    <form class="row form-margin" name="generalForm"role="form" class="form-horizontal" >
     <div class="form-group">
      <label for="inputFirstName" class="control-label">Utilizator</label>
      <input type="text" class="form-control" id="inputFirstName" name="username" required placeholder="E-mail">
     </div>
     <div class="form-group">
      <label for="inputPassword" class="control-label">Parola</label>
       <input type="password" class="form-control" id="inputPassword" required placeholder="Password">
      </div>
     <div class="form-group">
      <button type="submit" class="btn btn-success btn-block">Submit</button>
     </div>
    </form>
    
</body>
</html>