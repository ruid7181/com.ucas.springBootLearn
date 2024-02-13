package com.ucas.service.impl;

import com.ucas.entity.TMData;
import com.ucas.repository.TMDataRepository;
import com.ucas.service.TMDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

@Service
public class TMDataServiceImpl implements TMDataService {
    final
    TMDataRepository tmDataRepository;

    public TMDataServiceImpl(TMDataRepository tmDataRepository) {
        this.tmDataRepository = tmDataRepository;
    }

    public List<TMData> customQuery(Map<String, String> queryMap){
        Specification<TMData> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (queryMap.get("place") != null){
                if (queryMap.get("path_id") != null){
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("path_id").as(String.class), queryMap.get("path_id")));
                }
                if (queryMap.get("row_id") != null){
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("row_id").as(String.class), queryMap.get("row_id")));
                }
            }

            criteriaQuery.distinct(true).where(predicate);
            return criteriaQuery.getRestriction();
        };

        return tmDataRepository.findAll(specification);
    }
}
