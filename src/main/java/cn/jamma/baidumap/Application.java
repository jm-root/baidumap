package cn.jamma.baidumap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Application {

	@GetMapping("/")
	Map<String,Object> hello(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("name", "baidumap");
		param.put("version", "0.0.1");
		return param;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
