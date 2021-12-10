# Laboratorio di LPO, 5 novembre 2020: programmazione in OCaml

Definire in OCaml le seguenti funzioni.

1. `prod : int list -> int` </br>
`prod l` restituisce il prodotto di tutti i numeri interi contenuti nella lista `l`.
1. `member : 'a -> 'a list -> bool` </br> 
`member e l` restituisce `true` se e solo se `e` è un elemento della lista `l`.
1. `insert : 'a -> 'a list -> 'a list` </br>
  `insert e l` restituisce la lista ottenuta aggiungendo `e` in fondo alla lista `l` se `e` non appartiene già a `l`;
  restituisce `l` altrimenti.
1. `odd : 'a list -> 'a list` </br>
`odd l` restituisce la lista ottenuta da `l` tenendo solo gli elementi di indice dispari, dove il primo elemento ha indice 1.</br>
Esempio: `odd [1;2;3;4;5] = [1;3;5]`
1. `ord_insert : 'a -> 'a list -> 'a list` </br>
`ord_insert e l` restituisce la lista ordinata in modo crescente e senza ripetizioni ottenuta aggiungendo `e` a `l`,
assumendo che `l` sia ordinata in modo crescente e senza ripetizioni.
1. `merge : 'a list * 'a list -> 'a list` </br>
`merge (l1,l2)` restituisce la lista ordinata in modo crescente e senza ripetizioni
ottenuta fondendo assieme le liste ordinate in modo crescente e senza ripetizioni `l1` ed `l2`. </br>
Esempio: `merge ([1;3;5],[2;4;6]) = [1;2;3;4;5;6]`
1. `curried_merge : 'a list -> 'a list -> 'a list` </br>
   `curried_merge` è  la versione curried di `merge`; definire la funzione senza
  usare `merge`.


