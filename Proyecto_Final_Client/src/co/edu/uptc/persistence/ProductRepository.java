package co.edu.uptc.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Product;

public class ProductRepository {

    private static final String FILE_PATH = "data/products.json";
    private final Gson gson;
    private List<Product> products;


    public ProductRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.products = loadProducts();
    }

    private List<Product> loadProducts() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            List<Product> loaded = gson.fromJson(reader, new TypeToken<List<Product>>() {}.getType());
            return (loaded != null) ? loaded : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("[Error] No se pudo leer el archivo de productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Product findProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}
