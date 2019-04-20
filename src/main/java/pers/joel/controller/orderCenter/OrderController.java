package pers.joel.controller.orderCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.controller.BaseController;

@Controller
@RequestMapping("orderCenter")
public class OrderController extends BaseController {
    protected transient final Logger log = LoggerFactory.getLogger(getClass());


}
