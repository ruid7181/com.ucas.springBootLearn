package com.ucas.controller;

import com.ucas.entity.TMData;
import com.ucas.entity.TMQuery;
import com.ucas.repository.TMDataRepository;
import com.ucas.service.impl.TMDataServiceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/data")
public class TMDataHandler {
    private final TMDataRepository tmDataRepository;

    public TMDataHandler(TMDataRepository tmDataRepository) {
        this.tmDataRepository = tmDataRepository;
    }

    @GetMapping("/jpaFindAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", tmDataRepository.findAll());
        modelAndView.setViewName("index0");
        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/jpaCustomQuery")
    public List<TMData> customQuery(@RequestBody Map<String, String> queryMap){
        /*
        queryMap.put("con_path", "123");
        queryMap.put("con_row", "32");
        queryMap.put("time", "1");
        queryMap.put("con_tLis", "20160618|20210907|19890814");
        queryMap.put("con_tRange", "19900101|20000101");

        Map扁平化. 结构: {con_path, con_row, time, con_tLis, con_tRange, con_loc, order}
        */
        System.out.println(queryMap.toString());
        Specification<TMData> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (!Objects.equals(queryMap.get("con_path"), "")){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("path_id"), queryMap.get("con_path")));
            }
            if (!Objects.equals(queryMap.get("con_row"), "")){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("row_id"), queryMap.get("con_row")));
            }
            if (queryMap.get("time").equals("on")) {
                Predicate tPredicate;
                List<Predicate> tLisPredicate = new ArrayList<>();
                if (!queryMap.get("con_tLis").equals("false")) {
                    String[] tLis = queryMap.get("con_tLis").split("\\|");
                    System.out.println("CONTLIS");
                    for (String t :
                            tLis) {
                        tLisPredicate.add(criteriaBuilder.equal(root.get("time"), t));
                    }
                }
                if (!queryMap.get("con_tRange").equals("false")) {
                    String[] tRanges = queryMap.get("con_tRange").split("\\|");
                    System.out.println("hERE");
                    List<Predicate> tRangePredicate = new ArrayList<>();
                    tRangePredicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time"), tRanges[0]));
                    tRangePredicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("time"), tRanges[1]));

                    tPredicate = criteriaBuilder.or(criteriaBuilder.or(tLisPredicate.toArray(new Predicate[0])),
                            criteriaBuilder.and(tRangePredicate.toArray(new Predicate[0])));
                } else {
                    tPredicate = criteriaBuilder.or(tLisPredicate.toArray(new Predicate[0]));
                }
                predicate = criteriaBuilder.and(predicate, tPredicate);
            }
            criteriaQuery.distinct(true).where(predicate);
            return criteriaQuery.getRestriction();
        };
        List<TMData> tmDataList = this.tmDataRepository.findAll(specification);
        System.out.println(tmDataList);
        return tmDataList;
    }

    @GetMapping("/query")
    public ModelAndView toQuery(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("query");
        return modelAndView;
    }
}
