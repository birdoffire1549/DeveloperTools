/**
 * Copyright 2020 by FirebirdCSS
 */
package com.firebirdcss.tool.developer_tools.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Scott Griffis
 * <p>
 * Date: 09/06/2020
 *
 */
class FunctionTest {
    @Test
    void testMultiLineToSingleLine() {
        String initialInput = "There is\na couple\rof words\n\rper line.";
        
        String expected = "There is a couple of words per line.";
        assertEquals(expected, Function.multiLineToSingleLine(initialInput));
    }
    
    @Test
    void testUnescapeJavaString() {
        String sample1 = "This is an\\b string.\\nList:\\n\\r\\tStuff1\\fblablabla\\\"quoted\\\"\nPath: \\\\usr\\\\path\\\\path";
        
        String expected1 = "This is an\b string.\nList:\n\r\tStuff1\fblablabla\"quoted\"\nPath: \\usr\\path\\path";
        assertEquals(expected1, Function.unescapeJavaString(sample1));
    }
    
    @Test
    void testMakeJsonPretty() {
        String sample1 = "{\"quiz\": {\"sport\": {\"q1\": {\"question\": \"Which one is correct team name in NBA?\",\"options\": [\"New York Bulls\",\"Los Angeles Kings\",\"Golden State Warriros\",\"Huston Rocket\"],\"answer\": \"Huston Rocket\"}},\"maths\": {\"q1\": {\"question\": \"5 + 7 = ?\",\"options\": [\"10\",\"11\",\"12\",\"13\"],\"answer\": \"12\"},\"q2\": {\"question\": \"12 - 8 = ?\",\"options\": [\"1\",\"2\",\"3\",\"4\"],\"answer\": \"4\"}}}}";
        
        String expected1 = "" +
                "{\n" + 
                "  \"quiz\": {\n" + 
                "    \"sport\": {\n" + 
                "      \"q1\": {\n" + 
                "        \"question\": \"Which one is correct team name in NBA?\",\n" + 
                "        \"options\": [\n" + 
                "          \"New York Bulls\",\n" + 
                "          \"Los Angeles Kings\",\n" + 
                "          \"Golden State Warriros\",\n" + 
                "          \"Huston Rocket\"\n" + 
                "        ],\n" + 
                "        \"answer\": \"Huston Rocket\"\n" + 
                "      }\n" + 
                "    },\n" + 
                "    \"maths\": {\n" + 
                "      \"q1\": {\n" + 
                "        \"question\": \"5 + 7 \\u003d ?\",\n" + 
                "        \"options\": [\n" + 
                "          \"10\",\n" + 
                "          \"11\",\n" + 
                "          \"12\",\n" + 
                "          \"13\"\n" + 
                "        ],\n" + 
                "        \"answer\": \"12\"\n" + 
                "      },\n" + 
                "      \"q2\": {\n" + 
                "        \"question\": \"12 - 8 \\u003d ?\",\n" + 
                "        \"options\": [\n" + 
                "          \"1\",\n" + 
                "          \"2\",\n" + 
                "          \"3\",\n" + 
                "          \"4\"\n" + 
                "        ],\n" + 
                "        \"answer\": \"4\"\n" + 
                "      }\n" + 
                "    }\n" + 
                "  }\n" + 
                "}";
        assertEquals(expected1, Function.makeJsonPretty(sample1));
    }
}
