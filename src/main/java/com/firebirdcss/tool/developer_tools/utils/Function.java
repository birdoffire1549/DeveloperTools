/**
 * Copyright 2020 by FirebirdCSS
 */
package com.firebirdcss.tool.developer_tools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author Scott Griffis
 * <p>
 * Date: 09/06/2020
 *
 */
public class Function {
    public static String multiLineToSingleLine(String string) {
        if (string != null) {
            StringBuilder result = new StringBuilder(string);
            boolean wasLineBreak = false;
            for (int i = 0; i < result.length(); i++) {
                switch (result.charAt(i)) {
                    case '\n':
                    case '\r':
                        result.deleteCharAt(i--);
                        wasLineBreak = true;
                        break;
                    default:
                        if (wasLineBreak) {
                            wasLineBreak = false;
                            result.insert(i, ' ');
                        }
                        break;
                }
            }
            
            return result.toString();
        }
        
        return null;
    }
    
    public static String unescapeJavaString(String string) {
        if (string != null) {
            StringBuilder result = new StringBuilder(string);
            if (string.startsWith("\"") && string.endsWith("\"") && string.length() > 1) {
                result.deleteCharAt(0);
                result.deleteCharAt(result.length() - 1);
            }
            
            boolean prevSlash = false;
            for (int i = 0; i < result.length(); i++) {
                if (prevSlash) {
                    prevSlash = false;
                    switch (result.charAt(i)) {
                        case 'b':
                        case 'B':
                            result.replace(i, (i + 1), "\b");
                            break;
                        case 'n':
                        case 'N':
                            result.replace(i, (i + 1), "\n");
                            break;
                        case 't':
                        case 'T':
                            result.replace(i, (i + 1), "\t");
                            break;
                        case 'r':
                        case 'R':
                            result.replace(i, (i + 1), "\r");
                            break;
                        case 'f':
                        case 'F':
                            result.replace(i, (i + 1), "\f");
                            break;
                        default:
                            // Do nothing the char falls through...
                            break;
                    }
                } else if (result.charAt(i) == '\\') {
                    prevSlash = true;
                    result.deleteCharAt(i--);
                }
            }
            
            return result.toString();
        }
        
        return null;
    }
    
    public static String makeJsonPretty(String string) {
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(string).getAsJsonObject();
        
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String prettyString = gson.toJson(jObj);
        
        return prettyString;
    }
}
