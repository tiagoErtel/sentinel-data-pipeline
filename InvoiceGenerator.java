import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class InvoiceGenerator {

    private static final String[] PRODUCTS = { "Hammer", "Steel Pan", "Chef Knife", "Garden Shovel",
            "Pressure Cooker" };
    private static final String[] STORES = { "Dublin_Central", "Cork_Hub", "Galway_Store", "Limerick_Outlet" };
    private static final String[] CURRENCIES = { "EUR", "BRL", "USD" };

    public static void main(String[] args) {
        String fileName = "invoices.csv";
        int recordsToGenerate = 10000;

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append(
                    "invoice_id,timestamp,product_name,quantity,unit_price,currency,store_location,total_amount\n");

            Random random = new Random();

            for (int i = 0; i < recordsToGenerate; i++) {
                String invoiceId = UUID.randomUUID().toString().substring(0, 8);
                String timestamp = LocalDateTime.now().minusDays(random.nextInt(30)).toString();
                String product = PRODUCTS[random.nextInt(PRODUCTS.length)];
                int quantity = random.nextInt(5) + 1;
                double unitPrice = 10 + (random.nextDouble() * 90);
                String currency = CURRENCIES[random.nextInt(CURRENCIES.length)];
                String store = STORES[random.nextInt(STORES.length)];
                double total = unitPrice * quantity;

                writer.append(String.format("%s,%s,%s,%d,%.2f,%s,%s,%.2f\n",
                        invoiceId, timestamp, product, quantity, unitPrice, currency, store, total));
            }

            System.out.println("✅ Successfully generated " + recordsToGenerate + " invoices in " + fileName);

        } catch (IOException e) {
            System.err.println("❌ Error writing to file: " + e.getMessage());
        }
    }
}
