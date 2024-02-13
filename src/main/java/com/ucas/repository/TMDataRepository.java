package com.ucas.repository;

import com.ucas.entity.TMData;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TMDataRepository extends JpaRepository<TMData, Long> {
    List<TMData> findAll(Specification<TMData> specification);
    // jpa里面已经定义了增删改查操作.
    // 下面定义自己的方法.
}
