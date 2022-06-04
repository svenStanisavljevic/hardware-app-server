package hr.tvz.stanisavljevic.hardwareapp.hardware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HardwareService hardwareService;

    private String token;

    @BeforeEach
    void setUp() throws Exception {
        Map<String,Object> body = new HashMap<>();
        body.put("username","Sven");
        body.put("password","4v9vuaTd");
        MvcResult mvcResult = mockMvc.perform(post("/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        response = response.replace("{\"jwt\":\"", "");
        token = response.replace("\"}", "");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllHardware() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hardware")
                        .with(csrf())
                        .header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void getHardwareByCode() throws Exception {

        String TEST_CODE = "258137";
        String TEST_NAME = "Kplus 8GB RAM";

        this.mockMvc.perform(
                        get("/hardware/" + TEST_CODE)
                                .with(csrf())
                                .header("Authorization","Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.code").value(TEST_CODE))
                .andExpect(jsonPath("$.name").value(TEST_NAME));
    }

    @Test
    void save() throws Exception {
        String TEST_CODE = "test";
        String TEST_NAME = "test1";
        String TEST_TYPE = "CPU";
        Integer TEST_COUNT = 200;
        Long TEST_PRICE = 120L;

        HardwareCommand hardwareCommand = new HardwareCommand(
                TEST_NAME,TEST_CODE,TEST_PRICE,TEST_TYPE,TEST_COUNT);


        this.mockMvc.perform(
                        post("/hardware")
                                .with(csrf())
                                .header("Authorization","Bearer " + token)
                                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                                .content(objectMapper.writeValueAsString(hardwareCommand))
                                .accept(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.price").value(TEST_PRICE))
                .andExpect(jsonPath("$.code").value(TEST_CODE));

    }

}