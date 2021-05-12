package pdm.clothshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    private int productId;

    private String productName;
    private String productDescription;
    @Column(name = "price")
    private float productPrice;

    private String image;

    @Column(name = "manufacturedate")
    private Date manufactureDate;

    @ManyToOne
    @JoinColumn(name = "brandid")
     Brand brand;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "spec",
    joinColumns = @JoinColumn(name = "productid",insertable = false),
    inverseJoinColumns = @JoinColumn(name = "colorcode",insertable = false))
    private List<Color> colors=new ArrayList<>();

}
