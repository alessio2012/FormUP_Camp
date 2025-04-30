

<%@ page 
		language="java" 
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"
		import = " java.util.Collections" 
		
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FormUP Camp</title>
	</head>
	
	<% 
	
	
	Collection<ServicesBean> services = (Collection<?>) request.getAttribute("services");
	if(services == null) {
		response.sendRedirect("./ServiceControl");	
		return;
	}

	
	%>
	
	<%  if( request.getAttribute("status") != null) {
		
			if( request.getAttribute("status").equals("successfull")  ) {%>
				<script> alert("Inserito con successo"); </script>

	<%		}
		}
		
	%>
	
	
	<body>
	<h1> FORMUP CAMP</h1>
	
	<h3> Lista dei servizi disponibili</h3>
	
	<table border="1"> 
		<tr> 
			<th> # </th>
			<th> Titolo </th>
			<th> Status Servizio</th>
			<th> Data Inizio </th>
			<th> Data Fine </th>
			
		</tr>
		
		<tr> 
			<td> -1 </td> 
			<td> Estate Ragazzi </td>
			<td> Disponibile </td>
			<td> 30-04-25 </td>
			<td> 30-04-25 </td>
			
		</tr>
	</table>
	
	<form action="./ServiceControl" method="post">
		<fieldset>
		    <legend>Informazioni Servizio</legend>
		
		    <p>
		      <label for="titolo">Titolo:<br>
		      <input type="text" id="titolo" name="titolo" required>
		      </label>
		    </p>
		
		    <p>
		      <label for="descrizione">Descrizione:<br>
		      <textarea id="descrizione" name="descrizione" rows="4" cols="50"></textarea>
		      </label>
		    </p>
		
		    <p>
		      <label for="costo">Costo (euro/pz):<br>
		      <input type="number" id="costo" name="costo" step="0.01" required>
		      </label>
		    </p>
		
		    <p>
		      <label for="disponibilita">
		        <input type="checkbox" id="disponibilita" name="disponibilita" value="true" disabled>
		        Disponibile
		      </label>
		    </p>
		
		    <p>
		      <label for="dataInizio">Data Inizio:<br>
		      <input type="date" id="dataInizio" name="dataInizio" required>
		      </label>
		    </p>
		
		    <p>
		      <label for="dataFine">Data Fine:<br>
		      <input type="date" id="dataFine" name="dataFine" required>
		      </label>
		    </p>
		
		    <p>
		      <label for="idCategoria">Categoria:<br>
		      <input type="text" id="idCategoria" name="idCategoria" maxlength="30">
		      </label>
		    </p>
		
		    <p>
		      <input type="submit" value="Salva">
		    </p>
		</fieldset>
	
	</form>
		
	
	</body>
</html>