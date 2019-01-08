<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${message}
<form action="registerTeam" method="post">
Team Name : <input type="text" name="teamName"/></br>
Total Matches : <input type="text" name="totalMatches"/></br>
Team Won no.of matches : <input type="text" name="wins"/></br>
Team Lost no.of matches : <input type="text" name="loses"/></br>
Team ties no.of matches : <input type="text" name="tie"/></br>
<input type="submit" value="Register"/>
</form>

<form action="showScore">
	<input type="submit" value="Show Score"/>
</form>
</body>
</html>