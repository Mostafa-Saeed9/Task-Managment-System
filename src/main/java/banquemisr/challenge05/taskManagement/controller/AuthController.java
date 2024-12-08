package banquemisr.challenge05.taskManagement.controller;


import banquemisr.challenge05.taskManagement.dto.LoginDto;
import banquemisr.challenge05.taskManagement.security.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private USerService

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword())
        );
        return jwtConfig.getnerateToken(loginDto.getUserName());
    }
}
