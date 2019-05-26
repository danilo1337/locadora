package entidade;

import java.util.Date;

/**
 * @author Felipe Karioka
 */

public class Copias {
    private int id = 0;
    private int filmeId = 0;
    private Boolean disponivel = false;
    private Boolean reserva = false;
    private Boolean disponivelVenda = false;
    private Date dataReserva = null;
    private Date dataCompra = null;
    private Date dataVenda = null;

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

    public Boolean getReserva() {
        return reserva;
    }

    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }

    public Boolean getDisponivelVenda() {
        return disponivelVenda;
    }

    public void setDisponivelVenda(Boolean disponivelVenda) {
        this.disponivelVenda = disponivelVenda;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
}
