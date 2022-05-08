package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book extends Product {
    protected String author;

    public Book(int id, String title, int price, String author) {
        super(id, title, price);
        this.author = author;
    }
}
