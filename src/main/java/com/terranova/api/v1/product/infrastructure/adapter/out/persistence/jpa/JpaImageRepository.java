package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query("SELECT i FROM ImageEntity i WHERE i.product.productId IN :ids")
    List<ImageEntity> findByProductsIds(@Param("ids") List<Long> ids);
}
