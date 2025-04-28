<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<% request.getAttribute("message"); %>

	<form action="./AddNewService" method="POST">
		<p> Titolo del servizio/Evento  <input type="text" name="serviceTitle" id="serviceTitle"> </p>
		<p> Descrizione Servizio </p> <textarea name="serviceDescription"></textarea> 
		<p> Aggiungi qualche opzione personalizzata </p> <textarea name="personalizedOption"> </textarea> 
		<p> Categoria 
		
			<!--  DA SOSTITUIRE CON LA SELECT DEL DB  -->
			<select name="serviceCategory" id="serviceCategory">
				<option value="internalEvent"> Evento interno </option>
				<option value="externalEvent"> Evento esterno </option>
			</select>	
		</p>
		
		<p> Prezzo (euro) <input type="number" min="1" name="serviceCost"> </p>
		<p> Data di inizio <input type="date" name="startDate"> </p>
		<p> Data di fine <input type="date" name="endDate"> </p>
		<input type="submit" name="submitBtn" id="submitBtn">
	</form>
</body>
</html>