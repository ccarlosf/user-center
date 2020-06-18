package com.ccarlos.usercenter.controller.user;

import com.ccarlos.usercenter.domain.dto.user.LoginRespDTO;
import com.ccarlos.usercenter.domain.dto.user.UserLoginDTO;
import com.ccarlos.usercenter.domain.entity.user.User;
import com.ccarlos.usercenter.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("我被请求了...");
        return this.userService.findById(id);
    }

    // q?id=1&wxId=aaa&...
    @GetMapping("/q")
    public User query(User user) {
        return user;
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO loginDTO)  {

    }
}
