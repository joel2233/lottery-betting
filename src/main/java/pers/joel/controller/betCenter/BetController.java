package pers.joel.controller.betCenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.joel.controller.BaseController;

@Controller
@RequestMapping("/betCenter")
public class BetController extends BaseController {

    protected transient final Logger log = LoggerFactory.getLogger(getClass());


}
