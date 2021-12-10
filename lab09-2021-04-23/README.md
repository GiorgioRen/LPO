# Laboratorio di LPO, 23 aprile 2020: parser ricorsivi top-down

## Scopo del laboratorio
Questo è il primo dei laboratori propedeutici al progetto finale.
Svilupperemo assieme l'interprete di un semplice linguaggio di programmazione passando
attraverso le tre fasi illustrate durante le lezioni del primo semestre:
1. analisi lessicale e sintattica: sviluppo di un tokenizer, di un parser e implementazione dell'albero della sintassi astratta (AST);
2. semantica statica: sviluppo di un typechecker per il controllo dei tipi mediante una visita dell'AST;
3. semantica dinamica: sviluppo di un interprete (ossia, di una macchina virtuale) in grado di valutare le espressioni ed eseguire le istruzioni
del linguaggio mediante un altro tipo di visita dell'AST, diverso da quella implementato per il controllo dei tipi.

Il progetto finale che dovrete sviluppare consisterà nell'estensione del linguaggio implementato in questi ultimi laboratori. Le soluzioni proposte
per questi laboratori saranno quindi un buon punto di partenza per l'implementazione del vostro progetto.

In questo laboratorio ci occuperemo del punto 1 indicato sopra. Come vedremo, sviluppare un tokenizer, un parser e fornire un'implementazione completa del
tipo AST non è semplice, anche per un linguaggio giocattolo. La soluzione proposta per questo laboratorio consiste di più di 700 SLOC (source lines of code, quindi escludendo commenti e linee vuote) distribuite su 39 file diversi! Ovviamente, buona parte del codice sarà già presente nel repository, voi dovrete 'solamente'
- comprendere il funzionamento del codice già presente;
- completare alcune classi; i dettagli si trovano in fondo a questo README.

Per portare a termine il vostro compito con **successo** sarà necessario il ripasso di alcuni argomenti, in particolare vi consiglio di rivedere il seguente materiale disponibile su AulaWeb:
- [lezione 09-23: Elementi di un linguaggio di programmazione. Stringhe. Linguaggi formali](https://2020.aulaweb.unige.it/mod/resource/view.php?id=27016)
- [lezione 09-24: Espressioni regolari](https://2020.aulaweb.unige.it/mod/resource/view.php?id=27968)
- [lezione 09-29: Tokenizer e parser. Grammatiche context-free](https://2020.aulaweb.unige.it/mod/resource/view.php?id=32125)
- [lezione 09-30: Alberi di derivazione. Sintassi e grammatiche ambigue](https://2020.aulaweb.unige.it/mod/resource/view.php?id=33131)
- [lezione 10-01: Disambiguazione di grammatiche](https://2020.aulaweb.unige.it/mod/resource/view.php?id=33750)
- [lezione 12-02: Stringhe ed espressioni regolari in Java](https://2020.aulaweb.unige.it/mod/resource/view.php?id=63569)
- [laboratorio 12-10. Java: espressioni regolari](https://2020.aulaweb.unige.it/mod/page/view.php?id=66441) e [soluzione proposta](https://2020.aulaweb.unige.it/mod/resource/view.php?id=69031)
- [lezione 04-16: Parser top-down ricorsivi. Tipi enum in Java](https://2020.aulaweb.unige.it/mod/resource/view.php?id=105521)

### Specifica della sintassi del linguaggio
La grammatica EBNF del linguaggio si trova all'inizio del file `lab_09_04_23.parser.BufferedParser` sotto forma di commento ed è già in forma non
ambigua;  è una guida indispensabile per lo sviluppo del codice della classe
`lab_09_04_23.parser.BufferedParser`.
```txt
Prog ::= StmtSeq EOF
StmtSeq ::= Stmt (';' StmtSeq)?
Stmt ::= 'var'? IDENT '=' Exp | 'print' Exp |  'if' '(' Exp ')' Block ('else' Block)?
Block ::= '{' StmtSeq '}'
Exp ::= Eq ('&&' Eq)*
Eq ::= Add ('==' Add)*
Add ::= Mul ('+' Mul)*
Mul::= Atom ('*' Atom)*
Atom ::= '<<' Exp ',' Exp '>>' | 'fst' Atom | 'snd' Atom | '-' Atom | '!' Atom | BOOL | NUM | IDENT | '(' Exp ')'
```
```txt
EOF = EndOfFile
IDENT = identificatore della variabile
Block è il than dell'if
tuttil gli operatori associano da sinistra
<< Exp , Exp >> sono coppie di valori


```
Secondo le convenzioni adottate a lezione, la grammatica si basa su categorie lessicali definite da espressioni regolari nel
tokenizer implementato dalla classe `lab_09_04_23.parser.BufferedTokenizer` e dal tipo `enum`
`lab_09_04_23.parser.TokenType`: `ID` per gli identificatori di variabile, `BOOL` e `NUM` per i literal di tipo booleano e naturale.
Il tipo di token `EOF` è speciale ed essenziale per identificare la fine del programma e per garantire che esso non termini con
elementi estranei alla sintassi: nel nostro caso il non terminale principale `Prog` specifica che un programma sintatticamente corretto può solo essere
una sequenza non vuota di istruzioni separati dal terminale `;`.

### Tokenizer
Il parser  `lab_09_04_23.parser.BufferedParser` si basa sulla classe `lab_09_04_23.parser.BufferedTokenizer` (che implementa l'interfaccia
`lab_09_04_23.parser.Tokenizer`) e sul tipo `enum` che definisce tutti i possibili tipi di token; entrambi i codici che troverete come starter-code sono già completi e funzionanti. La nozione di token è un'astrazione della nozione di lessema
che permette di passare dalla sintassi concreta a quella astratta. Per esempio, il tipo  `STMT_SEP` rappresenta il token usato per separare due istruzioni;
il parser si riferisce a esso indipendentemente dalla sua sintassi concreta `;`. In questo modo il codice risulterà più leggibile e più facilmente modificabile: nel caso volessimo usare un simbolo diverso, basterà modificare `lab_09_04_23.parser.BufferedTokenizer` lasciando invariato il codice del parser contenuto in `lab_09_04_23.parser.BufferedParser`. In modo del tutto simile, verranno definiti i tipi di token per le
parole chiave (keyword), ossia quei terminali che rappresentano parole chiave che non possono essere usate come identificatori; per esempio `PRINT` corrisponde alla parola chiave che introduce l'istruzione di stampa; il parser si riferisce a questo tipo di token indipendentemente dalla sua rappresentazione concreta, sia essa `print`, `log`, `output` o qualsiasi altra ragionevole scelta.

<!-- Come noterete, la parte di definizione  dei token è statica, ossia realizzata tramite variabili di classe, poiché sarebbe poco pratico e non particolarmente
utile creare tokenizer a partire dalla stessa classe in grado di definire token diversi. -->
Le variabili di classe `keywords` e `symbols` definiscono le mappe (o dizionari) che associano
alle parole chiavi e ai simboli concreti il loro corrispondente tipo astratto; i simboli sono stringhe di caratteri non alfanumerici, mentre le keyword sono stringhe di lettere che non possono essere usate come identificatori; vedere per esempio il programma `tests/failure/prog09.txt`.

Le costanti di tipo `enum Group` rappresentano i gruppi dell'espressione regolare contenuta nel campo `regEx` e usata dal `matcher` del tokenizer;
il loro ordine è importante per un corretto funzionamento del tokenizer e deve coincidere con l'ordine in cui le varie sotto espressioni vengono
combinate per definire `regEx`:

```java
regEx = String.join(regExUnion, symbolRegEx, keywordRegEx, skipRegEx, identRegEx, numRegEx);
```
In questo modo il metodo `assignTokenType()` della classe `lab_09_04_23.parser.BufferedTokenizer` può usare correttamente il
metodo predefinito `ordinal()` del tipo `enum` per restituire il numero naturale assegnato a ogni costante definita in `Group` e individuare il corretto tipo di token del corrente lessema riconosciuto dal tokenizer.

Notate che le costanti `Group.SKIP`, `Group.IDENT` e `Group.NUM` sono diverse da
`TokenType.SKIP`, `TokenType.IDENT` e `TokenType.NUM`: le prime rappresentano i gruppi dell'espressione regolare `regEx`, le seconde i corrispondenti tipi di token (`SKIP` spazi bianchi e commenti su singola linea, `IDENT` identificatori, `NUM` literal numerici); infatti, l'ordine delle costanti definite in `lab09_04_23.parser.TokenType` non è rilevante come accade per il tipo `Group`. Inoltre, grazie all'istruzione
`import static lab09_04_23.parser.TokenType.*` all'interno di `lab09_04_23.parser.BufferedTokenizer`, le espressioni
`SKIP`, `IDENT` e `NUM` sono un'abbreviazione di `TokenType.SKIP`, `TokenType.IDENT` e `TokenType.NUM`.

Il tokenizer `lab_09_04_23.parser.BufferedTokenizer` implementa l'interfaccia `lab_09_04_23.parser.Tokenizer` contenente i metodi del tokenizer usati dal parser implementato dalla classe `lab_09_04_23.parser.BufferedParser`:
```java
public interface Tokenizer extends AutoCloseable {

	TokenType next() throws TokenizerException;

	TokenType tokenType();

	String tokenString();

	int intValue();

	boolean boolValue();

	public void close() throws IOException;

	int getLineNumber();
}
```
Il metodo principale è `TokenType next()` usato per avanzare nella lettura del buffered reader associato al tokenizer: il metodo
solleva l'eccezione controllata (checked) `TokenizerException` se non esiste un prossimo valido lessema, altrimenti restituisce
il tipo del token appena riconosciuto che può essere `EOF` se il buffered reader è terminato. Il tipo `SKIP` viene gestito internamente dal tokenizer
per gestire spazi bianchi e commenti, che vengono scartati; quindi non viene mai
restituito dal metodo `next()`.

Il metodo `TokenType tokenType()` restituisce il tipo del token appena riconosciuto (coincidente con l'ultimo valore restituito da `next()`)
o solleva l'eccezione `IllegalStateException` se nessun token è stato precedentemente riconosciuto.

Il metodo `String tokenString()` restituisce il lessema corrispondente al token appena riconosciuto o solleva l'eccezione
`IllegalStateException` se nessun token è stato precedentemente riconosciuto.

Il metodo `int intValue()` restituisce il valore del token di tipo `NUM` che è stato appena riconosciuto
 o solleva l'eccezione `IllegalStateException` se nessun token di tale tipo è stato precedentemente riconosciuto.

Il metodo `boolean boolValue()` restituisce il valore del token di tipo `BOOL` che è stato appena riconosciuto
 o solleva l'eccezione `IllegalStateException` se nessun token di tale tipo è stato precedentemente riconosciuto.

Il metodo	`void close() throws IOException` è necessario perché il tokenizer è di tipo `AutoCloseable`, per poter usare il costrutto
`try-with-resources` (vedere il metodo `lab09_04_23.parser.BufferedParser.main(String[])`), per gestire lo stream di input di tipo `BufferedReader`
a cui accede il tokenizer; un commento simile si applica
all'interfaccia `lab_09_04_23.parser.Parser`, dato che il parser dipende dal tokenizer (vedere il costruttore `BufferedParser(BufferedTokenizer)`).

Il metodo `int getLineNumber()` restituisce la linea corrente del `BufferedReader` associato al tokenizer, grazie alla decorazione
`java.io.LineNumberReader` (vedere il costruttore `BufferedTokenizer(BufferedReader)`); questo permette di definire messaggi di errore più comprensibili
annotati dal numero di linea in cui è stato rilevato l'errore.

### Implementazione dell'albero della sintassi astratta (AST)
Il package `lab_09_04_23.parser.ast` contiene tutte le definizioni necessarie all'implementazione dell'albero della sintassi astratta (AST).
L'interfaccia `AST` corrispondente al tipo più generale di qualsiasi nodo dell'AST; le sottointerfacce `Prog`, `StmtSeq`, `Stmt`, `Exp` e `Ident`
rappresentano sottotipi di nodi, corrispondenti alle categorie sintattiche
principali (ossia i non-terminal principali). Al momento le interfacce non contengono metodi, ma servono solo per rappresentare vari tipi di nodi
non compatibili tra di loro; per esempio, `Exp` non è sottotipo di `Stmt` e `Stmt` non è sottotipo di `Exp`, perché un'espressione non è uno statement e viceversa. Nei prossimi laboratori verranno introdotti nelle interfacce metodi per implementare la visita di un AST.

Alcune classi astratte permettono di raccogliere a fattore comune codice riutilizzabile:
- `UnaryOp`: codice comune agli operatori unari;
- `BinaryOp`: codice comune agli operatori binari;
- `PrimLiteral<T>`: codice comune alle foglie che corrispondono a literal di tipo primitivo;
- `AbstractAssignStmt`: codice comune agli statement `var` (dichiarazione) e assegnazione.

Per quanto riguarda le sequenze di elementi sintattici (come accade per `StmtSeq`), sarebbe possibile implementare
nodi di un AST con un numero variabile di figli, ma per semplicità si possono gestire tramite due tipi di nodi:

- `SingleStmt`: sequenza con un unico statement, quindi nodo con un unico figlio di tipo `Stmt`.
- `MoreStmt`: sequenza con almeno due statement, quindi nodo con due figli, il primo di tipo `Stmt`, il secondo di tipo `StmtSeq`.

Entrambe le classi riusano il codice delle classi generiche astratte `Single<T>` e `More<FT,RT>`.


#### Importante
Per facilitare il testing, tutte le classi che implementano i nodi dell'AST ridefiniscono il metodo `String toString()` ereditato da `Object` per visualizzare
il corrispondente AST in forma di termine. Per tale scopo vengono usati i metodi predefiniti `getClass()`
e `getSimpleName()` che permettono di accedere al nome della classe di un oggetto.
Per esempio, la stampa dell'AST generato dal parser a partire dal programma contenuto nel file ` tests/success/prog01.txt` produce il termine
```java
ProgClass(MoreStmt(PrintStmt(Add(Sign(IntLiteral(40)),Mul(IntLiteral(5),IntLiteral(3)))),SingleStmt(PrintStmt(Sign(Mul(Add(IntLiteral(40),IntLiteral(5)),IntLiteral(3)))))))
```

## Classi da completare

### Package `lab_09_04_23.parser`
L'unica classe da completare è `BufferedParser`

### Package `lab_09_04_23.parser.ast`

- `AssignStmt`
- `VarStmt`
- `IfStmt`
- `Add`
- `Fst`


## Tests
Potete utilizzare i test nei seguenti folder
- `tests/success`: programmi corretti sintatticamente
- `tests/failure`: programmi con errori di sintassi
