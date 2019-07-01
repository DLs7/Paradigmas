Professora,

Queria constar que foi uma mão compilar isso daqui no meu computador.
Não usei Maven nem Gradle porque eu não conseguia instalar e usar direito.
Baixei 3 bibliotecas: apache.commons.io, apache.commons.lang3 e com.opencsv.

Eu consegui fazer elas rodarem pelo terminal setando o CLASSPATH do meu sistema
com as bibliotecas da apache.

Compilei meu programa de um jeito bem específico:
javac -cp ".;commons-io.jar;commons-lang.jar;opencsv.jar;" *.java
java -cp ".;commons-io.jar;commons-lang.jar;opencsv.jar;" EnadeUFSMExplorer

Pelo o que eu li, no Linux se usa ':' ao invés de ';' na hora de compilar.

Se eu for upar alguma coisa nova no git serão os extras.
Pelo amor de Deus, se você não conseguir rodar, me chama que eu te mostro. 
Peço perdão por ser um WindowsPleb, grato pela compreensão.