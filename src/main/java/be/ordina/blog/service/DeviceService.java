package be.ordina.blog.service;

import be.ordina.blog.model.Device;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    public List<Device> getDevices() {
        List<Device> devices = new ArrayList<>();

        /*
            Some business logic here...
         */

        return devices;
    }
}
