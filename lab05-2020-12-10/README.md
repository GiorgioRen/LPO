# Laboratorio di LPO, 10 dicembre 2020: programmazione in Java, espressioni regolari

## Standard input in Java

In Java si può accedere allo standard input tramite l'espressione [`System.in`](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/System.html#in). Come per lo standard output, può essere conveniente usare un import
statico:

```java
import static java.lang.System.in;
import static java.lang.System.out;
```
In questo modo si possono usare i nomi semplici `in` e `out`.	

È anche possibile usare un import statico "on demand" di tutti i campi e metodi statici di `System`:

```java
import static java.lang.System.*;
```

`System.in` contiene un oggetto di tipo [`java.io.InputStream`](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/io/InputStream.html) che implementa un stream binario di input gestito a basso livello. Per la lettura da stream di testo è più conveniente usare classi come
[`java.util.Scanner`](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/Scanner.html)
che permettono di eseguire operazioni più ad alto livello utili per l'analisi lessicale e la decodifica delle stringhe lette da input:

```java
import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextLine()) // il metodo nextLine() solleva NoSuchElementException se non ci sono più linee
        String line = sc.nextLine(); // legge la prossima linea dello stream  
        // ...
    sc.close(); // chiude lo stream usato da sc
  }
}
```
Un oggetto della classe `Scanner` viene inizializzato con lo stream di input da cui si vuole leggere (`System.in` nell'esempio). Oltre ai metodi `hasNextLine()` e `nextLine()` usati in questa esperienza di laboratorio, la classe fornisce altri metodi simili per riconoscere, leggere e, eventualmente, decodificare, literal e stringhe
(`hasNextInt()`, `nextInt()`, `hasNextBoolean()`, `nextBoolean()`, `hasNext(Pattern pattern)`, `next(Pattern pattern)`, etc.). Prima di chiamare un metodo `next` bisogna verificare che ci sia un prossimo dato da leggere, tramite il corrispondente metodo `hasNext`, per evitare che venga sollevata un'eccezione di tipo `NoSuchElementException` o `InputMismatchException`.

Alla fine delle operazioni di lettura, per chiudere lo stream usato dallo scanner bisogna chiamare il metodo `close()`.

### Esercizi proposti

1. Completare la classe `lab05_12_10.LineLexer` che implementa un analizzatore lessicale usando le classi del package [`java.util.regex`](https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/regex/package-summary.html); l'analizzatore permette di riconoscere i lessemi che conpongono una linea di testo, usando l'espressione regolare con cui viene inizializzato.

   Una volta completata la classe `LineLexer`, eseguire alcuni test mediante la classe
  `lab05_12_10.Main`.

   Esempio di esecuzione:

   ```bash
   $ java lab05_12_10.Main
   x23 res
   lexeme: 'x' group: 1
   lexeme: '23' group: 2
   lexeme: ' ' group: 3
   lexeme: 'res' group: 1
   qwert  42   
   lexeme: 'qwert' group: 1
   lexeme: '  ' group: 3
   lexeme: '42' group: 2
   ctrl-d
   ```

   L'espressione regolare `([a-zA-Z]+)|(\d+)|(\s+)` permette all'analizzatore di riconoscere i seguenti gruppi:

   * gruppo 1: sequenze non vuote di lettere;
   * gruppo 2: sequenze non vuote di cifre decimali;
   * gruppo 3: sequenze non vuote di spazi bianchi.

   #### Osservazioni

   * per rispettare le regole lessicali di Java, nella stringa passata al factory method `LineLexer.withRegex` le espressioni regolari `\d` e `\s`
devono essere rispettivamente convertite in `\\d` e `\\s`;
   * nonostante nelle espressioni regolari l'operatore `+` e quello di concatenazione abbiano la precedenza sull'unione `|`,
tutti gli operandi dell'unione sono delimitati da parantesi per definire i gruppi corrispondenti. 

1. Modificare la classe `Main.java` cambiando l'espressione regolare  passata a `LineLexer.withRegex`
  perché vengano riconosciuti i seguenti gruppi:

   * *identificatori* (gruppo 1): sequenze non vuote di lettere, cifre decimali e carattere `_` (underscore), che devono iniziare
  con una lettera;

   * *literal interi* (gruppo 2): sequenze non vuote di cifre dove `0` non può apparire in posizioni non significative.

   * *spazi bianchi* (gruppo 3): sequenze non vuote di spazi bianchi.

1. Modificare la classe `Main.java` del punto precedente estendendo l'espressione regolare  passata a  `LineLexer.withRegex`
  perché venga riconosciuto anche il seguente gruppo:

   * *literal di tipo stringa* (gruppo 4): sequenze che iniziano e finiscono con il carattere `"` (doppio apice);
  la stringa delimitata dai doppi apici è una sequenza, anche vuota, di stringhe che possono essere:

      * le stringhe di lunghezza 1 composte da un qualsiasi carattere diverso da `"`  (doppio apice) e `\` (backslash);
      * la stringa di lunghezza 2 `\\` (2 backslash);
      * la stringa di lunghezza 2 `\"` (backslash doppio apice).

   Esempio di stringa valida: `"a double quote\" a backslash \\"`.

