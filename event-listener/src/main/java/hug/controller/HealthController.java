package hug.controller;

import com.google.common.collect.ImmutableMap;
import com.util.XDateUtils;
import hug.common.CommonEventPublisher;
import hug.listeners.events.MsgEventEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
@Api(value = "HealthController", tags = "HealthController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HealthController {

    @Resource
    Environment evn;

    @Resource
    private CommonEventPublisher commonEventPublisher;

    @GetMapping(value = "status")
    @ApiOperation(value = "health", notes = "health")
    public String health() {
        log.info("-{}", evn.getProperty("catalina.base"));

        Date date = XDateUtils.getCurrentDate2();

        log.info("date : {}", date);

        return "ok";
    }

    @GetMapping(value = "test")
    @ApiOperation(value = "test", notes = "test")
    public String test() {
        log.info("-{}", evn.getProperty("catalina.base"));

        Map source = ImmutableMap.of("order_id", "1", "order_state", "i'm create!");
        commonEventPublisher.publish(source, MsgEventEnum.order_create);


        return "ok";
    }
}
