package com.cms.utils;

import java.util.Arrays;
import java.util.ResourceBundle;

public class AccentRemover {
            public static String toUrlFriendly(String s) {
                    int maxLength = Math.min(s.length(), 236);
                    char[] buffer = new char[maxLength];
                    int n = 0;
                    for (int i = 0; i < maxLength; i++) {
                            char ch = s.charAt(i);
                            buffer[n] = removeAccent(ch);
                            // skip not printable characters
                            if (buffer[n] > 31) {
                                    n++;
                            }
                    }
                    // skip trailing slashes
                    while (n > 0 && buffer[n - 1] == '/') {
                            n--;
                    }
                    return String.valueOf(buffer, 0, n);
            }

            public static char removeAccent(char ch) {
                ResourceBundle rs = ResourceBundle.getBundle("com.cms.application");
                String[] SPECIAL_CHARACTERS = rs.getString("SPECIAL_CHARACTERS").split(",");                
                String[] REPLACEMENTS= rs.getString("REPLACEMENTS").split(",");
                char[] s = new char[1];
                s[0]=ch;
                String temp = new String(s);
                int index = Arrays.binarySearch(SPECIAL_CHARACTERS,temp);
                if (index >= 0) {
                        ch = REPLACEMENTS[index].charAt(0);
                }
                return ch;
            }
            
            public static String removeAccent(String s) {                
                    StringBuilder sb = new StringBuilder(s);
                    for (int i = 0; i < sb.length(); i++) {
                            sb.setCharAt(i, removeAccent(sb.charAt(i)));
                    }
                    return sb.toString();
            }	
}
