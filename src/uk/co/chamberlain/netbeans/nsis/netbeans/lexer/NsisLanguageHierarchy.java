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

import java.util.*;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

public class NsisLanguageHierarchy extends LanguageHierarchy<NsisTokenId> {

    private static List<NsisTokenId> tokens;
    private static Map<Integer, NsisTokenId> idToToken;

    private static void init() {        
        tokens = Arrays.asList(new NsisTokenId[]{
            new NsisTokenId("EOF", "whitespace", 0),
            new NsisTokenId("WHITESPACE", "whitespace", 1),
            new NsisTokenId("SINGLE_LINE_COMMENT", "comment", 4),
            new NsisTokenId("FORMAL_COMMENT", "comment", 5),
            new NsisTokenId("MULTI_LINE_COMMENT", "comment", 6),
            new NsisTokenId("ABSTRACT", "keyword", 8),
            new NsisTokenId("ASSERT", "keyword", 9),
            new NsisTokenId("BOOLEAN", "keyword", 10),
            new NsisTokenId("BREAK", "keyword", 11),
            new NsisTokenId("BYTE", "keyword", 12),
            new NsisTokenId("CASE", "keyword", 13),
            new NsisTokenId("CATCH", "keyword", 14),
            new NsisTokenId("CHAR", "keyword", 15),
            new NsisTokenId("CLASS", "keyword", 16),
            new NsisTokenId("CONST", "keyword", 17),
            new NsisTokenId("CONTINUE", "keyword", 18),
            new NsisTokenId("_DEFAULT", "keyword", 19),
            new NsisTokenId("DO", "keyword", 20),
            new NsisTokenId("DOUBLE", "keyword", 21),
            new NsisTokenId("ELSE", "keyword", 22),
            new NsisTokenId("ENUM", "keyword", 23),
            new NsisTokenId("EXTENDS", "keyword", 24),
            new NsisTokenId("FALSE", "keyword", 25),
            new NsisTokenId("FINAL", "keyword", 26),
            new NsisTokenId("FINALLY", "keyword", 27),
            new NsisTokenId("FLOAT", "keyword", 28),
            new NsisTokenId("FOR", "keyword", 29),
            new NsisTokenId("GOTO", "keyword", 30),
            new NsisTokenId("IF", "keyword", 31),
            new NsisTokenId("IMPLEMENTS", "keyword", 32),
            new NsisTokenId("IMPORT", "keyword", 33),
            new NsisTokenId("INSTANCEOF", "keyword", 34),
            new NsisTokenId("INT", "keyword", 35),
            new NsisTokenId("INTERFACE", "keyword", 36),
            new NsisTokenId("LONG", "keyword", 37),
            new NsisTokenId("NAME", "keyword", 38),
            new NsisTokenId("NEW", "keyword", 39),
            new NsisTokenId("NULL", "keyword", 40),
            new NsisTokenId("PACKAGE", "keyword", 41),
            new NsisTokenId("PRIVATE", "keyword", 42),
            new NsisTokenId("PROTECTED", "keyword", 43),
            new NsisTokenId("PUBLIC", "keyword", 44),
            new NsisTokenId("RETURN", "keyword", 45),
            new NsisTokenId("SHORT", "keyword", 46),
            new NsisTokenId("STATIC", "keyword", 47),
            new NsisTokenId("STRICTFP", "keyword", 48),
            new NsisTokenId("SUPER", "keyword", 49),
            new NsisTokenId("SWITCH", "keyword", 50),
            new NsisTokenId("SYNCHRONIZED", "keyword", 51),
            new NsisTokenId("THIS", "keyword", 52),
            new NsisTokenId("THROW", "keyword", 53),
            new NsisTokenId("THROWS", "keyword", 54),
            new NsisTokenId("TRANSIENT", "keyword", 55),
            new NsisTokenId("TRUE", "keyword", 56),
            new NsisTokenId("TRY", "keyword", 57),
            new NsisTokenId("VOID", "keyword", 58),
            new NsisTokenId("VOLATILE", "keyword", 59),
            new NsisTokenId("WHILE", "keyword", 60),
            new NsisTokenId("INTEGER_LITERAL", "literal", 61),
            new NsisTokenId("DECIMAL_LITERAL", "literal", 62),
            new NsisTokenId("HEX_LITERAL", "literal", 63),
            new NsisTokenId("OCTAL_LITERAL", "literal", 64),
            new NsisTokenId("FLOATING_POINT_LITERAL", "literal", 65),
            new NsisTokenId("DECIMAL_FLOATING_POINT_LITERAL", "literal", 66),
            new NsisTokenId("DECIMAL_EXPONENT", "number", 67),
            new NsisTokenId("HEXADECIMAL_FLOATING_POINT_LITERAL", "literal", 68),
            new NsisTokenId("HEXADECIMAL_EXPONENT", "number", 69),
            new NsisTokenId("CHARACTER_LITERAL", "literal", 70),
            new NsisTokenId("STRING_LITERAL", "literal", 71),
            new NsisTokenId("IDENTIFIER", "identifier", 72),
            new NsisTokenId("LETTER", "literal", 73),
            new NsisTokenId("PART_LETTER", "literal", 74),
            new NsisTokenId("LPAREN", "operator", 75),
            new NsisTokenId("RPAREN", "operator", 76),
            new NsisTokenId("LBRACE", "operator", 77),
            new NsisTokenId("RBRACE", "operator", 78),
            new NsisTokenId("LBRACKET", "operator", 79),
            new NsisTokenId("RBRACKET", "operator", 80),
            new NsisTokenId("SEMICOLON", "operator", 81),
            new NsisTokenId("COMMA", "operator", 82),
            new NsisTokenId("DOT", "operator", 83),
            new NsisTokenId("AT", "operator", 84),
            new NsisTokenId("ASSIGN", "operator", 85),
            new NsisTokenId("LT", "operator", 86),
            new NsisTokenId("BANG", "operator", 87),
            new NsisTokenId("TILDE", "operator", 88),
            new NsisTokenId("HOOK", "operator", 89),
            new NsisTokenId("COLON", "operator", 90),
            new NsisTokenId("EQ", "operator", 91),
            new NsisTokenId("LE", "operator", 92),
            new NsisTokenId("GE", "operator", 93),
            new NsisTokenId("NE", "operator", 94),
            new NsisTokenId("SC_OR", "operator", 95),
            new NsisTokenId("SC_AND", "operator", 96),
            new NsisTokenId("INCR", "operator", 97),
            new NsisTokenId("DECR", "operator", 98),
            new NsisTokenId("PLUS", "operator", 99),
            new NsisTokenId("MINUS", "operator", 100),
            new NsisTokenId("STAR", "operator", 101),
            new NsisTokenId("SLASH", "operator", 102),
            new NsisTokenId("BIT_AND", "operator", 103),
            new NsisTokenId("BIT_OR", "operator", 104),
            new NsisTokenId("XOR", "operator", 105),
            new NsisTokenId("REM", "operator", 106),
            new NsisTokenId("LSHIFT", "operator", 107),
            new NsisTokenId("PLUSASSIGN", "operator", 108),
            new NsisTokenId("MINUSASSIGN", "operator", 109),
            new NsisTokenId("STARASSIGN", "operator", 110),
            new NsisTokenId("SLASHASSIGN", "operator", 111),
            new NsisTokenId("ANDASSIGN", "operator", 112),
            new NsisTokenId("ORASSIGN", "operator", 113),
            new NsisTokenId("XORASSIGN", "operator", 114),
            new NsisTokenId("REMASSIGN", "operator", 115),
            new NsisTokenId("LSHIFTASSIGN", "operator", 116),
            new NsisTokenId("RSIGNEDSHIFTASSIGN", "operator", 117),
            new NsisTokenId("RUNSIGNEDSHIFTASSIGN", "operator", 118),
            new NsisTokenId("ELLIPSIS", "operator", 119),
            new NsisTokenId("RUNSIGNEDSHIFT", "operator", 120),
            new NsisTokenId("RSIGNEDSHIFT", "operator", 121),
            new NsisTokenId("GT", "operator", 122)
        });
        idToToken = new HashMap<Integer, NsisTokenId>();
        for (NsisTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    static synchronized NsisTokenId getToken(int id) {
        if (idToToken == null) {
            init();
        }
        return idToToken.get(id);
    }

    @Override
    protected synchronized Collection<NsisTokenId> createTokenIds() {
        if (tokens == null) {
            init();
        }
        return tokens;
    }

    @Override
    protected synchronized Lexer<NsisTokenId> createLexer(LexerRestartInfo<NsisTokenId> info) {
        return new NsisLexer(info);
    }

    @Override
    protected String mimeType() {
        return "text/x-nsi";
    }

}
