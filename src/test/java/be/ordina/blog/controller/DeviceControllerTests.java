package be.ordina.blog.controller;

import be.ordina.blog.model.Device;
import be.ordina.blog.service.DeviceService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
@WebAppConfiguration
public class DeviceControllerTests {

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    public DeviceService deviceService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @After
    public void cleanup() {
        EasyMock.verify(deviceService);
        EasyMock.reset(deviceService);
    }

    @Test
    public void getDevices() throws Exception {
        Device firstDevice = new Device("iPhone 6");
        Device secondDevice = new Device("Nexus 5");
        List<Device> devices = new ArrayList<>();
        devices.add(firstDevice);
        devices.add(secondDevice);

        EasyMock.expect(deviceService.getDevices()).andReturn(devices);

        EasyMock.replay(deviceService);

        this.mockMvc.perform(get("/devices").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(firstDevice.getName())))
                .andExpect(jsonPath("$[1].name", is(secondDevice.getName())))
                .andDo(document("device"));
    }

}
