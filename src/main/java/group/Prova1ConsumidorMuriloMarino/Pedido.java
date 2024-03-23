package group.Prova1ConsumidorMuriloMarino;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Pedido implements Serializable {
    private ItemCardapio cardapio;
    private int quantidade = 1;

    public Pedido(ItemCardapio cardapio) {
        this.cardapio = cardapio;
    }

    public ItemCardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(ItemCardapio cardapio) {
        this.cardapio = cardapio;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    public double getSubTotal() {
        return this.quantidade * this.cardapio.getPreco();
    }

    public String getSubTotalString() {
        DecimalFormat df = new DecimalFormat("#0.00");

        return "R$" + df.format(this.getSubTotal());
    }
}
