package sew.ai.helpers.props;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePaths {
    public static final String APP_CONFIG_PROP = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator + "application.properties";
    public static final String UTILITY_CONTACT_INFO_CONFIG_PROP = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator + "UtilityContactInfo.properties";
    public static final String API_CONFIG_PROP = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator + "apiconfig.properties";
    public static final String DATA_CONFIG = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SCP_TEXT_PROPERTIES = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator + "textprops"
            + File.separator + "scp" + File.separator;
    public static final String CSP_TEXT_PROPERTIES = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator + "textprops"
            + File.separator + "csp" + File.separator;
    public static final String FILE_UPLOAD_PATH = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator + "uploads" + File.separator;
    
   
    public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + File.separator + "reports" +
            File.separator + "spark"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())+".html";
    
    public static final String SRC_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "java" + File.separator;
    public static final String RESOURCE_PATH = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator;
    public static final String TEST_SRC_PATH = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String TEST_RESOURCE_PATH = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SCP_DOWNLOAD_MYDATA_EXCEL = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator + "excel"
            + File.separator + "downloadmydata" + File.separator;
    public static final String SCP_JSON_TEXT_PROPERTIES = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator + "datamodels"+File.separator;
    public static final String CSP_JSON_TEXT_PROPERTIES = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator + "datamodels"+File.separator;
    public static final String DOWNLOAD_PATH = System.getProperty("user.dir") + File.separator + "downloads"
			+ File.separator;
    public static final String DOWNLOAD_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "downloads"
			+ File.separator;
    public static String notificationFilePath = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "test" + File.separator+ "resources" + File.separator+ "excel" +File.separator;
}
