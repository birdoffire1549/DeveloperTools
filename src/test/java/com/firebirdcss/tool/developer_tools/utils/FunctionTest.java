/**
 * Copyright 2020 by FirebirdCSS
 */
package com.firebirdcss.tool.developer_tools.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Scott Griffis
 * <p>
 * Date: 09/06/2020
 *
 */
class FunctionTest {
    
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }
    
    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }
    
    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }
    
    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }
    
    /**
     * Test method for {@link com.firebirdcss.tool.developer_tools.utils.Function#multiLineToSingleLine(java.lang.String)}.
     */
    @Test
    void testMultiLineToSingleLine() {
        String initialInput = "There is\na couple\rof words\n\rper line.";
        
        String expected = "There is a couple of words per line.";
        assertEquals(expected, Function.multiLineToSingleLine(initialInput));
    }
    
    @Test
    void testUnescapeJavaString() {
        String sample1 = "This is an\b string.\nList:\n\r\tStuff1\fblablabla\"quoted\"\nPath: \\usr\\path\\path";
    }
    
}
