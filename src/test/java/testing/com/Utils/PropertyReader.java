package testing.com.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public PropertyReader(){

    }

    public static String readKey(String key) throws Exception {
        Properties p=new Properties();

        FileInputStream file= new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/testdata.properties"));
      p.load(file);

      if(p.getProperty(key)==null) {
          throw new Exception("Key not present");
      }
      else{
              return p.getProperty(key);

          }

      }

    }



