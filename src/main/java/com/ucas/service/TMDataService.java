package com.ucas.service;

import com.ucas.entity.TMData;

import java.util.List;
import java.util.Map;

public interface TMDataService{
    List<TMData> customQuery(Map<String, String> queryMap);
}
