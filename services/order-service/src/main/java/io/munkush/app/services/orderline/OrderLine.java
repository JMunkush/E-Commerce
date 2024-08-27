package io.munkush.app.services.orderline;


import io.munkush.app.services.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_line")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private double quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
