# Progetto finale LPO a.a. 2020-2021

> LPO_interpreter: interpreter del linguaggio `L++` definito a partire dall'[ultimo laboratorio](lab11-2021-05-14).
> Leggi la [specifica](progetto-finale/specifica.pdf) per maggiori informazioni.

## Contenuto del repository

* `semantica-statica.ml` : semantica statica del linguaggio esteso, definita in OCaml
* `semantica-dinamica.ml` : semantica dinamica del linguaggio esteso, definita in OCaml
* `specifica.pdf`: specifica del linguaggio esteso, con commenti ed esempi
* `tests/success`: test che vengono eseguiti senza errori senza l'opzione `-ntc`
* `tests/success/ntc`: test che vengono eseguiti senza errori con l'opzione `-ntc`
* `tests/failure/syntax`: test che non passano i controlli di sintassi 
* `tests/failure/type`: test che non passano i controlli di semantica statica (ossia, senza l'opzione `-ntc`)
* `tests/failure/ntc`: test che causano errori dinamici con l'opzione `-ntc`

## Modalità di consegna

È sufficiente avere attivato il link di GitHub classroom e rendere disponibili sul repository tutti i sorgenti necessari
per la compilazione del progetto. Ricordarsi di fare commit e push finali e comunicare il link
del repository tramite la [consegna su AulaWeb](https://2020.aulaweb.unige.it/mod/assign/view.php?id=21834). 
La consegna è **unica** per ogni gruppo.

**Importante**: per la consegna è necessario che il progetto passi **tutti i test** contenuti nel folder `tests`.
Tutti i componenti del gruppo devono aver contribuito attivamente allo sviluppo del progetto comprendendone il funzionamento;
ciò verrà verificato tramite un colloquio finale **individuale**.
