package com.developer.surena.util.language;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by MR on 9/11/2017.
 */
public class UTF8Control extends ResourceBundle.Control {
    protected static final String CHARSET = "UTF-8";
    protected static final String BUNDLE_EXTENSION = "properties";

    public ResourceBundle newBundle
            (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {
        // The below code is copied from default Control#newBundle() implementation.
        // Only the PropertyResourceBundle line is changed to read the file as UTF-8.
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, CHARSET));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }
}