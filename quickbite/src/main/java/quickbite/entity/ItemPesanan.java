package quickbite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pesanan")
public class ItemPesanan {
    @Id private String idItem;
    private int jumlah;
    private double subtotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pesanan", nullable = false)
    private Pesanan pesanan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    protected ItemPesanan() { }

    public ItemPesanan(String idItem, int jumlah, Menu menu) {
        this.idItem = idItem;
        this.jumlah = jumlah;
        this.menu = menu;
    }

    public double hitungSubtotal() {
        this.subtotal = menu.getHarga() * jumlah;
        return subtotal;
    }

    void setPesanan(Pesanan pesanan) { // package-private: hanya Pesanan yang boleh memanggil
        this.pesanan = pesanan;
    }
}
