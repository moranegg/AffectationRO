param n, >0, integer;
/*nombre de taches*/


param m, >0, integer; 
/* nombre de processeurs */


param b{1..m}, >0, integer; 
/*quantite de RAM disponible sur processeur*/ 

param a{1..m,1..n}, >=0; 
/*RAM d'une tache sur un processeur*/

param c{1..m,1..n}, >=0; 
/*cout d'affectation*/

var x{1..m,1..n}, >=0;
/*binary*/

s.t. ram{j in 1..m}: sum{i in 1..n}a[j,i]* x[j,i]<=b[j];
s.t. affect{i in 1..n}: sum{j in 1..m} x[j,i] =1; 


minimize obj: sum{i in 1..n, j in 1..m} c[j,i] * x[j,i];
/* L'objectif est de minimiser les couts de l'execution */

solve;

printf "sum=%f\n", (sum{i in 1..n, j in 1..m} c[j,i]*x[j,i]);
printf "nbTaches=%f\n", n;
printf "nbProcesseurs=%f\n", m;
printf "affectation \n";
for {i in 1..n}
{
    for {j in 1..m}
    {
        printf "%f ", (x[j,i]);
    }
    printf "\n";
}

end;
