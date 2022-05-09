package ru.netology.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Product;

@NoArgsConstructor
@Data
public class RepositoryProduct {
    Product[] products = new Product[0];

    public void saveProduct(Product product) {
        for (Product product1 : products) {
            if (product1.getId() == product.getId()) {
                throw new AlreadyExistsException("Element with id: " + product.getId() + " already exist");
            }
        }
        int length = products.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(products, 0, tmp, 0, products.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = product;
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = products.length - 1;
        Product[] exist = new Product[length];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                exist[index] = product;
                index++;
            }
            products = exist;
        }
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
