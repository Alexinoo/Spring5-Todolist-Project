package academy.learnprogramming.controller;

import academy.learnprogramming.service.DemoServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class DemoController {

    // == fields ==
    private final DemoServiceInterface demoServiceInterface;

    // == constructors ==
    @Autowired
    public DemoController(DemoServiceInterface demoServiceInterface) {
        this.demoServiceInterface = demoServiceInterface;
    }

    /* Request parameters - annotation
     * Multiple parameters age
     http://localhost:8080/todo-list/welcome?user=Alex
     *
     * Multiple parameters
     http://localhost:8080/todo-list/welcome?user=Alex&age=33
     *
     * Add another attribute
     */
    @GetMapping("welcome")
    public String welcome(@RequestParam String user,@RequestParam int age, Model model){
        model.addAttribute("helloMessage",demoServiceInterface.getHelloMessage(user));
        model.addAttribute("age",age);
        log.info("model = {}",model);
        return "welcome";
    }

    @ModelAttribute("welcomeMessage")
    public String welcomeMessage(){
        log.info("welcomeMessage() called");
        return demoServiceInterface.getWelcomeMessage();
    }

    /* Simple Service Challenge

    @GetMapping("welcome")
    public String welcome(Model model){
        model.addAttribute("helloMessage",demoServiceInterface.getHelloMessage("Alex"));
        log.info("model = {}",model);
        return "welcome";
    }

    @ModelAttribute("welcomeMessage")
    public String welcomeMessage(){
        log.info("welcomeMessage() called");
        return demoServiceInterface.getWelcomeMessage();
    }

    */

    ////////////////////////////////

    /*
     * Basic Setup

    // http://localhost:8080/todo-list/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // http://localhost:8080/todo-list/welcome
    @GetMapping("welcome")
    public String welcome(Model model){
        model.addAttribute("user","Alex");
        log.info("model = {}",model);
        // prefix + name + suffix
        // /WEB-INF/view/welcome.jsp
        return "welcome";
    }
    *
    * // model attributes
    *
    *
    @ModelAttribute("welcomeMessage")
    public String welcomeMessage(){
        log.info("welcomeMessage() called");
        return "Welcome to this Demo application";
    }
    */
}
