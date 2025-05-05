package admin.services;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ServicesBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6203464606915450267L;

	public void ServiceBean() { }
	
	private int idServizio;
	private Date dataInizio, datafine;
	private boolean disponibilita = false;
	private String titolo;
	private String descrizione;
	private double costo;
	
	public int getIdServizio() { return idServizio; }
	public void setIdServizio(int idServizio) { this.idServizio = idServizio; }
	
	public Date getDataInizio() { return dataInizio; }
	public void setDataInizio(Date dataInizio) { this.dataInizio = dataInizio; }
	
	public Date getDatafine() { return datafine; }
	public void setDatafine(Date datafine) { this.datafine = datafine; }
	
	public boolean isDisponibilita() { return disponibilita; }
	public void setDisponibilita(boolean disponibilita) { this.disponibilita = disponibilita; }
	
	public String getTitolo() { return titolo; }
	public void setTitolo(String titolo) { this.titolo = titolo; }
	
	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
	
	public double getCosto() { return costo; }
	public void setCosto(double costo) { this.costo = costo; }
	
	@Override
	public String toString() {
		return "ServicesBean [idServizio=" + idServizio + ", dataInizio=" + dataInizio + ", datafine=" + datafine
				+ ", disponibilita=" + disponibilita + ", titolo=" + titolo + ", descrizione=" + descrizione + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dataInizio, datafine, descrizione, disponibilita, idServizio, titolo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicesBean other = (ServicesBean) obj;
		return Objects.equals(dataInizio, other.dataInizio) && Objects.equals(datafine, other.datafine)
				&& Objects.equals(descrizione, other.descrizione) && disponibilita == other.disponibilita
				&& idServizio == other.idServizio && Objects.equals(titolo, other.titolo);
	}

	
	
	
	

}
