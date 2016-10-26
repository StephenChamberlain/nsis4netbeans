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
package uk.co.chamberlain.netbeans.nsis.options;

import org.openide.util.NbPreferences;

public class NsisOptionsManager {

    private static final String NSIS_HOME = "nsis.home";    
    private static final String NSIS_HOME_DEFAULT = "";
    private static final String NSIS_VERBOSITY = "nsis.verbosity";
    private static final int NSIS_VERBOSITY_DEFAULT = 2;

    public static String getNsisHome() {
        return NbPreferences.forModule(NsisPanel.class).get(NSIS_HOME, NSIS_HOME_DEFAULT);
    }

    public static void setNsisHome(final String nsisHome) {
        NbPreferences.forModule(NsisPanel.class).put(NSIS_HOME, nsisHome);
    }
    
    public static int getNsisVerbosity() {
        return NbPreferences.forModule(NsisPanel.class).getInt(NSIS_VERBOSITY, NSIS_VERBOSITY_DEFAULT);
    }

    public static void setNsisVerbosity(final int nsisVerbosity) {
        NbPreferences.forModule(NsisPanel.class).putInt(NSIS_VERBOSITY, nsisVerbosity);
    }    
}
