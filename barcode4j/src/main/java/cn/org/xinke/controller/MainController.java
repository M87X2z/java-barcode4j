package cn.org.xinke.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cinco on 2018-3-21.
 */
@RestController
public class MainController {

    @RequestMapping("/")
    public String main () {
        return "Hello barcode4j..................";
    }
}
