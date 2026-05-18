package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaImageRepository extends JpaRepository<ImageEntity, Long> {
    @Query("SELECT i FROM ImageEntity i WHERE i.product.productId IN :ids")
    List<ImageEntity> findByProductsIds(@Param("ids") List<Long> ids);

    List<ImageEntity> findAllByProduct_ProductIdAndIdImageIn(Long productId, List<Long> imagesIds);

    @Modifying
    @Query("DELETE FROM ImageEntity i WHERE i.product.productId = :productId AND i.idImage IN :imagesIds")
    int deleteByProductIdAndIds(Long productId, List<Long> imagesIds);
}
