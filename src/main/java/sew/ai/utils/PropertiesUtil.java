package sew.ai.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil {
    Properties props = null;
    public static Properties resourcePathProps = null;
    public static Properties applicationProps = null;
    InputStream inputStream = null;
    String configPropFP;

    public PropertiesUtil(String configPropFP) {
        try {
            props = new Properties();
            this.configPropFP = configPropFP;
            inputStream = new FileInputStream(configPropFP);
            props.load(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void initResourcePathProps(String filePath) {
        resourcePathProps = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            resourcePathProps.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initApplicationProps(String filePath) {
        applicationProps = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            applicationProps.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getResourcePathValue(String key) {
        String value;
        value = resourcePathProps.getProperty(key).trim();
        return value;
    }

    public static String getApplicationPropValue(String key) {
        String value;
        value = applicationProps.getProperty(key).trim();
        return value;
    }

    public String getPropValue(String key) {
        String value;
        value = props.getProperty(key).trim();
        return value;
    }
    
    /**
	 * This method is used to get the multiple values against given single key.
	 *
	 * @param key
	 * @return
	 */
	public List<String> getMultipleRbTextValues(String key){
		Properties props = null;
		List<String> multipleProps = new ArrayList<String>();
		String property = props.getProperty(key).trim();
		String[] properties = property.split(",");
		multipleProps = Arrays.asList(properties);
		return multipleProps;
	}
    
}
