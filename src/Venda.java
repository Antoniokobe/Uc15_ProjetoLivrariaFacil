import java.util.Date;

public class Venda {
    private int id;
    private Cliente cliente;
    private Date data;
    private double valorTotal;

    public Venda(int id, Cliente cliente, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.data = new Date();
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", data=" + data +
                ", total=R$ " + valorTotal +
                '}';
    }
}