/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.chamberlain.netbeans.nsis.netbeans.lexer;

import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.JavaCharStream;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.Token;
import uk.co.chamberlain.netbeans.nsis.lexer.NSISParserTokenManager;

class NsisLexer implements Lexer<NsisTokenId> {

    private LexerRestartInfo<NsisTokenId> info;
    private NSISParserTokenManager nsisParserTokenManager;

    NsisLexer(LexerRestartInfo<NsisTokenId> info) {
        this.info = info;
        JavaCharStream stream = new JavaCharStream(info.input());
        nsisParserTokenManager = new NSISParserTokenManager(stream);
    }

    @Override
    public org.netbeans.api.lexer.Token<NsisTokenId> nextToken() {
        Token token = nsisParserTokenManager.getNextToken();
        if (info.input().readLength() < 1) {
            return null;
        }
        return info.tokenFactory().createToken(NsisLanguageHierarchy.getToken(token.kind));
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }

}
