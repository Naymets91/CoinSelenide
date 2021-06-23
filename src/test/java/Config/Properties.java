package Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class Properties {

    private String wlClientBaseUri;

    public Properties()
    {
        java.util.Properties props = new java.util.Properties();
        File file = new File("config.txt");
        if (!file.exists()) {
            file = new File(getClass().getResource("/config.txt").getFile());
        }
        try {
            props.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.wlClientBaseUri = props.getProperty("WLClientBaseUri", "http://trader.sigma.eqvola.info/");
    }

    public String getWlClientBaseUri() {
        return wlClientBaseUri;
    }



}
