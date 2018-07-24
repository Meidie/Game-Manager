# Dizajn

## TODO

### Legenda 
* K - konstruktor
* M - metoda
* (M) - zahrnut do / vytvorit metodu

1. Class Player
* K si vypita meno a ci chce byt server/client (boolean) nastavit do Player
* (M) ak sa rozhodne pre clienta tak vypytat IP a Port a vytvorit new Client(IP,Port)

2. Class Client 
* moznost odosielat a primat spravy (pripadne objekty) 

3. Class Game     new Game(int x, int y)
* K vypitat x, y, velkost plochy a vytvorit 2rozmerne pole (char) setnut ho na "0"
* M rozhodovaci alg o tom ci ked spravim tah ci som vyhral alebo je remiza 

4. Class Server
* moznost odosielat a primat spravy (pripadne objekty) 
* M zmenu nastavenia hry (velkost plochy)


#TODO 24.7.2018
* static M - imput od uzivatela ci chce server alebo client   (napr  1- server  0 - client )
* static M - imput (int) pre port     ps skratky ak sa zada napr 0 -> tak posli 300000
* static M - imput (String) pre IP    ps skratka ak sa zada 0 daj localhost 
