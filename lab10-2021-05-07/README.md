# Laboratorio di LPO, 7 maggio 2021: specifica e implementazione della semantica statica di un linguaggio

### Scopo del laboratorio
Questo è il secondo laboratorio propedeutico al progetto finale.
Svilupperemo un typechecker per il linguaggio giocattolo di cui abbiamo implementato il parser durante il laboratorio precedente; l'implementazione si basa sul visitor pattern (vedere [slide e video](https://2020.aulaweb.unige.it/course/view.php?id=3646#section-23)).
La soluzione completa per questo laboratorio è complessa, ma buona parte del codice sarà già presente, incluso il parser del [laboratorio precedente](https://2020.aulaweb.unige.it/course/view.php?id=3646#section-21).
Voi dovrete 'solamente'
- comprendere il funzionamento del codice già presente;
- completare alcuni metodi della classe `lab10_05_07/visitors/typechecking/TypeCheck.java`; i dettagli si trovano in fondo a questo README.

#### Specifica della semantica statica 
Come spiegato in [slide e video](https://2020.aulaweb.unige.it/course/view.php?id=3646#section-23), la specifica della semantica statica
del linguaggio è espressa in OCaml nel file `semantica-statica.ml`. In fondo al file trovate anche qualche semplice test; la semantica è definita
a livello di sintassi astratta implementata in OCaml tramite variant type, quindi i test sono necessariamente semplici, visto che l'AST del programma deve essere costruito manualmente. Per meglio comprendere la semantica statica sarà indispensabile fare un po' di ripasso di OCaml
(vedere le slide su AulaWeb delle lezioni dalla 10-06 alla 11-04); in particolare vi consiglio di rivedervi le slide su [eccezioni](https://2020.aulaweb.unige.it/mod/resource/view.php?id=48232) e [variant type](https://2020.aulaweb.unige.it/mod/resource/view.php?id=50948).

#### Implementazione degli ambienti in Java
L'implementazione degli ambienti si trova nel package `lab10_05_07/environments`; anche se già completa, è importante comprenderne bene il funzionamento del codice. Per meglio riutilizzare l'implementazione, è stata definita la classe generica `lab10_05_07.environments.GenEnvironment<T>`,
dove la variabile di tipo `T` corrisponde al tipo di informazione (payload) associata agli identificatori di variabile definiti nei vari scope dell'ambiente; per gli ambienti statici, `T` è associata a `Type`
(vedi dettagli sotto). La classe  `lab10_05_07.environments.GenEnvironment<T>` verrà usata anche nel prossimo laboratorio per implementare la semantica dinamica del linguaggio; in quel caso `T` verrà associata a `Value`.

**Nota bene**: tutti i metodi dell'interfaccia `lab10_05_07.environments.Environment` sono usati nel typechecker (file `lab10_05_07/visitors/typechecking/TypeCheck.java`), a parte `update` che è utile solamente per la semantica dinamica, implementata nel prossimo  laboratorio.

#### Implementazione dei tipi in Java
La semantica statica si basa sulla nozione di tipo statico (vedere  file `semantica-statica.ml`); per il nostro linguaggio esistono i tipi di base (ossia, primitivi) `Int` e `Bool` e il tipo strutturato prodotto (`Prod`). Per esempio, l'espressione `<<1,<<true,0>>>>` (sintassi concreta) in OCaml corrispondente all'AST `Pair(Num 1,Pair(Bool true,Num 0))` che ha tipo statico `Prod(Int,Prod(Bool,Int))` (provate a valutare l'espressione `wfExp starting_env (Pair(Num 1,Pair(Bool true,Num 0)))`). Sebbene i tipi statici non facciano parte della sintassi del linguaggio (non vanno dichiarati, visto che vengono dedotti dal typechecker),  `Prod(Int,Prod(Bool,Int))` corrisponde a un'*espressione di tipo* in forma astratta (AST). Conseguentemente, il package `lab10_05_07/visitors/typechecking` contiene le seguenti classi che implementano nodi dell'AST delle espressioni di tipo:
- `PrimType`: tipo `enum` che introduce le due costanti `BOOL` e `INT` corrispondenti a nodi foglia;
- `ProdType`: implementa i nodi che rappresentano il costruttore binario del tipo prodotto e che, quindi, hanno due figli `fstType` e `sndType`.
L'interfaccia `Type`, oltre a definire il tipo dell'AST delle espressioni di tipo, contiene dei metodi di default ausiliari che facilitano il controllo dei tipi nella classe `lab10_05_07.visitors.typechecking.TypeCheck`.

### Classi da completare

L'unica classe da completare è `lab10_05_07.visitors.typechecking.TypeCheck`, in particolare i metodi

- `visitAssignStmt(VarIdent ident, Exp exp)`
- `visitPrintStmt(Exp exp)`
- `visitVarStmt(VarIdent ident, Exp exp)`
- `visitIfStmt(Exp exp, Block thenBlock, Block elseBlock)`
- `visitBlock(StmtSeq stmtSeq)`
- `visitSingleStmt(Stmt stmt)`
- `visitMoreStmt(Stmt first, StmtSeq rest)`
- `visitAdd(Exp left, Exp right)`
- `visitIntLiteral(int value)`
- `visitMul(Exp left, Exp right)`
- `visitSign(Exp exp)`
- `visitVarIdent(VarIdent id)`
- `visitNot(Exp exp)`
- `visitAnd(Exp left, Exp right)`
- `visitBoolLiteral(boolean value)`
- `visitEq(Exp left, Exp right)`
- `visitPairLit(Exp left, Exp right)`
- `visitFst(Exp exp)`
- `visitSnd(Exp exp)`

### Tests
Potete utilizzare i test nei seguenti folder
- `tests/success`: programmi corretti sintatticamente
- `tests/failure/syntax`: programmi con errori di sintassi (come nel laboratorio precedente)
- `tests/failure/static-semantics/`: programmi corretti sintatticamente, ma con errori di semantica statica







