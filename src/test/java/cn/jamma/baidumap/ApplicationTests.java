package cn.jamma.baidumap;

import cn.jamma.baidumap.service.PlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	PlaceService placeService;

	@Test
	public void place() {
		System.out.println(placeService);
	}
}
