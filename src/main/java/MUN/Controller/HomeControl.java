package MUN.Controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControl{

    @RequestMapping("/home")
    public String index(){
        return "resources/static/index.html";
    }

}
