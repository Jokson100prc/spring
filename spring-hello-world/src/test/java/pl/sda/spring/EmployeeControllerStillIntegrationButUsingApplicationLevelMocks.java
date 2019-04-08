package pl.sda.spring;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeeControler.class)
public class EmployeeControllerStillIntegrationButUsingApplicationLevelMocks {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void givenEmptyEmployeesDb_WhenGetAllEmployees_ThenStatusIsOkAndEmptyListIsReturned() throws Exception {
        // when
        mockMvc.perform(get("/api/employees"))

                .andDo(print())
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }

    @Test
    public void givenTwoEmployeesInDb_WhenGetAllEmployees_ThenStatusIsOkAndListContainingTwoEmployeesIsReturned() throws Exception {
        // given
        Employee goobar = new Employee(10);
        goobar.setName("goobar");
        Employee foobar = new Employee(500);
        foobar.setName("foobar");
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(goobar, foobar));

        // when
        mockMvc.perform(get("/api/employees"))

                .andDo(print())
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(10)))
                .andExpect(jsonPath("$[0].name", is("goobar")))
                .andExpect(jsonPath("$[1].id", is(500)))
                .andExpect(jsonPath("$[1].name", is("foobar")));
    }
}
