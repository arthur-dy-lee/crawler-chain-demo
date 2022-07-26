package arthur.dy.lee.helloworld;

import arthur.dy.lee.chain.OmniService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Arthur.li
 * @since 2022/7/25 15:39
 */
@Slf4j
@RestController
@RequestMapping("v1")
public class CrawlerController {

    @Resource OmniService omniService;


}
