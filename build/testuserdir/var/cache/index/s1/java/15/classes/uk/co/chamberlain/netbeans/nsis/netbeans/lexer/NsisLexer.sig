����   3 ]
  3	  4 5
 6 7
  8 9
  :	  ;
  <
 = >
 6 ?	 ( @
 A B
 C D E F G info )Lorg/netbeans/spi/lexer/LexerRestartInfo; 	Signature gLorg/netbeans/spi/lexer/LexerRestartInfo<Luk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisTokenId;>; nsisParserTokenManager ELuk/co/chamberlain/netbeans/nsis/javacc/lexer/NSISParserTokenManager; <init> ,(Lorg/netbeans/spi/lexer/LexerRestartInfo;)V Code LineNumberTable LocalVariableTable this :Luk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisLexer; stream =Luk/co/chamberlain/netbeans/nsis/javacc/lexer/JavaCharStream; LocalVariableTypeTable j(Lorg/netbeans/spi/lexer/LexerRestartInfo<Luk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisTokenId;>;)V 	nextToken  ()Lorg/netbeans/api/lexer/Token; token 4Luk/co/chamberlain/netbeans/nsis/javacc/lexer/Token; StackMapTable H ^()Lorg/netbeans/api/lexer/Token<Luk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisTokenId;>; #org.netbeans.SourceLevelAnnotations Ljava/lang/Override; state ()Ljava/lang/Object; release ()V nLjava/lang/Object;Lorg/netbeans/spi/lexer/Lexer<Luk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisTokenId;>; 
SourceFile NsisLexer.java  /   ;uk/co/chamberlain/netbeans/nsis/javacc/lexer/JavaCharStream I J K  L Cuk/co/chamberlain/netbeans/nsis/javacc/lexer/NSISParserTokenManager  M   N O P Q R S T U V W X Y Z [ \ 8uk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisLexer java/lang/Object org/netbeans/spi/lexer/Lexer 2uk/co/chamberlain/netbeans/nsis/javacc/lexer/Token 'org/netbeans/spi/lexer/LexerRestartInfo input %()Lorg/netbeans/spi/lexer/LexerInput; &(Lorg/netbeans/spi/lexer/LexerInput;)V @(Luk/co/chamberlain/netbeans/nsis/javacc/lexer/JavaCharStream;)V getNextToken 6()Luk/co/chamberlain/netbeans/nsis/javacc/lexer/Token; !org/netbeans/spi/lexer/LexerInput 
readLength ()I tokenFactory '()Lorg/netbeans/spi/lexer/TokenFactory; kind I Duk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisLanguageHierarchy getToken ?(I)Luk/co/chamberlain/netbeans/nsis/netbeans/lexer/NsisTokenId; #org/netbeans/spi/lexer/TokenFactory createToken @(Lorg/netbeans/api/lexer/TokenId;)Lorg/netbeans/api/lexer/Token;                               �     "*� *+� � Y+� � M*� Y,� � �              	    !          "       "          !       "        "  # $     x     **� � 	L*� � � 
� �*� � +� � � �                         *      " % &  '    �  (     ) *     +    , -     ,     �           $             *     +    . /     +      �           )             *     +        0 1    2