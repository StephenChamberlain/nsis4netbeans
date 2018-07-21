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

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.openide.util.Exceptions;
import uk.co.chamberlain.netbeans.nsis.ResourceUtils;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.NSISParserConstants;

public class NsisLanguageHierarchy extends LanguageHierarchy<NsisTokenId> {

    private static final Logger LOGGER = Logger.getLogger(NsisLanguageHierarchy.class.getName());

    private static final NSISParserConstants PARSER_CONSTANTS = new ParserConstants();

    private static final String COMMAND = "command";
    private static final String KEYWORD = "keyword";
    private static final String LITERAL = "literal";
    private static final String COMMENT = "comment";
    private static final String WHITESPACE = "whitespace";
    private static final String NUMBER = "number";
    private static final String IDENTIFIER = "identifier";
    private static final String FUNCTION = "function";
    private static final String SECTION = "section";
    private static final String PLUGIN = "plugin";
    private static final String OPERATOR = "";

    private static List<String> commands;
    private static List<String> whitespace;
    private static List<String> literals;
    private static List<String> comments;
    private static List<String> operators;
    private static List<String> numbers;
    private static List<String> identifiers;
    private static List<String> functions;
    private static List<String> sections;
    private static List<String> plugins;
    private static List<NsisTokenId> tokens;
    private static Map<Integer, NsisTokenId> idToToken;
    private static NsisTokenId tokenForErrorSituation;

    private static void init() {
        commands = buildLanguageConstruct(ResourceUtils.getNsisDefsCommands());
        whitespace = buildLanguageConstruct(ResourceUtils.getNsisDefsWhitespace());
        literals = buildLanguageConstruct(ResourceUtils.getNsisDefsLiterals());
        comments = buildLanguageConstruct(ResourceUtils.getNsisDefsComments());
        operators = buildLanguageConstruct(ResourceUtils.getNsisDefsOperators());
        numbers = buildLanguageConstruct(ResourceUtils.getNsisDefsNumbers());
        identifiers = buildLanguageConstruct(ResourceUtils.getNsisDefsIdentifiers());
        functions = buildLanguageConstruct(ResourceUtils.getNsisDefsFunctions());
        sections = buildLanguageConstruct(ResourceUtils.getNsisDefsSections());
        plugins = buildLanguageConstruct(ResourceUtils.getNsisDefsPlugins());

        addFieldsToTokens();
    }

    private static List<String> buildLanguageConstruct(InputStream languageConstructStream) {
        List<String> languageConstruct = new ArrayList<>();
        try (Scanner languageConstructScanner = new Scanner(languageConstructStream)) {
            while (languageConstructScanner.hasNext()) {
                languageConstruct.add(languageConstructScanner.next());
            }
        }
        return languageConstruct;
    }

    private static void addFieldsToTokens() {
        tokens = new ArrayList<>();

        for (final Field field : NSISParserConstants.class.getFields()) {
            try {
                if (field.getType() == Integer.TYPE) {
                    if (commands.contains(field.getName())) {
                        addTokenId(field, COMMAND);

                    } else if (whitespace.contains(field.getName())) {
                        addTokenId(field, WHITESPACE);

                    } else if (literals.contains(field.getName())) {
                        addTokenId(field, LITERAL);

                    } else if (comments.contains(field.getName())) {
                        addTokenId(field, COMMENT);

                    } else if (operators.contains(field.getName())) {
                        addTokenId(field, OPERATOR);

                    } else if (numbers.contains(field.getName())) {
                        addTokenId(field, NUMBER);

                    } else if (identifiers.contains(field.getName())) {
                        addTokenId(field, IDENTIFIER);

                    } else if (functions.contains(field.getName())) {
                        addTokenId(field, FUNCTION);

                    } else if (sections.contains(field.getName())) {
                        addTokenId(field, SECTION);

                    } else if (plugins.contains(field.getName())) {
                        addTokenId(field, PLUGIN);

                    } else {
                        addTokenId(field, KEYWORD);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        idToToken = new HashMap<>();
        tokens.forEach((token) -> {
            idToToken.put(token.ordinal(), token);
        });
    }

    private static void addTokenId(final Field field, final String type)
            throws IllegalArgumentException, IllegalAccessException {

        final NsisTokenId tokenId = new NsisTokenId(field.getName(), type, field.getInt(PARSER_CONSTANTS));
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

        if ("WHITESPACE".equals(field.getName())) {
            tokenForErrorSituation = tokenId;
        }
    }

    static synchronized NsisTokenId getToken(final int id) {
        if (idToToken == null) {
            init();
        }
        return idToToken.get(id);
    }

    static synchronized NsisTokenId getTokenForErrorSituation() {
        return tokenForErrorSituation;
    }

    @Override
    protected synchronized Collection<NsisTokenId> createTokenIds() {
        if (tokens == null) {
            init();
        }
        return tokens;
    }

    @Override
    protected synchronized Lexer<NsisTokenId> createLexer(final LexerRestartInfo<NsisTokenId> info) {
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
