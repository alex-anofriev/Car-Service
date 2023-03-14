package testtask.autoservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import testtask.autoservice.model.enums.OrderStatus;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goods_seq")
    @SequenceGenerator(name = "goods_seq", allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
    @Column(name = "problem_description")
    private String problemDescription;
    @Column(name = "acceptance_date")
    private LocalDate acceptanceDate;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ServiceModel> services;
    @ManyToMany
    @JoinTable(name = "orders_goods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goods;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "cost_for_client")
    private BigDecimal costForClient;
    @Column(name = "finish_date")
    private LocalDate finishDate;
    @Column(name = "agreement_to_repair")
    private Boolean agreementToRepair = Boolean.TRUE;
}
