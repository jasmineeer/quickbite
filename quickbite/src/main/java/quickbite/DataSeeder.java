package quickbite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import quickbite.entity.Menu;
import quickbite.entity.Pelanggan;
import quickbite.repository.MenuRepository;
import quickbite.repository.PelangganRepository;

// Data awal untuk uji coba (hanya jalan jika profile bukan "prod")
@Component
@Profile("!prod")
public class DataSeeder implements CommandLineRunner {
    private final PelangganRepository pelangganRepo;
    private final MenuRepository menuRepo;

    public DataSeeder(PelangganRepository pelangganRepo, MenuRepository menuRepo) {
        this.pelangganRepo = pelangganRepo;
        this.menuRepo = menuRepo;
    }

    @Override
    public void run(String... args) {
        if (!pelangganRepo.existsById("P-01")) {
            pelangganRepo.save(new Pelanggan("P-01", "Budi", "budi@mail.com", "081234567890", "Malang"));
        }
        seedMenu("M-01", "Nasi Goreng Kampung", 25000.0, 20);
        seedMenu("M-02", "Mie Ayam Bakso", 22000.0, 20);
        seedMenu("M-03", "Ayam Geprek", 28000.0, 20);
        seedMenu("M-04", "Sate Ayam", 30000.0, 20);
        seedMenu("M-05", "Es Teh Manis", 8000.0, 20);
        seedMenu("M-06", "Kopi Susu", 18000.0, 20);
    }

    private void seedMenu(String id, String nama, double harga, int stok) {
        if (!menuRepo.existsById(id)) {
            menuRepo.save(new Menu(id, nama, harga, stok));
        }
    }
}