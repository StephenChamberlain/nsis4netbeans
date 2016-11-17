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

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.openide.util.Exceptions;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.NSISParserConstants;

public class NsisLanguageHierarchy extends LanguageHierarchy<NsisTokenId> {

    private static final Logger LOGGER = Logger.getLogger(NsisLanguageHierarchy.class.getName());

    private static final NSISParserConstants PARSER_CONSTANTS = new ParserConstants();

    private static final String KEYWORD = "keyword";
    private static final String LITERAL = "literal";
    private static final String COMMENT = "comment";
    private static final String WHITESPACE = "whitespace";
    private static final String NUMBER = "number";
    private static final String IDENTIFIER = "identifier";
    private static final String OPERATOR = "";

    private static List<String> whitespace;
    private static List<String> literals;
    private static List<String> comments;
    private static List<NsisTokenId> tokens;
    private static Map<Integer, NsisTokenId> idToToken;

    private static void init() {

        whitespace = Arrays.asList(
                "EOF",
                "WHITESPACE"
        );

        literals = Arrays.asList(
                "INTEGER_LITERAL",
                "DECIMAL_LITERAL",
                "HEX_LITERAL",
                "OCTAL_LITERAL",
                "FLOATING_POINT_LITERAL",
                "DECIMAL_FLOATING_POINT_LITERAL",
                "DECIMAL_EXPONENT",
                "HEXADECIMAL_FLOATING_POINT_LITERAL",
                "CHARACTER_LITERAL",
                "STRING_LITERAL",
                "LETTER",
                "PART_LETTER"
        );

        comments = Arrays.asList(
                "SINGLE_LINE_COMMENT",
                "FORMAL_COMMENT",
                "MULTI_LINE_COMMENT"
        );

        tokens = new ArrayList<>();

        for (final Field field : NSISParserConstants.class
                .getFields()) {
            try {
                if (field.getType() == Integer.TYPE) {
                    if (whitespace.contains(field.getName())) {
                        addTokenId(field, WHITESPACE);

                    } else if (literals.contains(field.getName())) {
                        addTokenId(field, LITERAL);                        
                        
                    } else if (comments.contains(field.getName())) {
                        addTokenId(field, COMMENT);

                    } else {
                        addTokenId(field, KEYWORD);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

//        tokens = Arrays.asList(new NsisTokenId[]{
//            new NsisTokenId("EOF", WHITESPACE, 0),
//            new NsisTokenId("WHITESPACE", WHITESPACE, 1),
//            new NsisTokenId("SINGLE_LINE_COMMENT", COMMENT, 4),
//            new NsisTokenId("FORMAL_COMMENT", COMMENT, 5),
//            new NsisTokenId("MULTI_LINE_COMMENT", COMMENT, 6),
//            new NsisTokenId("ABSTRACT", KEYWORD, 8),
//            new NsisTokenId("ASSERT", KEYWORD, 9),
//            new NsisTokenId("BOOLEAN", KEYWORD, 10),
//            new NsisTokenId("BREAK", KEYWORD, 11),
//            new NsisTokenId("BYTE", KEYWORD, 12),
//            new NsisTokenId("CASE", KEYWORD, 13),
//            new NsisTokenId("CATCH", KEYWORD, 14),
//            new NsisTokenId("CHAR", KEYWORD, 15),
//            new NsisTokenId("CLASS", KEYWORD, 16),
//            new NsisTokenId("CONST", KEYWORD, 17),
//            new NsisTokenId("CONTINUE", KEYWORD, 18),
//            new NsisTokenId("_DEFAULT", KEYWORD, 19),
//            new NsisTokenId("DO", KEYWORD, 20),
//            new NsisTokenId("DOUBLE", KEYWORD, 21),
//            new NsisTokenId("ELSE", KEYWORD, 22),
//            new NsisTokenId("ENUM", KEYWORD, 23),
//            new NsisTokenId("EXTENDS", KEYWORD, 24),
//            new NsisTokenId("FALSE", KEYWORD, 25),
//            new NsisTokenId("FINAL", KEYWORD, 26),
//            new NsisTokenId("FINALLY", KEYWORD, 27),
//            new NsisTokenId("FLOAT", KEYWORD, 28),
//            new NsisTokenId("FOR", KEYWORD, 29),
//            new NsisTokenId("GOTO", KEYWORD, 30),
//            new NsisTokenId("IF", KEYWORD, 31),
//            new NsisTokenId("IMPLEMENTS", KEYWORD, 32),
//            new NsisTokenId("IMPORT", KEYWORD, 33),
//            new NsisTokenId("INSTANCEOF", KEYWORD, 34),
//            new NsisTokenId("INT", KEYWORD, 35),
//            new NsisTokenId("INTERFACE", KEYWORD, 36),
//            new NsisTokenId("LONG", KEYWORD, 37),
//            new NsisTokenId("NAME", KEYWORD, 38),
//            new NsisTokenId("NEW", KEYWORD, 39),
//            new NsisTokenId("NULL", KEYWORD, 40),
//            new NsisTokenId("PACKAGE", KEYWORD, 41),
//            new NsisTokenId("PRIVATE", KEYWORD, 42),
//            new NsisTokenId("PROTECTED", KEYWORD, 43),
//            new NsisTokenId("PUBLIC", KEYWORD, 44),
//            new NsisTokenId("RETURN", KEYWORD, 45),
//            new NsisTokenId("SHORT", KEYWORD, 46),
//            new NsisTokenId("STATIC", KEYWORD, 47),
//            new NsisTokenId("STRICTFP", KEYWORD, 48),
//            new NsisTokenId("SUPER", KEYWORD, 49),
//            new NsisTokenId("SWITCH", KEYWORD, 50),
//            new NsisTokenId("SYNCHRONIZED", KEYWORD, 51),
//            new NsisTokenId("THIS", KEYWORD, 52),
//            new NsisTokenId("THROW", KEYWORD, 53),
//            new NsisTokenId("THROWS", KEYWORD, 54),
//            new NsisTokenId("TRANSIENT", KEYWORD, 55),
//            new NsisTokenId("TRUE", KEYWORD, 56),
//            new NsisTokenId("TRY", KEYWORD, 57),
//            new NsisTokenId("VOID", KEYWORD, 58),
//            new NsisTokenId("VOLATILE", KEYWORD, 59),
//            new NsisTokenId("WHILE", KEYWORD, 60),
//            new NsisTokenId("INTEGER_LITERAL", LITERAL, 61),
//            new NsisTokenId("DECIMAL_LITERAL", LITERAL, 62),
//            new NsisTokenId("HEX_LITERAL", LITERAL, 63),
//            new NsisTokenId("OCTAL_LITERAL", LITERAL, 64),
//            new NsisTokenId("FLOATING_POINT_LITERAL", LITERAL, 65),
//            new NsisTokenId("DECIMAL_FLOATING_POINT_LITERAL", LITERAL, 66),
//            new NsisTokenId("DECIMAL_EXPONENT", NUMBER, 67),
//            new NsisTokenId("HEXADECIMAL_FLOATING_POINT_LITERAL", LITERAL, 68),
//            new NsisTokenId("HEXADECIMAL_EXPONENT", NUMBER, 69),
//            new NsisTokenId("CHARACTER_LITERAL", LITERAL, 70),
//            new NsisTokenId("STRING_LITERAL", LITERAL, 71),
//            new NsisTokenId("IDENTIFIER", IDENTIFIER, 72),
//            new NsisTokenId("LETTER", LITERAL, 73),
//            new NsisTokenId("PART_LETTER", LITERAL, 74),
//            new NsisTokenId("LPAREN", OPERATOR, 75),
//            new NsisTokenId("RPAREN", OPERATOR, 76),
//            new NsisTokenId("LBRACE", OPERATOR, 77),
//            new NsisTokenId("RBRACE", OPERATOR, 78),
//            new NsisTokenId("LBRACKET", OPERATOR, 79),
//            new NsisTokenId("RBRACKET", OPERATOR, 80),
//            new NsisTokenId("SEMICOLON", OPERATOR, 81),
//            new NsisTokenId("COMMA", OPERATOR, 82),
//            new NsisTokenId("DOT", OPERATOR, 83),
//            new NsisTokenId("AT", OPERATOR, 84),
//            new NsisTokenId("ASSIGN", OPERATOR, 85),
//            new NsisTokenId("LT", OPERATOR, 86),
//            new NsisTokenId("BANG", OPERATOR, 87),
//            new NsisTokenId("TILDE", OPERATOR, 88),
//            new NsisTokenId("HOOK", OPERATOR, 89),
//            new NsisTokenId("COLON", OPERATOR, 90),
//            new NsisTokenId("EQ", OPERATOR, 91),
//            new NsisTokenId("LE", OPERATOR, 92),
//            new NsisTokenId("GE", OPERATOR, 93),
//            new NsisTokenId("NE", OPERATOR, 94),
//            new NsisTokenId("SC_OR", OPERATOR, 95),
//            new NsisTokenId("SC_AND", OPERATOR, 96),
//            new NsisTokenId("INCR", OPERATOR, 97),
//            new NsisTokenId("DECR", OPERATOR, 98),
//            new NsisTokenId("PLUS", OPERATOR, 99),
//            new NsisTokenId("MINUS", OPERATOR, 100),
//            new NsisTokenId("STAR", OPERATOR, 101),
//            new NsisTokenId("SLASH", OPERATOR, 102),
//            new NsisTokenId("BIT_AND", OPERATOR, 103),
//            new NsisTokenId("BIT_OR", OPERATOR, 104),
//            new NsisTokenId("XOR", OPERATOR, 105),
//            new NsisTokenId("REM", OPERATOR, 106),
//            new NsisTokenId("LSHIFT", OPERATOR, 107),
//            new NsisTokenId("PLUSASSIGN", OPERATOR, 108),
//            new NsisTokenId("MINUSASSIGN", OPERATOR, 109),
//            new NsisTokenId("STARASSIGN", OPERATOR, 110),
//            new NsisTokenId("SLASHASSIGN", OPERATOR, 111),
//            new NsisTokenId("ANDASSIGN", OPERATOR, 112),
//            new NsisTokenId("ORASSIGN", OPERATOR, 113),
//            new NsisTokenId("XORASSIGN", OPERATOR, 114),
//            new NsisTokenId("REMASSIGN", OPERATOR, 115),
//            new NsisTokenId("LSHIFTASSIGN", OPERATOR, 116),
//            new NsisTokenId("RSIGNEDSHIFTASSIGN", OPERATOR, 117),
//            new NsisTokenId("RUNSIGNEDSHIFTASSIGN", OPERATOR, 118),
//            new NsisTokenId("ELLIPSIS", OPERATOR, 119),
//            new NsisTokenId("RUNSIGNEDSHIFT", OPERATOR, 120),
//            new NsisTokenId("RSIGNEDSHIFT", OPERATOR, 121),
//            new NsisTokenId("GT", OPERATOR, 122)
//        });
        idToToken = new HashMap<>();
        for (NsisTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    private static void addTokenId(final Field field, final String type)
            throws IllegalArgumentException, IllegalAccessException {

        NsisTokenId tokenId = new NsisTokenId(field.getName(), type, field.getInt(PARSER_CONSTANTS));
        for (NsisTokenId existingToken : tokens) {
            if (existingToken.ordinal() == tokenId.ordinal()) {
                LOGGER.log(
                        Level.WARNING,
                        "Duplicate ordinal; existing {0} duplicate {1}",
                        new Object[]{existingToken.name(), tokenId.name()});
                return;
            }
        }
        tokens.add(tokenId);
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

    private static class ParserConstants implements NSISParserConstants {
        // Object handle to use in reflection
    }

}
