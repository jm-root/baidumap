package cn.jamma.baidumap.service;

import cn.jamma.baidumap.bean.PlaceSearchParam;
import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dumap.DuMapClient;
import com.baidubce.services.dumap.model.PlaceSearchByRegionParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class PlaceService {
    private DuMapClient client = null;

    @Value("${appid}")
    private String appid;

    @Value("${accessKey}")
    private String accessKey;

    @Value("${secretKey}")
    private String secretKey;

    @Autowired
    private Environment env;

    public DuMapClient getClient() {
        if (client == null) {
            BceClientConfiguration config = new BceClientConfiguration()
                    .withCredentials(new DefaultBceCredentials(accessKey, secretKey));

            client = new DuMapClient(config);
        }
        return client;
    }

    public String search(PlaceSearchParam param) {
        PlaceSearchByRegionParam params = PlaceSearchByRegionParam.builder()
                .query(param.query)
                .region(param.region)
                .output("json")
                .coordType(3)
                .retCoordtype("gcj02ll")
                .pageSize(param.page_size)
                .pageNum(param.page_num)
                .build();
        return getClient().placeQuery(appid, params);
    }
}