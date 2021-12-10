# Laboratorio di LPO, 9 aprile 2021

## Gestione input/output ed eccezioni, uso di `java.util.Map<K,V>`, `java.util.HashMap<K,V>` e `java.util.TreeMap<K,V>` 

### Esercizi proposti

1.  Completare la classe `WordCounterUnsorted` il cui metodo `count` permette di contare il numero di volte che ogni parola (ossia, una sequenza di lettere dove minuscole e maiuscole sono considerate diverse) appare nello stream di testo `reader`, senza ordinare le parole.
La classe non implementa il metodo opzionale `countSorted`.


1. Completare la classe `WordCounterSorted` che oltre a implementare `count`, fornisce anche il metodo opzionale
`countSorted` che conta le parole in ordine alfabetico.

1. Completare la classe `Main` che implementa una semplice interfaccia da linea di comando con le seguenti opzioni:
```
	-i <input>
	-o <output>
	-sort
```
L'opzione `-i <input>` specifica il file di testo di input, l'opzione `-o <output>` quello di output, l'opzione `-sort` richiede
che le parole vangano stampate in ordine alfabetico.
Una stessa opzione può essere ripetuta più volte, l'ultima è quella che viene presa in considerazione.
Se input o output non vengono specificati il programma interagisce rispettivamente con lo standard input o output.

Esempio 1:
```
$ java -cp ~/git/LPOatDIBRIS/LPO19-20/java/labs/bin lab08_04_06.Main -i example.txt 
{exception=1, however=1, instances=1, methods=1, for=1, optionally=1, do=2, recursive=1, current=1, not=1, operations=1, Implementations=1, scenario=1, hashCode=1, and=1, of=1, referential=2, This=1, where=1, traversal=1, directly=1, so=1, map=3, implementations=1, which=1, or=1, may=2, perform=1, indirectly=1, includes=1, handle=1, an=1, the=4, most=1, fail=1, with=1, contains=1, itself=1, Some=1, equals=1, clone=1, self=2, toString=1}
```

Esempio 2:
```
$ java -cp ~/git/LPOatDIBRIS/LPO19-20/java/labs/bin lab08_04_06.Main -o out.txt -i example.txt 
$ cat out.txt 
{exception=1, however=1, instances=1, methods=1, for=1, optionally=1, do=2, recursive=1, current=1, not=1, operations=1, Implementations=1, scenario=1, hashCode=1, and=1, of=1, referential=2, This=1, where=1, traversal=1, directly=1, so=1, map=3, implementations=1, which=1, or=1, may=2, perform=1, indirectly=1, includes=1, handle=1, an=1, the=4, most=1, fail=1, with=1, contains=1, itself=1, Some=1, equals=1, clone=1, self=2, toString=1}
```

Esempio 3:
```
$ java -cp ~/git/LPOatDIBRIS/LPO19-20/java/labs/bin lab08_04_06.Main -sort -i example.txt 
{Implementations=1, Some=1, This=1, an=1, and=1, clone=1, contains=1, current=1, directly=1, do=2, equals=1, exception=1, fail=1, for=1, handle=1, hashCode=1, however=1, implementations=1, includes=1, indirectly=1, instances=1, itself=1, map=3, may=2, methods=1, most=1, not=1, of=1, operations=1, optionally=1, or=1, perform=1, recursive=1, referential=2, scenario=1, self=2, so=1, the=4, toString=1, traversal=1, where=1, which=1, with=1}
```

Esempio 4:
```
java -cp ~/git/LPOatDIBRIS/LPO19-20/java/labs/bin lab08_04_06.Main -sort -i 
Missing argument for option -i
```

Esempio 5:
```
java -cp ~/git/LPOatDIBRIS/LPO19-20/java/labs/bin lab08_04_06.Main -s 
Option error.
Valid options:
	-i <input>
	-o <output>
	-sort
```
