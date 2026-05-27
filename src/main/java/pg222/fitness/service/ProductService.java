package pg222.fitness.service;

import pg222.fitness.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private FileService fileService;

    public List<Product> getAllProducts() throws IOException {
        List<Product> products = new ArrayList<>();
        List<String> lines = fileService.readFile("products.txt");
        for (String line : lines) {
            String[] parts = line.split(",");
            products.add(new Product(
                    parts[0],
                    parts[1],
                    Double.parseDouble(parts[2]),
                    Boolean.parseBoolean(parts[3]),
                    parts[4]
            ));
        }
        return products;
    }

    public void saveProduct(Product product) throws IOException {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        if (!product.isAvailable()){
            product.setAvailable(false);
        }

        List<Product> products = getAllProducts();
        List<String> newLines = new ArrayList<>();
        boolean found = false;

        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                newLines.add(product.toString());
                found = true;
            } else {
                newLines.add(p.toString());
            }
        }

        if (!found) {
            newLines.add(product.toString());
        }

        fileService.writeFile("products.txt", newLines);
    }

    public void deleteProduct(String id) throws IOException {
        List<Product> products = getAllProducts();
        List<String> newLines = new ArrayList<>();

        for (Product p : products) {
            if (!p.getId().equals(id)) {
                newLines.add(p.toString());
            }
        }

        fileService.writeFile("products.txt", newLines);
    }

    public Product getProductById(String id) throws IOException {
        List<Product> products = getAllProducts();
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}