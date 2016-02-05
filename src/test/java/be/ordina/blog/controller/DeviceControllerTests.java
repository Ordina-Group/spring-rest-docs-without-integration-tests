package be.ordina.blog.controller;

import be.ordina.blog.model.Device;
import be.ordina.blog.service.DeviceService;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DeviceControllerTests {

    public DeviceController deviceController;
    public DeviceService mockedDeviceService = EasyMock.createStrictMock(DeviceService.class);

    @Before
    public void setup() {
        deviceController = new DeviceController(mockedDeviceService);
    }

    @After
    public void cleanup() {
        EasyMock.verify(mockedDeviceService);
        EasyMock.reset(mockedDeviceService);
    }

    @Test
    public void getDevices() throws Exception {
        List<Device> mockedDevices = new ArrayList<>();
        mockedDevices.add(new Device("iPhone 6"));
        mockedDevices.add(new Device("Nexus 5"));

        EasyMock.expect(mockedDeviceService.getDevices()).andReturn(mockedDevices);

        EasyMock.replay(mockedDeviceService);

        List<Device> devices = deviceController.getDevices();

        Assert.assertEquals(mockedDevices, devices);
    }

}
