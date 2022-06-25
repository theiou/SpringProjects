package by.theiou.SpringLibrary.models;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class Book {
    private int id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "Диапазон длины {1; 100}")
    private String name;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100, message = "Диапазон длины {1; 100}")
    private String author;
    @NotNull(message = "Поле не может быть пустым")
    @Range(min = 1500, max = 2022, message = "Год издания книги должен находится в диапазоне от 1500 до 2022")
    private int year;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
