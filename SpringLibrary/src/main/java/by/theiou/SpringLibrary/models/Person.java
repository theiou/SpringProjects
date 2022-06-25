package by.theiou.SpringLibrary.models;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 100,message = "Диапазон длины {1; 100}")
    private String name;
    @NotNull(message = "Поле не может быть пустым")
    @Range(min = 1900, max = 2022, message = "Год рожддения должен находится в диапазоне от 1900 до 2022")
    private int yearOfBirth;

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

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Person(int id, String name, int yearOfBirth) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }
    public Person(){}


}
