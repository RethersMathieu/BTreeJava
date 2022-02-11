# Projet d'indexation & Recherche - FSGBD

## Membres du groupe

 - Nicolas CORBIERE
 - Alexandre LEMOINE
 - Yannick ALCARAZ
 - Mathieu RETHERS
 - Lisa DESLANDES

## Sujet (ReadMe.txt)

  Il s'agit d'une implémentation en Java d'un arbre balancé. Plus précisément il s'agit d'une classe de gestion d'un BTree+ , ce qui signifie que TOUTES les données sont stockées dans les feuilles de l'arbre, les noeuds intermédiaires permettant uniquement de trier les données.

  Les arbres BTree et BTree+ sont fréquemment utilisés, en particulier dans le stockage de données car ils permettent d'avoir toujours un temps de recherche maitrisable.On les retrouve principalement dans les systèmes de fichier (NTFS par exemple) et dans les SGBD (Oracle...)

Ici sont gérés :
- la création d'un arbre de n'importe quel ordre
- l'ajout de données
- la recherche d'une valeur dans l'arbre

La classe de traitement est générique, c'est à dire que l'on peut stocker n'importe quel type de données dans l'arbre, mais comme il s'agit d'un tri, il faut définir et préciser une procédure de comparaison de ces données.

Grâce à la serialization, on peut aussi sauvegarder et charger un arbre.

## Ce que nous avons ajouté

  - Ajout dans le GUI le fait de pouvoir sélectionner un fichier de type CSV
  - Modification du constructeur BTreePlus permettant l'implémentation de données CSV directement dans l'arbre BTree
  - Création d'une Inner Class "indexation" permettant de contenir l'index, le pointeur et les valeurs associées.
  - Ajout d'une méthode de recherche séquenciel et de ses tests (main)
  - Mise en place de la générétité des données de l'arbre (possibilité de le mettre en String, Long, Int, Double, Float, Boolean et Char)
  - Ajout de la méthode "search" dans la classe BTreePlus, et de la méthode "getByIndex" dans la classe Noeud qui permette une recherche par index.
  - Recherche des feuilles suivantes -> Pointeur

## Resultat de la Recherche

  Nous précisons que ces résultats sont véridiques, nous avons effectués ces tests à plusieurs reprises sur différents environnements.

Temps de recherche séquenciel dans le fichier :
   - min = 0 ms
   - max = 2 ms
   - moyenne = 0.59 ms
   
Temps de recherche par l'index dans le fichier :
   - min = 0 ms
   - max = 0 ms
   - moyenne = 0.0 ms

PREUVE IMAGE :

![image](https://user-images.githubusercontent.com/71333891/153670980-f829f503-b672-4bc3-b9f6-da43968eab29.png)
