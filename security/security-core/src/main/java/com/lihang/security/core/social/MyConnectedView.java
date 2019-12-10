package com.lihang.security.core.social;
import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
public class MyConnectedView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        if(map.get("connection") == null){
            httpServletResponse.getWriter().write("<h3>解除绑定成功</h3>");
        }else {
            httpServletResponse.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
