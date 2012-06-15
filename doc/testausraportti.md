Testausraportti
===============

`gamelogic`-paketin ydinluokkien julkiset metodit (triviaaleja getter- ja
setter-metodeja lukuunottamatta) on pyritty testaamaan mahdollisimman hyvin
automaattisin JUnit-yksikkötestein.

`ui.swing`-paketin luokkien testaus on suoritettu ajamalla ohjelmaa ja
käyttämällä käyttöliittymää.

Kovin suurilla syötteillä ei testejä ole suoritettu, koska syötteet ei
reaalisesti nouse kovinkaan korkeiksi. Toisaalta en näe syytä, miksi
suuremmatkaan tietomäärät rikkoisivat ohjelman. Tietyin paikoin on käytetty
naiiveja algoritmeja ja tietorakenteita, mikä jossain kohtaa alkaa
varmastikin näkyä hitautena.
