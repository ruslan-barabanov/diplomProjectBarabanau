import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathProperties {

    public static Properties readFile() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("src/test/resources/conf.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
