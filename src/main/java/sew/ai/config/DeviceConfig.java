package sew.ai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import sew.ai.models.AndroidDeviceModel;
import sew.ai.models.DeviceViewportModel;
import sew.ai.models.IOSDeviceModel;
import sew.ai.utils.FileUtil;

import java.io.IOException;
import java.nio.file.Files;

public class DeviceConfig {

    public static IOSDeviceModel readIOSDeviceConfig() throws IOException {
        byte[] jsonData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile("iosDevice.json")).toPath());
        IOSDeviceModel[] iosDeviceModels = objectMapper.readValue(jsonData, IOSDeviceModel[].class);
        return new IOSDeviceModel(iosDeviceModels);
    }

    public static AndroidDeviceModel readAndroidDeviceConfig() throws IOException {
        byte[] jsonData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile("androidDevice.json")).toPath());
        AndroidDeviceModel[] androidDeviceModels = objectMapper.readValue(jsonData, AndroidDeviceModel[].class);
        return new AndroidDeviceModel(androidDeviceModels);
    }

    public static DeviceViewportModel readDeviceViewportConfig() throws IOException {
        byte[] jsonData = null;
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = Files.readAllBytes(FileUtils.getFile(FileUtil.getFile("deviceViewport.json")).toPath());
        DeviceViewportModel[] deviceViewportModels = objectMapper.readValue(jsonData, DeviceViewportModel[].class);
        return new DeviceViewportModel(deviceViewportModels);
    }
}
