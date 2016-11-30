/*
 * NSIS 4 NetBeans
 * Copyright (C) 2016 Stephen Chamberlain
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.chamberlain.netbeans.nsis;

import java.io.InputStream;
import org.openide.util.Lookup;

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
