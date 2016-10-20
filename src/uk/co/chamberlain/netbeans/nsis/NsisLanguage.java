/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.chamberlain.netbeans.nsis;

import org.netbeans.api.lexer.Language;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.LanguageRegistration;
import uk.co.chamberlain.netbeans.nsis.netbeans.lexer.NsisTokenId;

@LanguageRegistration(mimeType = "text/x-nsi")
public class NsisLanguage extends DefaultLanguageConfig {

    @Override
    public Language getLexerLanguage() {
        return NsisTokenId.getLanguage();
    }

    @Override
    public String getDisplayName() {
        return "NSIS";
    }

}
