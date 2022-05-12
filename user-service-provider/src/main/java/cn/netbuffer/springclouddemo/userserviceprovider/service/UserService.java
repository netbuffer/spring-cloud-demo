package cn.netbuffer.springclouddemo.userserviceprovider.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.stereotype.Service;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Trace(operationName = "addUser")
    @Tags({@Tag(key = "addUserParam", value = "arg[0]"), @Tag(key = "returnUser", value = "returnedObj")})
    public Map add(Map user) {
        int id = (int) (Math.random() * 100000);
        log.debug("add user={} id={}", user, id);
        user.put("id", id);
        return user;
    }

}