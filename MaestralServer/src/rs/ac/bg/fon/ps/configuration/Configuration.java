/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Katarina
 */
public class Configuration {

    private static Configuration instance;
    private Properties properties;

    private Configuration() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("config/configuration.properties"));
        } catch (Exception ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getPort() {
        return properties.getProperty("port");
    }
    public void setPort(int port) throws Exception {
        properties.put("port", port + "");
        writeToFile();
    }
    public void setDbConfig(String username, String password, String url) throws Exception {
        properties.put("username", username);
        properties.put("password", password);
        properties.put("url", url);
        writeToFile();
    }



    private void writeToFile() throws Exception {
        FileOutputStream outputStream = new FileOutputStream("config/configuration.properties");
        properties.store(outputStream, "");
        outputStream.close();
    }
}
