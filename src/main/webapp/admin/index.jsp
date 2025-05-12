

<%@ page import="formup.admin.services.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../conf/includes/header.jsp" %>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FormUP Camp</title>
		
		<!--  Verifica degli input  -->
		
		<script>
			document.addEventListener("DOMContentLoaded", function() {
			  const form = document.querySelector("form");
			
			  form.addEventListener("submit", function(event) {
			    // Recupera i valori dei campi
			    const titolo = document.getElementById("titolo").value.trim();
			    const descrizione = document.getElementById("descrizione").value.trim();
			    const costo = document.getElementById("costo").value.trim();
			    const dataInizio = document.getElementById("dataInizio").value;
			    const dataFine = document.getElementById("dataFine").value;
			
			    let messaggiErrore = [];
			
			    // Controlla se i campi obbligatori sono vuoti
			    if (!titolo) messaggiErrore.push("Il campo 'Titolo' è obbligatorio.");
			    if (!descrizione) messaggiErrore.push("Il campo 'Descrizione' è obbligatorio.");
			    if (!costo) messaggiErrore.push("Il campo 'Costo' è obbligatorio.");
			    if (!dataInizio) messaggiErrore.push("Il campo 'Data Inizio' è obbligatorio.");
			    if (!dataFine) messaggiErrore.push("Il campo 'Data Fine' è obbligatorio.");
			
			    // Se ci sono errori, impedisce l'invio del form e mostra gli errori
			    if (messaggiErrore.length > 0) {
			      event.preventDefault(); // Blocca l'invio del form
			      alert(messaggiErrore.join("\n")); // Mostra gli errori
			    }
			  });
			});
		</script>

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
		      <input type="text" id="titolo" name="titolo" >
		      </label>
		    </p>
		
		    <p>
		      <label for="descrizione">Descrizione:<br>
		      <textarea id="descrizione" name="descrizione" rows="4" cols="50"></textarea>
		      </label>
		    </p>
		
		    <p>
		      <label for="costo">Costo (euro/pz):<br>
		      <input type="number" id="costo" name="costo" step="0.01" >
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
		      <input type="date" id="dataInizio" name="dataInizio" >
		      </label>
		    </p>
		
		    <p>
		      <label for="dataFine">Data Fine:<br>
		      <input type="date" id="dataFine" name="dataFine" >
		      </label>
		    </p>
		
		    <p>
		      <label for="idCategoria">Categoria: (da configurare)<br>
		      <input type="text" id="idCategoria" name="idCategoria" maxlength="30">
		      </label>
		    </p>
		
		    <p>
		      <input type="submit" value="Aggiugi">
		    </p>
		</fieldset>
	
	</form>
	
	
	
	<form action="./UserControl?operation=insert" method="post">
		<fieldset>
		    <legend>Informazioni Utente</legend>
		
		    <p>
		      <label for="titolo">Nome utente:<br>
		      <input type="text" id="username" name="username" >
		      </label>
		    </p>
		
		    <p>
		      <label for="email">Email:<br>
		      <input type="text" id="email" name="email" rows="4" cols="50"></textarea>
		      </label>
		    </p>
		
		    <p>
		      <label for="nome">Nome:<br>
		      <input type="text" id="nome" name="nome"  >
		      </label>
		    </p>
		    
		    <p>
		      <label for="cognome">Cognome:<br>
		      <input type="text" id="cognome" name="cognome"  >
		      </label>
		    </p>
		
		    <p>
		      <label for="password">
		        Password <input type="password" id="password" name="password">
		      </label>
		    </p>

		    <p>
		      <input type="submit" value="Aggiugi">
		    </p>
		</fieldset>
	
	</form>
		
	
	</body>
</html>