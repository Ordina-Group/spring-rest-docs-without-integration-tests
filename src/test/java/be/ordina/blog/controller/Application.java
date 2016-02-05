package be.ordina.blog.controller;

import be.ordina.blog.service.DeviceService;
import org.easymock.EasyMock;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "be.ordina.blog.controller" } )
public class Application {

    @Bean
    public DeviceService getDeviceService() {
        return EasyMock.createStrictMock(DeviceService.class);
    }
}
