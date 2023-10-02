package cl.barbatos.basemvc.util;

import org.springframework.stereotype.Component;

@Component
public class ComparatorUtil {

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

}
