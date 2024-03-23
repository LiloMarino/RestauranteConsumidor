package group.Prova1ConsumidorMuriloMarino;

public class Pedido {
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

    public double getSubTotal()
    {
        return this.quantidade * this.cardapio.getPreco();
    }
}
