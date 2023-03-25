package pojo;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
//    @Column(name="name")
    private String name;
//    @Column(name="price")
    private double price;
    public Product(){
        super();
    }
    public Product(String id,String name,double price){
        super();
        this.id= id;
        this.name= name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
