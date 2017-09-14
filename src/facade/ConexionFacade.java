/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author franco   
 */
public class ConexionFacade {

    public static final Map PROPIEDADES = new HashMap<String, String>() {
        {
            put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/secyt");
            put("javax.persistence.jdbc.password","napo2009");
            put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            put("javax.persistence.jdbc.user","root");
        }
    };
}
