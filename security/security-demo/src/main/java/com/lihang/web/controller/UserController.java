package com.lihang.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lihang.dto.User;
import com.lihang.dto.UserQueryCondition;
import com.lihang.exception.UserNotExitException;
import io.swagger.annotations.*;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户数据接口")
public class UserController {

    //@RequestMapping(value = "/user",method = RequestMethod.GET)
    //@GetMapping("/user")
    @ApiOperation(value="查询用户",notes = "查询所有")
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
    @ApiOperation(value="查询用户详情",notes = "根据用户ID查询")
    @ApiImplicitParam(paramType = "path",name = "id",value = "用户ID",required = true)
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
    @ApiResponses({
            @ApiResponse(code=200,message = "删除成功"),
            @ApiResponse(code=500,message = "删除失败"),
    })
    @ApiOperation(value="删除用户",notes = "通过用户id删除")
    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@ApiParam("用户id") @PathVariable Integer id){
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
/*
* 获取当前登陆用户的信息
*  public Object getCurrentUserDetail(Authentication authentication)
* */
   @GetMapping("/detail")
    public Object getCurrentUserDetail(@AuthenticationPrincipal UserDetails userDetails){
       return userDetails;
   }
}
