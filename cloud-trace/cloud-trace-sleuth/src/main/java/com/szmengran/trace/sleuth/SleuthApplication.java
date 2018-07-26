package com.szmengran.trace.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.szmengran.trace.sleuth
 * @Description: TODO
 * @date 2018年7月14日 上午11:56:37
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@SpringBootApplication
@RefreshScope
@RestController
public class SleuthApplication {

  private static Logger log = LoggerFactory.getLogger(SleuthApplication.class);

  @RequestMapping("/")
  public String home() {
    log.info("Handling home");
    return "Hello World";
  }

  public static void main(String[] args) {
    SpringApplication.run(SleuthApplication.class, args);
  }
}
