package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.Product;
import ru.netology.repository.RepositoryProduct;

@NoArgsConstructor
@Data
public class ManagerProduct {
    private RepositoryProduct repository = new RepositoryProduct();

    Product[] products = new Product[0];

    public void addProduct(Product product) {
        repository.saveProduct(product);
    }

    public Product[] findAll() {
        return repository.findAll();
    }

    public void searchBy(String text) {
        int length = products.length + 1;
        Product[] result = new Product[length];// тут будем хранить подошедшие запросу продукты
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                if (result.length < length) {
                    int lengthMore = products.length + 1;
                    Product[] moreThanOne = new Product[lengthMore];
                    System.arraycopy(products, 0, moreThanOne, 0, products.length);
                    int lastIndexMore = moreThanOne.length - 1;
                    moreThanOne[lastIndexMore] = product;
                    products = moreThanOne;
                    length++;
                    lengthMore++;
                }
                if (result.length == length) {
                    int lastIndex = result.length - 1;
                    result[lastIndex] = product; // "добавляем в конец" массива result продукт product
                    length++;
                    products = result;
                }
            }
        }
    }

    // метод определения соответствия товара product запросу search
    public boolean matches(Product product, String search) {
        if (product.getTitle().contains(search)) {
            return true;
        } else {
            return false;
        }
        // или в одну строку:
        // return product.getName().contains(search);
    }

    public Product[] findAllRevert() {
        Product[] tmp = new Product[products.length];
        for (int i = 0; i < products.length; i++) {
            int index = products.length - i - 1;
            tmp[i] = products[index];

        }
        products = tmp;
        return products;
    }
}
