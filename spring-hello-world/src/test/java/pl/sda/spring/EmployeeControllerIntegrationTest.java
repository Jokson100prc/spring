package pl.sda.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
/*    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void cleanDb() {
        employeeRepository.deleteAll();
        // clean also state for 100 otger repositories
        // and also remember to re-initialize google drive
        // abd 5 other AWS instances
        // oh, and also we use mongodb - clean this too
    }*/

    @Test
    public void givenEmptyEmployeesDb_WhenCallGetOnEmployees_ThenStatus200AndEmptyListIsReturned() throws Exception {
        // when
        mockMvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                // then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }

    @Test
    public void givenOneEmployee_WhenCallGetOnEmployees_ThenStatus200AndListContainingThisEmployeeIsReturned() throws Exception {
        // given
        String body = "{\"name\": \"goobar\"}";
        mockMvc.perform(
                post("/api/employees").content(body).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        // when
        mockMvc.perform(get("/api/employees"))

                .andDo(print())
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("goobar")));
    }

    @Test
    public void givenTwoEmployees_WhenCallGetSingleEmployee_ThenStatusIs200AndSingleMatchingEmployeeIsReturned() throws Exception {
        // given
        // TODO: wyslij post z name "goobar"
        // TODO: wyslij nastepny post z name "foobar"


        String creaeteGoobarBody = "{\"name\": \"goobar\"}";
        String createFoobarBody = "{\"name\": \"foobar\"}";
        MvcResult mvcResult = mockMvc.perform(
                post("/api/employees").content(creaeteGoobarBody).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk()).andReturn();
        Employee createdGoobar =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);
        mockMvc.perform(
                post("/api/employees").content(createFoobarBody).contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());


        // when
        // TODO: wyslij get na "api/employees/{id}"

        mockMvc.perform(get("/api/employees/{id}", createdGoobar.getId()))
                .andDo(print())

                // then
                // TODO: upewnij sie ze status jest ok
                // TODO: upewnij sie ze id i name pasuja do wybranego employee

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(createdGoobar.getId())))
                .andExpect(jsonPath("$.name", is("goobar")));
    }
}
