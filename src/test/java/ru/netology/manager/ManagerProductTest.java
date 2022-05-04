package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.Book;
import ru.netology.Product;
import ru.netology.Smartphone;
import ru.netology.repository.RepositoryProduct;

import static org.junit.jupiter.api.Assertions.*;

class ManagerProductTest {

    Product bookOne = new Book(1, "Harry Potter", 250, "J. K. Rowling");
    Product bookTwo = new Book(3, "The Great Shantaram", 350, "Gregory David");
    Product bookThree = new Book(5, "The Great Don Quixote", 215, "Miguel de Cervantes");
    Product bookFour = new Book(7, "The Great Ulysses", 280, "James Joyce");
    Product bookFive = new Book(9, "The Great Gatsby", 150, "F. Scott Fitzgerald");
    Product smartphoneOne = new Smartphone(2, "Mi 9T", 7000, "Xiaomi");
    Product smartphoneTwo = new Smartphone(4, "Iphone 13 Pro Max", 15000, "Apple");
    Product smartphoneThree = new Smartphone(6, "Galaxy S22 Ultra", 10000, "Samsung");
    Product smartphoneFour = new Smartphone(8, "Google Pixel 6", 12000, "Google");
    Product smartphoneFive = new Smartphone(10, "OnePlus 10 Pro", 9000, "BBK Electronics");


    @Test
    void shouldGetEmptyBox() {
        ManagerProduct manager = new ManagerProduct();

        Product[] expected = {};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGetOneProduct() {
        ManagerProduct manager = new ManagerProduct();

        manager.addProduct(bookOne);

        Product[] expected = {bookOne};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldGetSeveralProduct() {
        ManagerProduct manager = new ManagerProduct();

        manager.addProduct(bookOne);
        manager.addProduct(bookTwo);
        manager.addProduct(bookThree);
        manager.addProduct(smartphoneOne);
        manager.addProduct(smartphoneTwo);
        manager.addProduct(smartphoneThree);

        Product[] expected = {bookOne, bookTwo, bookThree, smartphoneOne, smartphoneTwo, smartphoneThree};
        Product[] actual = manager.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveProductByIdFirstProduct() {
        RepositoryProduct repository = new RepositoryProduct();

        repository.saveProduct(bookOne);
        repository.saveProduct(bookTwo);
        repository.saveProduct(bookThree);
        repository.saveProduct(bookFour);
        repository.saveProduct(bookFive);
        repository.saveProduct(smartphoneOne);
        repository.saveProduct(smartphoneTwo);
        repository.saveProduct(smartphoneThree);
        repository.saveProduct(smartphoneFour);
        repository.saveProduct(smartphoneFive);

        repository.removeById(1);

        Product[] expected = {bookTwo, bookThree, bookFour, bookFive, smartphoneOne, smartphoneTwo, smartphoneThree, smartphoneFour, smartphoneFive};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveProductByIdFifthProduct() {
        RepositoryProduct repository = new RepositoryProduct();

        repository.saveProduct(bookOne);
        repository.saveProduct(bookTwo);
        repository.saveProduct(bookThree);
        repository.saveProduct(bookFour);
        repository.saveProduct(bookFive);
        repository.saveProduct(smartphoneOne);
        repository.saveProduct(smartphoneTwo);
        repository.saveProduct(smartphoneThree);
        repository.saveProduct(smartphoneFour);
        repository.saveProduct(smartphoneFive);

        repository.removeById(9);

        Product[] expected = {bookOne, bookTwo, bookThree, bookFour, smartphoneOne, smartphoneTwo, smartphoneThree, smartphoneFour, smartphoneFive};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveProductByIdLastProduct() {
        RepositoryProduct repository = new RepositoryProduct();

        repository.saveProduct(bookOne);
        repository.saveProduct(bookTwo);
        repository.saveProduct(bookThree);
        repository.saveProduct(bookFour);
        repository.saveProduct(bookFive);
        repository.saveProduct(smartphoneOne);
        repository.saveProduct(smartphoneTwo);
        repository.saveProduct(smartphoneThree);
        repository.saveProduct(smartphoneFour);
        repository.saveProduct(smartphoneFive);

        repository.removeById(10);

        Product[] expected = {bookOne, bookTwo, bookThree, bookFour, bookFive, smartphoneOne, smartphoneTwo, smartphoneThree, smartphoneFour};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchProductOneResult() {
        ManagerProduct manager = new ManagerProduct();

        manager.addProduct(bookOne);
        manager.addProduct(bookTwo);
        manager.addProduct(bookThree);
        manager.addProduct(bookFour);
        manager.addProduct(bookFive);
        manager.addProduct(smartphoneOne);
        manager.addProduct(smartphoneTwo);
        manager.addProduct(smartphoneThree);
        manager.addProduct(smartphoneFour);
        manager.addProduct(smartphoneFive);

        manager.searchBy("Don Quixote");

        Product[] expected = {bookThree};
        Product[] actual = manager.findAllRevert();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchProductSeveralResult() {
        ManagerProduct manager = new ManagerProduct();

        manager.addProduct(bookOne);
        manager.addProduct(bookTwo);
        manager.addProduct(bookThree);
        manager.addProduct(bookFour);
        manager.addProduct(bookFive);
        manager.addProduct(smartphoneOne);
        manager.addProduct(smartphoneTwo);
        manager.addProduct(smartphoneThree);
        manager.addProduct(smartphoneFour);
        manager.addProduct(smartphoneFive);

        manager.searchBy("The Great");

        Product[] expected = {bookFive, bookFour, bookThree, bookTwo};
        Product[] actual = manager.findAllRevert();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchProductAllResult() {
        ManagerProduct manager = new ManagerProduct();

        manager.addProduct(bookTwo);
        manager.addProduct(bookThree);
        manager.addProduct(bookFour);
        manager.addProduct(bookFive);

        manager.searchBy("The Great");

        Product[] expected = {bookFive, bookFour, bookThree, bookTwo};
        Product[] actual = manager.findAllRevert();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchProductNullResult() {
        ManagerProduct manager = new ManagerProduct();

        manager.addProduct(bookOne);
        manager.addProduct(bookTwo);
        manager.addProduct(bookThree);
        manager.addProduct(bookFour);
        manager.addProduct(bookFive);
        manager.addProduct(smartphoneOne);
        manager.addProduct(smartphoneTwo);
        manager.addProduct(smartphoneThree);
        manager.addProduct(smartphoneFour);
        manager.addProduct(smartphoneFive);

        manager.searchBy("Hello");

        Product[] expected = {};
        Product[] actual = manager.findAllRevert();

        assertArrayEquals(expected, actual);
    }
}