package pdm.clothshop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter

@Table(name = "color")
@NoArgsConstructor
@AllArgsConstructor
public class Color {
    @Id
    @Column(name = "colorcode")
    private String colorCode;
    @Column(name = "colorname")
    private String colorName;

}
