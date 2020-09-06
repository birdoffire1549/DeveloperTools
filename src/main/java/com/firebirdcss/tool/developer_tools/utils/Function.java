/**
 * Copyright 2020 by FirebirdCSS
 */
package com.firebirdcss.tool.developer_tools.utils;

/**
 * @author Scott Griffis
 * <p>
 * Date: 09/06/2020
 *
 */
public class Function {
    public static String multiLineToSingleLine(String string) {
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
}
