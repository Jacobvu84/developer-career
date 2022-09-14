package jacob.vu.coffee.models;

import javax.persistence.*;

@Entity
@Table(name="tblProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    @Column(nullable = false, unique = true, length = 255)
    private String name;
    @Column(name = "yearOfManufacture")
    private int _year;
    private Double price;
    private String url;

    public Product(){}

    public Product(String name, int year, Double price, String url) {
        this.name = name;
        this._year = year;
        this.price = price;
        this.url = url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void set_year(int _year) {
        this._year = _year;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int get_year() {
        return _year;
    }

    public Double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + _year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
