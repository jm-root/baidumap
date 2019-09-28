package cn.jamma.baidumap.controller;

import cn.jamma.baidumap.bean.PlaceSearchParam;
import cn.jamma.baidumap.service.PlaceService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PlaceController {
    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    PlaceService placeService;

    @GetMapping("/placeSearch")
    Object search(
            @RequestParam String query,
            @RequestParam(required = false, defaultValue = "中国") String region,
            @RequestParam(required = false, defaultValue = "10") int page_size,
            @RequestParam(required = false, defaultValue = "0") int page_num
    ) {
        PlaceSearchParam param = new PlaceSearchParam();
        param.query = query;
        param.region = region;
        param.page_size = page_size;
        param.page_num = page_num;

        try {
            String doc = placeService.search(param);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(doc);
            return rootNode;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> o = new HashMap<String, Object>();
            o.put("statu", 1);
            o.put("message", e.getMessage());
            return o;
        }
    }

    @GetMapping("/reverseGeocoder")
    Object reverseGeocoder(@RequestParam String location) {
        try {
            String doc = placeService.reverseGeocoder(location);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(doc);
            return rootNode;
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> o = new HashMap<String, Object>();
            o.put("statu", 1);
            o.put("message", e.getMessage());
            return o;
        }
    }
}
