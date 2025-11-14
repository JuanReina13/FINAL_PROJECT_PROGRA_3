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

    /**
     * Carga los productos desde el archivo JSON.
     */
    private List<Product> loadProducts() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            List<Product> loaded = gson.fromJson(reader, new TypeToken<List<Product>>() {}.getType());
            return (loaded != null) ? loaded : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("[Error] No se pudo leer el archivo de productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Devuelve todos los productos disponibles.
     */
    public List<Product> getAllProducts() {
        return products;
    }

    /**
     * Devuelve todos los productos pertenecientes a una categoría.
     * Ejemplo: getProductsByCategory("HOT_DOGS");
     */
    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Busca un producto por nombre (coincidencia parcial o exacta).
     */
    public Product findProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Agrega un nuevo producto a la lista y guarda el archivo.
     */
    public void addProduct(Product product) {
        products.add(product);
        saveProducts(products);
    }

    /**
     * Guarda la lista completa de productos al archivo JSON.
     */
    public void saveProducts(List<Product> products) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(products, writer);
            System.out.println("[OK] Productos guardados correctamente en " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("[Error] No se pudo guardar el archivo de productos: " + e.getMessage());
        }
    }

    /**
     * Elimina un producto por su nombre.
     */
    public boolean deleteProductByName(String name) {
        boolean removed = products.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (removed) {
            saveProducts(products);
        }
        return removed;
    }

    /**
     * Actualiza un producto existente (por nombre).
     */
    public boolean updateProduct(Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getName().equalsIgnoreCase(updatedProduct.getName())) {
                products.set(i, updatedProduct);
                saveProducts(products);
                return true;
            }
        }
        return false;
    }
}
