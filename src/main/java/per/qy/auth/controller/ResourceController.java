package per.qy.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ResourceController {

    @GetMapping("/protected")
    @ResponseBody
    public String protectedRes(HttpServletRequest request, HttpServletResponse response) {
        return "This is a protected resource.";
    }

    @GetMapping("/unprotected")
    @ResponseBody
    public String unprotected(HttpServletRequest request, HttpServletResponse response) {
        return "This is an unprotected resource.";
    }
}
