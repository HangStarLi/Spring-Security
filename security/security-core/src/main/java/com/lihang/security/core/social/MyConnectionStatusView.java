package com.lihang.security.core.social;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component("connect/status")
public class MyConnectionStatusView  extends AbstractView {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String,List<Connection<?>>> connection = (Map<String, List<Connection<?>>>) model.get("connectionMap");
        System.out.println(connection);
        Map<String,Boolean> result = new HashMap<>();
        for (String key :connection.keySet()) {
            result.put(key,CollectionUtils.isNotEmpty(connection.get(key)));
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
