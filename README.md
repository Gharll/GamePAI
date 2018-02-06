# GamePAI
Projekt końcowy gry na przedmiot: "Projektowanie aplikacji internetowych"

Opis założeń:
- Gra zaklada nieskonczona liczbe graczy lub gdy ze wzgledu na reguly to nie ma sensu, gre w wielu pokojach rownoczesnie
- Komunikacja pomiedzy klientami (graczami) odbywa sie zawsze za posrednictwem serwera
- Serwer jest odporny na nieprawidlowe polecenia przesylane przez klienta
- Konfiguracja serwera w pliku XML.
- Nalezy dostarczyc takze zbior testow jednostkowych.

Gra która została zaprojektowana to połączenie dwóch gier:
- kółko i krzyżyk (podobne zasady aby wygrać grę)
- go! (ze względu na rozmiary planszy i podobny wygląd)
Dodatkowo na planszy pojawiają się przeszkody, na których gracz nie może stawiać swoich "żetonów".

Aby uruchomić grę należy najpierw uruchomić server/Server.java
Kolejnych klientów tworzymy po przez uruchomienie application/MainApp.java
