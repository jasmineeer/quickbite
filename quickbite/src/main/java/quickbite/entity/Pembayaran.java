package quickbite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {
    @Id private String idPembayaran;
    private double jumlahBayar;
    private String metode;
    private String statusBayar;
    private String kodeReferensi;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pesanan", nullable = false, unique = true)
    private Pesanan pesanan;

    protected Pembayaran() { }

    public Pembayaran(String id, Pesanan pesanan, double jumlahBayar,
                      String metode, String statusBayar,
                      String kodeReferensi) {
        this.idPembayaran = id;
        this.pesanan = pesanan;
        this.jumlahBayar = jumlahBayar;
        this.metode = metode;
        this.statusBayar = statusBayar;
        this.kodeReferensi = kodeReferensi;
    }

    public boolean prosesVerifikasi() {
        return "SUKSES".equalsIgnoreCase(statusBayar)
                && kodeReferensi != null;
    }
}
