package interview.library.model;

import interview.library.model.converter.BooleanStringConverter;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    private boolean isLoaned;

    public Book() {}

    public Book(String title) {
        this.title = title;
        this.isLoaned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean isLoaned) {
        this.isLoaned = isLoaned;
    }

}
