package testtask.autoservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_seq")
    @SequenceGenerator(name = "goods_seq", allocationSize = 1)
    private Long id;
    private String name;
    private BigDecimal price;
}
