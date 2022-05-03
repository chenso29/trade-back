package src.main.java.com.trade_accounting.controllers.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trade_accounting.models.entity.client.Employee;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.trade_accounting.config.SecurityConstants.EXPIRATION_TIME;
import static com.trade_accounting.config.SecurityConstants.SECRET;

@RestController
@RequestMapping("/api/token")
@Tag(name = "Authorization Token Generator Controller", description = "Получение токена авторизации")
@Api(tags = "Authorization Token Generator Controller")
public class AuthRestController {

    @PostMapping
    public ResponseEntity<String> getToken() {
        Employee emp = new Employee();
        emp.setEmail("karimogon@mail.ru");
        String token = JWT.create()
                .withSubject(emp.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        return ResponseEntity.ok("Bearer " + token);
    }
}
