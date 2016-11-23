/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.chamberlain.netbeans.nsis;

import java.io.InputStream;
import org.openide.util.Lookup;

/**
 *
 * @author Stephen Chamberlain
 */
public class ResourceUtils {

    private static final String NSIS_DEFS_RESOURCE_BASE = "uk/co/chamberlain/netbeans/nsis/netbeans/lexer/";

    public static InputStream getNsisDefsCommands() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-commands.txt");
    }

    public static InputStream getNsisDefsWhitespace() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-whitespace.txt");
    }

    private static InputStream getResourceAsInputStream(final String resource) {
        return Lookup.getDefault().lookup(ClassLoader.class).getResourceAsStream(resource);
    }
}
