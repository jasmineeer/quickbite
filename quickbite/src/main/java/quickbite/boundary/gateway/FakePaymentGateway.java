package quickbite.boundary.gateway;

import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import quickbite.controller.exception.PaymentFailedException;

// Gateway PALSU untuk belajar/uji lokal (aktif jika profile BUKAN "prod").
// Token "tok_gagal" sengaja ditolak supaya bisa menguji HTTP 502.
@Component
@Profile("!prod")
class FakePaymentGateway implements PaymentGatewayAPI {

    @Override
    public String mintaTransaksiPembayaran(double total, String tokenKartu) {
        if ("tok_gagal".equals(tokenKartu)) {
            throw new PaymentFailedException("Gateway menolak transaksi");
        }
        return "REF-" + UUID.randomUUID();
    }
}
