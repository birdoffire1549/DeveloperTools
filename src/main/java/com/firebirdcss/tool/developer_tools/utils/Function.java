/**
 * Copyright 2020 by FirebirdCSS
 */
package com.firebirdcss.tool.developer_tools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * This class is a utility method class which contains methods to perform the
 * main functionality of this application.
 * 
 * @author Scott Griffis
 * <p>
 * Date: 09/06/2020
 *
 */
public class Function {
    /**
     * This method converts a multi-line string to a single line string.
     * Line breaks are rendered as a single space.
     * 
     * @param string - The given string as {@link String}
     * @return Returns the converted string as {@link String}
     */
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
    
    /**
     * UnEscape the contents of the given string from a Java string perspective.
     * 
     * @param string - The given string as {@link String}
     * @return Returns the escaped string as {@link String}
     */
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
    
    /**
     * This method is intended to be given a JSON String and then that JSON is rendered
     * into a more visually appealing format.
     * 
     * @param json - The JSON string to be formatted as {@link String}
     * @return Returns the formatted string as {@link String}
     */
    public static String makeJsonPretty(String json) {
        JsonParser parser = new JsonParser();
        JsonObject jObj = parser.parse(json).getAsJsonObject();
        
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
        String prettyString = gson.toJson(jObj);
        
        return prettyString;
    }
}
