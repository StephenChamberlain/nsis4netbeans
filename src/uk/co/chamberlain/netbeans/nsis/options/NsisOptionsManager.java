/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.chamberlain.netbeans.nsis.options;

import org.openide.util.NbPreferences;

/**
 *
 * @author Stephen Chamberlain
 */
public class NsisOptionsManager {

    public static String getNsisHome() {
        return NbPreferences.forModule(NsisPanel.class).get("nsis.home", "");
    }
    
    public static void setNsisHome(final String nsisHome) {
        NbPreferences.forModule(NsisPanel.class).put("nsis.home", nsisHome);
    }    
}
