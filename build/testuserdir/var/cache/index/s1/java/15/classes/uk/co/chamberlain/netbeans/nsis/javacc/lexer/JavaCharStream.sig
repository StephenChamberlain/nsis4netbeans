����   3 c
  A	  B C D
  E
  F
  G H I
  J K
 
 A
  L
 M N
  O
  P Q R S
  E T U input #Lorg/netbeans/spi/lexer/LexerInput; 
staticFlag Z <init> &(Lorg/netbeans/spi/lexer/LexerInput;)V Code LineNumberTable LocalVariableTable this =Luk/co/chamberlain/netbeans/nsis/javacc/lexer/JavaCharStream; (Ljava/io/Reader;II)V stream Ljava/io/Reader; i I i0 ,(Ljava/io/InputStream;Ljava/lang/String;II)V Ljava/io/InputStream; encoding Ljava/lang/String; 
Exceptions V 
BeginToken ()C GetImage ()Ljava/lang/String; 	GetSuffix (I)[C len StackMapTable ReInit backup (I)V getBeginColumn ()I getBeginLine getEndColumn 
getEndLine readChar result 
SourceFile JavaCharStream.java  W   'java/lang/UnsupportedOperationException Not yet implemented  X = . Y Z [ \ 0 ] 9 "java/lang/IllegalArgumentException Y ^ _ ` a 6 7 b 9 !org/netbeans/spi/lexer/LexerInput java/io/IOException LexerInput EOF ;uk/co/chamberlain/netbeans/nsis/javacc/lexer/JavaCharStream java/lang/Object $java/io/UnsupportedEncodingException ()V (Ljava/lang/String;)V readText ()Ljava/lang/CharSequence; java/lang/CharSequence toString 
readLength (II)Ljava/lang/CharSequence; java/lang/String toCharArray ()[C read !                        F     
*� *+� �              	         
        
       !     Z     *� � Y� �       
          *             " #     $ %     & %     '     d     *� � Y� �       
          4             " (     ) *     $ %     & %  +     ,   - .     /     *� �                         +        / 0     7     *� � �  �                           1 2     x     3*� � 	� � 
Y� �*� *� � 	d*� � 	� �  � �           $  %  '        3        3 3 %  4       5 !     R     
� Y� �           +    *    
        
 " #    
 $ %    
 & %    5 '     \     
� Y� �           /    4    
        
 " (    
 ) *    
 $ %    
 & %  +     ,   6 7     A     	*� � �       
    3  4        	        	 $ %    8 9     ,     �           7                : 9     ,     �           ;                ; 9     ,     �           ?                < 9     ,     �           C                = .     f     *� � <� � Y� ���           G  H  I  K                > %  4    �  +       ?    @