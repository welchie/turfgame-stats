<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Strava Stats</title>
</head>
<body>
	<h1>WeeWelchie.org Strava Stats</h1>
	<form name="statsForm" action="stats/v1" method="POST">
		<table>
			<tr>
				<td>Client ID</td>
				<td><input type="text" name="clientId" style="width: 100px" /></td>
			</tr>
			<tr>
				<td>Secret Key</td>
				<td><input type="text" name="clientSecret" style="width: 400px" /></td>
			</tr>
		</table>
		<button type="submit">Get My Stats</button>
	</form>

</body>
</html>
