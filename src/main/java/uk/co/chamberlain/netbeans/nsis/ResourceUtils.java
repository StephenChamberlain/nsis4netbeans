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

    public static InputStream getNsisDefsLiterals() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-literals.txt");
    }

    public static InputStream getNsisDefsComments() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-comments.txt");
    }

    public static InputStream getNsisDefsOperators() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-operators.txt");
    }

    public static InputStream getNsisDefsNumbers() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-numbers.txt");
    }

    public static InputStream getNsisDefsIdentifiers() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-identifiers.txt");
    }

    public static InputStream getNsisDefsFunctions() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-functions.txt");
    }

    public static InputStream getNsisDefsSections() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-sections.txt");
    }    
    
    public static InputStream getNsisDefsPlugins() {
        return getResourceAsInputStream(
                NSIS_DEFS_RESOURCE_BASE + "nsis-defs-plugins.txt");
    }    
    
    private static InputStream getResourceAsInputStream(final String resource) {
        return Lookup.getDefault().lookup(ClassLoader.class).getResourceAsStream(resource);
    }
}
