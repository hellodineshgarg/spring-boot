package com.developer.surena.util.language;

import java.util.Enumeration;
import java.util.ResourceBundle;


public class LanguageUtil extends ResourceBundle{

    static ResourceBundle bundle;
    protected static final String BUNDLE_NAME = "language";
    protected static final Control UTF8_CONTROL = new UTF8Control();

    public LanguageUtil() {
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, UTF8_CONTROL));
    }

    static {
        bundle = ResourceBundle.getBundle(BUNDLE_NAME,UTF8_CONTROL);
    }

    public static String get(String key){
        return bundle.getString(key);
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

    public static void main(String[] args) {
        //ResourceBundle r = LanguageUtil.getBundle();

        System.out.println(LanguageUtil.get("first_name"));

    }
}
