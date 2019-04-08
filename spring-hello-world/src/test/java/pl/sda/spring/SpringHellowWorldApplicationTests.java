package pl.sda.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SpringHellowWorldApplicationTests {

    @Autowired
    private EmployeeControler controler;

    @Test
    public void contextLoads() {
        assertThat(controler).isNotNull();
    }

}
