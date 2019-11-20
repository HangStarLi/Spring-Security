package com.lihang.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lihang.dto.User;
import com.lihang.dto.UserQueryCondition;
import com.lihang.exception.UserNotExitException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    //@GetMapping("/user")
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUser(UserQueryCondition condition,@PageableDefault(size = 5,page = 1,sort = "age,asc") Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> list = new ArrayList<>(3);
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }


   // @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)//注意反斜杠\\
    //@GetMapping("/user/{id:\\d+}")
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUserInfoTest(@PathVariable Integer id){
        if (id<0)
            throw new UserNotExitException(id+"");

        System.out.println(id+":进入服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User createUser(@Valid  @RequestBody User user, BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));

        return user;
    }

    @PutMapping("/{id:\\d+}")
    public void updateUser(@Valid @RequestBody User user,BindingResult errors){
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error->{
                FieldError fieldError = (FieldError) error;
                System.out.println(fieldError.getField()+":"+error.getDefaultMessage());
            });
        }
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable Integer id){
        System.out.println(id);
    }

    /*
    * 测试自定义异常
    * */
/*    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getUserInfoTestException(@PathVariable Integer id){
       throw new UserNotExitException(id+"");
    }*/
}
