# APPidemic


Im Rahmen des Moduls Java 1 haben wir, <br>
**[Lucas Hengelhaupt](https://github.com/Diafreak)** <br>
**[Annkathrin Hirt](https://github.com/AnkaMulm)**   <br>
**[Leon Müller](https://github.com/DerNoobzockt)**   <br>
**[Henning Venhorst](https://github.com/HenningCV)** <br>
die Gruppe „javarumdennnicht“ gegründet und zusammen „APPidemic“ ins Leben gerufen. <br>
Was das ist und wie es aussieht werden Sie im Folgenden kennenlernen.



## Character

APPidemic ist nicht einfach nur irgendeine weitere social media Plattform.
Der Name lässt vielleicht erahnen, dass es sich um eine Art Mischform aus beispielsweise Instagram und der Corona-Warnapp handelt.
Wie auf Instagram kann man sich ein Profil erstellen, Bilder posten, anderen Leuten folgen und deren eigene Posts liken und kommentieren.
Zusätzlich kann man, wenn zum Beispiel ein gepostetes Bild bei einem gemeinsamen Treffen entstanden ist, die Mitwirkenden markieren.
Posts, in denen man markiert wird, landen ebenfalls auf dem eigenen Profil, allerdings in einer anderen Liste als die selbst geposteten Bilder.
Das Markieren funktioniert nur bei Mutuals, also Personen, denen man folgt, welche ebenfalls zurückfolgen, um Spam zu verhindern.
Denn mithilfe der Markierungen wird das Warnfeature realisiert.
Falls man nämlich positiv auf Covid-19 getestet werden sollte, kann man einen Alarm auslösen, der jede markierte Person über die Infektion informiert, sodass sie sich so früh wie möglich in Quarantäne begeben können.
<br><br>


## Struktur

Dies ist das UML-Diagramm des Projektes mit jeweils einer kurzen Beschreibung der einzelnen Klassen.
(Set und Get Methoden wurden nicht aufgelistet.)

![UML](https://github.com/fh-erfurt/APPidemic/blob/main/ProjectDocumentation/pictures/uml_appidemic.png)



<details>
<summary>Package User</summary>

**User** <br>
Die User-Klasse steht für den Benutzer und speichert persönliche Daten wie Geburtsdatum, Vor- und Nachname.
Außerdem müssen Nutzername, E-Mail-Adresse und Passwort angegeben werden, die später auch geändert werden können.
<br><br>

**UserList** <br>
Die Klasse UserList stellt eine Liste an Usern dar und imitiert dadurch sozusagen eine Datenbank und Datenbankeinträge.

![package_user](https://github.com/fh-erfurt/APPidemic/blob/main/ProjectDocumentation/pictures/package_user.png)
</details>


<details>
<summary>Package Profile</summary><br>

**Profile** <br>
Die Profile-Klasse ist das Herzstück des Projekts, da das meiste über diese Klasse abläuft.
Man kann eine Biografie erstellen und bearbeiten, Leuten folgen und entfolgen, sowohl Follower- als auch
FollowingListen einsehen und verschiedene Privatsphäre-Einstellungen festlegen. Weiterhin kann man Posts erstellen,
die dann auf dem eigenen Profil und ebenso auf den Profilen der potenziell markierten Personen zu sehen sind.
Außerdem kann man den Corona-Alarm an alle markierten Personen aussenden.
<br><br>

**ProfileList** <br>
Die Klasse ProfileList dient der Darstellung der Follower und Following Listen.
Sie dient hauptsächlich der Übersichtlichkeit, da dies auch mittels ArrayListe realisiert werden könnte.

![package_profile](https://github.com/fh-erfurt/APPidemic/blob/main/ProjectDocumentation/pictures/package_profile.png)
</details>


<details>
<summary>Package Post</summary><br>

**Post** <br>
Die Posts bringen Leben in eine social media Plattform.
Ein Post enthält den bzw. die Autor:in, die markierten Personen, eine Postbeschreibung sowie Zeitpunkt und Ort.
Das Bild an sich wird hier durch eine Bildbeschreibung realisiert, da keine Datenbankanbindung vorhanden ist und somit keine Bilder gespeichert werden.
Der gesamte Post wird erst vervollständigt, wenn er mittels zugehöriger Methode submitted wird.
Weiterhin können bei einem Post auch die Likes und die Liked By Liste, sowie die Kommentare eingesehen werden.
<br><br>

**Comment** <br>
Die Kommentare unter einem Post bestehen aus einer Liste von Comment-Klassen.
Die Klasse an sich ist recht klein und besteht nur aus dem Kommentar an sich, dem bzw. der Autor:in und dem Konstruktor.
Die Bearbeitung des Kommentars wird durch die get und set Methoden ermöglicht.

![package_post](https://github.com/fh-erfurt/APPidemic/blob/main/ProjectDocumentation/pictures/package_post.png)
</details>
<br>


## Hintergrund der Struktur

In diesem Projekt wurden nicht sehr viele Klassen verwendet, was dem Ganzen eine gewisse Übersichtlichkeit verleiht.
Comment war beispielsweise anfangs gar nicht als eigene Klasse geplant,
allerdings wurde dies dank der mehreren Elemente innerhalb des Comments ausgelagert.
<br><br>


## Ausführung

Bei APPidemic handelt es sich noch nicht um eine vollständige App, sondern nur um deren innere Struktur.
Seine Blüte wird es erst im Modul Java 2 erreichen.
Beim Benutzen und Testen des jetzigen Standes muss allerdings beachtet werden,
dass ein Java Development Kit zur Ausführung vorhanden sein muss.