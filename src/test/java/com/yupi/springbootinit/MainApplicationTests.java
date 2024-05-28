package com.yupi.springbootinit;

import com.yupi.springbootinit.service.UserInterfaceInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 主类测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class MainApplicationTests {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Test
    void contextLoads() {
        // 调用了userInterfaceInfoService的invokeCount方法，并传入两个参数(1L, 1L)
        boolean b = userInterfaceInfoService.invokeCount(1L, 1L);
        // 表示断言b的值为true，即测试用例期望invokeCount方法返回true
        Assertions.assertTrue(b);
    }

}
