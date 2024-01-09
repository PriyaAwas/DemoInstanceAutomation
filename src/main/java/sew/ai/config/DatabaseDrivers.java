package sew.ai.config;

import java.util.Map;

public class DatabaseDrivers {
    private Map<String, Map<String, DataBaseModel>> dataBaseConfig;

    public DatabaseDrivers() {
    }

    public Map<String, Map<String, DataBaseModel>> getDataBaseConfig() {
        return dataBaseConfig;
    }

    public void setDataBaseConfig(Map<String, Map<String, DataBaseModel>> dataBaseConfig) {
        this.dataBaseConfig = dataBaseConfig;
    }

    public DataBaseModel getDataBaseConfigurationValue(String env, String databaseType) {
        String databaseURI;
        DataBaseModel dataBaseModel = dataBaseConfig.get(env).get(databaseType);
        if (databaseType.equals("postgresql_db")) {
            databaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    "/" + dataBaseModel.getDatabase();
        } else {
            databaseURI = dataBaseModel.getDriver() + "//" + dataBaseModel.getHost() +
                    ";database=" + dataBaseModel.getDatabase() +
                    ";user=" + dataBaseModel.getUser() +
                    ";password=" + dataBaseModel.getPassword() +
                    ";ecrypt=" + dataBaseModel.getEncrypt() +
                    ";trustServerCertificate=" + dataBaseModel.getTrustServerCertificate();
        }
        return dataBaseModel;
    }
}
