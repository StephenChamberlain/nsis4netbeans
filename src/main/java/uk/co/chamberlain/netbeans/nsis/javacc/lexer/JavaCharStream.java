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
package uk.co.chamberlain.netbeans.nsis.javacc.lexer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.netbeans.spi.lexer.LexerInput;

public class JavaCharStream {

    private LexerInput input;

    static boolean staticFlag;

    public JavaCharStream(final LexerInput input) {
        this.input = input;
    }

    JavaCharStream(final Reader stream, final int i, final int i0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    JavaCharStream(final InputStream stream, final String encoding, final int i, final int i0) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    char BeginToken() throws IOException {
        return readChar();
    }

    String GetImage() {
        return input.readText().toString();
    }

    public char[] GetSuffix(final int len) {
        if (len > input.readLength()) {
            throw new IllegalArgumentException();
        }
        return input.readText(input.readLength() - len, input.readLength()).toString().toCharArray();
    }

    void ReInit(final Reader stream, final int i, final int i0) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void ReInit(final InputStream stream, final String encoding, final int i, final int i0) throws UnsupportedEncodingException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void backup(final int i) {
        input.backup(i);
    }

    int getBeginColumn() {
        return 0;
    }

    int getBeginLine() {
        return 0;
    }

    int getEndColumn() {
        return 0;
    }

    int getEndLine() {
        return 0;
    }

    char readChar() throws IOException {
        int result = input.read();
        if (result == LexerInput.EOF) {
            throw new IOException("LexerInput EOF");
        }
        return (char) result;
    }

}
