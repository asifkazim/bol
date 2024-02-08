package az.asifkazim.bol.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(length = 64)
    private String photos;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "firstPrice")
    private String firstPrice;

    @Column(name = "price")
    private String price;

    @Column(name = "status")
    private String status;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;

        return "user-photos/" + id + "/" + photos;
    }

    public Product() {

    }

    public Product(Long id, Integer productId, String photos, String name, String description, String firstPrice, String price, String status) {
        this.id = id;
        this.productId = productId;
        this.photos = photos;
        this.name = name;
        this.description = description;
        this.firstPrice = firstPrice;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(String firstPrice) {
        this.firstPrice = firstPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


