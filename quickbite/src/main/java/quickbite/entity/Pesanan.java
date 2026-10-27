package quickbite.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pesanan")
public class Pesanan {
    @Id private String idPesanan;
    private LocalDateTime tanggal;
    private double totalBiaya;

    @Enumerated(EnumType.STRING)
    private StatusPesanan status = StatusPesanan.MENUNGGU_BAYAR;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pelanggan", nullable = false)
    private Pelanggan pelanggan;

    @OneToMany(mappedBy = "pesanan", cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<ItemPesanan> items = new ArrayList<>();

    @OneToOne(mappedBy = "pesanan", cascade = CascadeType.ALL)
    private Pembayaran pembayaran;

    protected Pesanan() { }

    public Pesanan(String id, LocalDateTime tanggal, Pelanggan pelanggan) {
        this.idPesanan = id;
        this.tanggal = tanggal;
        this.pelanggan = pelanggan;
    }

    public void tambahItem(ItemPesanan item) {
        item.setPesanan(this); // komposisi ◆
        items.add(item);
    }

    public double hitungTotal() {
        totalBiaya = items.stream()
                .mapToDouble(ItemPesanan::hitungSubtotal)
                .sum();
        return totalBiaya;
    }

    public void updateStatus(StatusPesanan statusBaru) {
        this.status = statusBaru;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getIdPesanan() { return idPesanan; }
}
