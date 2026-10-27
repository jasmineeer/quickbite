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
        if (!menuRepo.existsById("M-01")) {
            menuRepo.save(new Menu("M-01", "Nasi Goreng", 25000.0, 10));
        }
        if (!menuRepo.existsById("M-02")) {
            menuRepo.save(new Menu("M-02", "Es Teh", 25000.0, 10));
        }
    }
}
