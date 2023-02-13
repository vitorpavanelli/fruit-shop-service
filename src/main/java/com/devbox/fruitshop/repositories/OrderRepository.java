package com.devbox.fruitshop.repositories;

import com.devbox.fruitshop.repositories.models.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  @Query("SELECT o FROM Order o JOIN FETCH o.orderLines WHERE o.id = :id")
  Optional<Order> findByIdWithOrderLines(final Long id);
}
