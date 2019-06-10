package entidade;

import java.util.Date;

/**
 * @author Felipe Karioka
 */

public class Copias {
    private int id = 0;
    private String codigoCopia = "";
    private int filmeId = 0;
    private Boolean disponivel = false;
    private Boolean reservada = false;
    private Boolean disponivelVenda = false;
    private java.sql.Date dataReserva = null;
    private java.sql.Date dataVenda = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Boolean getReservada() {
        return reservada;
    }

    public void setReservada(Boolean reservada) {
        this.reservada = reservada;
    }

    public Boolean getDisponivelVenda() {
        return disponivelVenda;
    }

    public void setDisponivelVenda(Boolean disponivelVenda) {
        this.disponivelVenda = disponivelVenda;
    }

    public java.sql.Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(java.sql.Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public java.sql.Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(java.sql.Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCodigoCopia() {
        return codigoCopia;
    }

    public void setCodigoCopia(String codigoCopia) {
        this.codigoCopia = codigoCopia;
    }
}
