package entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

        private int id;
            private Date data;
            private double valorTotal;
            private Pessoal pessoal;
            private List<ItemPedido> listaItens;

            //teste

        public Pedido() {
            pessoal = new Pessoal();
            listaItens = new ArrayList<>();
        }

        public Pedido(Pessoal pessoal, List<ItemPedido> listaItens) {
            this.pessoal = pessoal;
            this.listaItens = listaItens;
        }

        public int getId() {
            return id;
        }

        public void setId(int Id) {
            this.id = Id;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }

        public void setValorTotalAcumulando(double valor) {
            this.valorTotal += valor;
        }

        public Pessoal getPessoal() {
            return pessoal;
        }

        public void setPessoal(Pessoal pessoal) {
            this.pessoal = pessoal;
        }

        public List<ItemPedido> getListaItens() {
            return listaItens;
        }

        public void setListaItens(List<ItemPedido> listaItens) {
            this.listaItens = listaItens;
        }



    }
