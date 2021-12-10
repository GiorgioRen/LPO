# Laboratorio di LPO, 14 maggio 2021: specifica e implementazione della semantica dinamica di un linguaggio

### Scopo del laboratorio
Questo è il terzo e ultimo laboratorio propedeutico al progetto finale.
Svilupperemo un interprete in grado di eseguire programmi scritti nel linguaggio giocattolo di cui abbiamo implementato il parser e il type-checker durante i laboratori precedenti; come per il type-checker, l'implementazione si basa sul visitor pattern (vedere [slide e video](https://2020.aulaweb.unige.it/course/view.php?id=3646#section-23)).
La soluzione completa per questo laboratorio è complessa, ma buona parte del codice è già presente, incluso il [parser](https://2020.aulaweb.unige.it/course/view.php?id=3646#section-21) e il [type-checker](https://2020.aulaweb.unige.it/course/view.php?id=3646#section-23) sviluppati nei laboratori precedenti.
Voi dovrete 'solamente'
- comprendere il funzionamento del codice già presente;
- completare alcuni metodi della classe `lab11_05_14.visitors.evaluation.Eval`; i dettagli si trovano in fondo a questo README.

#### Specifica della semantica dinamica 
Come per la semantica statica, la specifica della semantica dinamica (vedere le [slide](https://2020.aulaweb.unige.it/mod/folder/view.php?id=112881))
del linguaggio è espressa in OCaml nel file `semantica-dinamica.ml`. In fondo trovate anche qualche semplice test; la semantica è definita
a livello di sintassi astratta implementata in OCaml tramite variant type, quindi i test non possono essere troppo complicati, visto che l'AST del programma deve essere costruito manualmente. Per meglio comprendere la semantica dinamica, se non l'avete già fatto per il laboratorio precedente, sarà indispensabile fare un po' di ripasso di OCaml
(vedere le slide su AulaWeb delle lezioni dalla 10-06 alla 11-04); in particolare vi consiglio di rivedervi le slide su [eccezioni](https://2020.aulaweb.unige.it/mod/resource/view.php?id=48232) e [variant type](https://2020.aulaweb.unige.it/mod/resource/view.php?id=50948).

#### Implementazione degli ambienti in Java
Come già commentato nel laboratorio precedente, l'implementazione degli ambienti è generica e, quindi, può essere usata sia per gli ambienti statici, sia per quelli dinamici; per la semantica dinamica, gli ambienti associano agli identificatoti di variabile i loro corrispondenti valori,  quindi
la classe `lab11_05_14.environments.GenEnvironment<T>` viene usata con `T`=`Value`. 

#### Implementazione dei valori in Java
La semantica dinamica si basa sulla nozione di valore (vedere  file `semantica-dinamica.ml`); nel linguaggio giocattolo da implementare
esistono i valori primitivi di tipo intero e booleano, implementati da due sottoclassi di `lab11_05_14.visitors.evaluation.PrimValue<T>`
e le coppie di valori implementate dalla classe `lab11_05_14.visitors.evaluation.PairValue`.

L'interfaccia `Value`, oltre a definire il tipo di tutti i valori, contiene i metodi di default per le conversioni dinamiche di tipo nel caso in cui
diano errore.

### Classi da completare
L'unica classe da completare è `lab11_05_14.visitors.evaluation.Eval`,  in particolare i metodi

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
- `tests/success`: programmi corretti rispetto alla sintassi e alla semantica statica e dinamica
- `tests/failure/syntax`: programmi con errori di sintassi (come nei laboratori precedenti)
- `tests/failure/static-semantics/`: programmi corretti sintatticamente, ma con errori di semantica statica (come nel laboratorio precedente)
- `tests/failure/static-semantics-only/`: programmi corretti rispetto alla sintassi ma non alla semantica statica, che possono essere eseguiti senza errori dinamici se viene usata l'opzione `-ntc` che evita il type-checking del programma. 
