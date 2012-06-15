Rakenne
=======

Ohjelma on jaettu kahteen pääpakettiin: pelilogiikan sisältävään
`gamelogic`- ja käyttöliittymät sisältävään `ui`-pakettiin. Jälkimmäinen
paketti sisältää alapaketteinaan eri käyttöliittymät, joita tällä hetkellä
on vain yksi (`ui.swing`).


gamelogic
---------

`gamelogic`-paketin ydintoiminnallisuus on jaettu luokkien `Board`, `Deck`,
`Meeple`, `Piece`, `Player` ja `VilleBasseEngine` kesken.

`Player`-luokka kuvaa peliä pelaavaa pelaajaa. Se pitää kirjaa pelaajan
pisteistä ja pelinappuloista. Lisäksi se toimii tehdasluokkana
pelinappuloille.

Luokka `Meeple` mallintaa pelinappulaa. Se tietää, kenen nappula se on ja
mihin kohtaan pelilaattaa se on asetettu.

Abstrakti luokka `Piece` vastaa pelilaattaa. Sillä on kiertokulma, tieto
sivujensa maastotyypeistä ja mahdollisesti asetettu pelinappula. Kaikki
laatat perivät `Piece`-luokan ja niiden tarvitsee ainoastaan asettaa
maastotyyppinsä.

Abstrakti `Deck`-luokka toteuttaa laattapakkaan liittyvät toiminnot, kuten
pakan sekoittamisen ja laattojen vetämisen pakasta. Eri pakkatyypit, kuten
oletuspakka, perivät nämä toiminnot ja konstruktorissaan lisäävät halutut
pelilaatat itseensä.

Luokka `Board` mallintaa pelilautaa. Pelilaattoja laitetaan laudalle ja
lauta pitää huolen, ettei laattoja laiteta sääntöjen vastaisiin paikkoihin.
`Board`- ja `Piece`-luokka keskustelevat suuntiin liittyvistä asioista
`Direction`-apuluokan välityksellä.

Pelimoottoriluokka `VilleBasseEngine` nivoo nämä kaikki yhteen helposti
käytettävään pakettiin. Se pitää huolen vuorojen kulusta, laattojen pakasta
nostamisesta jne. Moottori lähettää GameStateEvent-luokan instansseja
rekisteröidyille GameStateEventListener-rajapinnan toteuttajille, mikä
mahdollistaa tapahtumapohjaisen ohjelmarakenteen.


ui.swing
--------

Käyttöliittymän alustuksen yhteydessä käyttöliittymä luo itselleen uuden
`VilleBasseEngine`-instanssin ja rekisteröityy `GameStateEvent`-
kuuntelijaksi.  Samassa vaiheessa luodaan myös käyttöliittymän pääikkunan
komponentit: `JPanelUserEventSource`-luokan perilliset `BoardPanel` ja
`ControlPanel`, jotka välittävät käyttäjän toimintaa pääikkunalle
`UserEvent`-tapahtumien avulla.

`BoardPanel`-luokka vastaa pelilaudan rakentamisesta ja kuuntelee laudalla
tapahtuvia hiiritapahtumia. Lauta kostuu ruudukossa olevista `GUIPiece`
-instansseista, jotka `PieceToImageMapper`-luokan avustuksella piirtävät
laattaa vastaavan kuvat pyydetyn kokoisena ja oikeassa kiertokulmassa.

Pääikkuna kuuntele `GameStateEvent`-tapahtumia ja asettaa `GameStateEvent`
-tapahtumakäsittelijöissä pelin tilaa vastaavan toiminnallisuuden
toteuttavan kuuntelijan hoitamaan käyttäjän aiheuttamia tapahtumia.
