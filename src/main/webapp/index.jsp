

<%@ page import="formup.services.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FormUP Camp</title>
	</head>
	
	<% 
	
	Collection<?> services = (Collection<?>) request.getAttribute("collection");
	
	ServicesBean service = (ServicesBean) request.getAttribute("product");
	
	%>
	
	<%  if( request.getAttribute("status") != null) {
		
			if( request.getAttribute("status").equals("successfull")  ) {%>
				<script> alert("Operazione avvenuta con successo"); </script>

	<%		}
		}
		
	%>
	
	
	<body>
	
	<a href="./ServiceControl?operation=selectAll"> Aggiorna</a>
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
		
				<%
			if (services != null && services.size() != 0) {
				Iterator<?> it = services.iterator();
				while (it.hasNext()) {
					ServicesBean bean = (ServicesBean) it.next();
		%>
		<tr>
			<td><%=bean.getIdServizio()%></td>
			<td><%=bean.getTitolo()%></td>
			<td><%=bean.isDisponibilita()%></td>
			<td><%=bean.getDataInizio()%></td>
			<td><%=bean.getDatafine()%></td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Non ho trovato servizi disponibili</td>
		</tr>
		<%
			}
		%>
		
	</table>
	
	<form action="./ServiceControl?operation=insert" method="post">
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
		        <input type="checkbox" id="disponibilita" name="disponibilita">
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