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
package uk.co.chamberlain.netbeans.nsis.netbeans.lexer;

import java.util.logging.Logger;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.JavaCharStream;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.NSISParserTokenManager;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.Token;

class NsisLexer implements Lexer<NsisTokenId> {

    private static final Logger LOGGER = Logger.getLogger(NsisLexer.class.getName());
    
    private final LexerRestartInfo<NsisTokenId> info;
    private final NSISParserTokenManager nsisParserTokenManager;

    NsisLexer(LexerRestartInfo<NsisTokenId> info) {
        this.info = info;
        JavaCharStream stream = new JavaCharStream(info.input());
        nsisParserTokenManager = new NSISParserTokenManager(stream);
    }

    @Override
    public org.netbeans.api.lexer.Token<NsisTokenId> nextToken() {
        try {
            Token token = nsisParserTokenManager.getNextToken();
            if (info.input().readLength() < 1) {
                return null;
            }
            return info.tokenFactory().createToken(NsisLanguageHierarchy.getToken(token.kind));
        } catch (Throwable t) {
            LOGGER.severe(t.getMessage());
            return info.tokenFactory().createToken(NsisLanguageHierarchy.getTokenForErrorSituation());
        }
    }

    @Override
    public Object state() {
        return null;
    }

    @Override
    public void release() {
    }

}
