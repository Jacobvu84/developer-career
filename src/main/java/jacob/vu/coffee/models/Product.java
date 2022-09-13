package jacob.vu.coffee.models;

public class Product {

    private Long id;
    private String name;
    private int year;
    private Double price;
    private String url;

    public Product(){}

    public Product(Long id, String name, int year, Double price, String url) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
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
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
