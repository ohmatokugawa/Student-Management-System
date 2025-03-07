Nazwa projektu: Student Management System
Autor: Dominik Haładus

Cel projektu: Stworzenie prostego systemu w języku Java do zarządzania studentami z 
zintegrowaną bazą danych SQLite oraz GUI opartym na Swing.
Instrukcje dotyczące kompilacji i uruchamiania aplikacji.

W przypadku braku zainstalowanej Javy na Twoim urządzeniu nie będziesz w stanie 
skompilować programu.

Jeżeli chcesz prawidłowo korzystać z aplikacji zainstaluj Java Compiler z wersją co 
najmniej 20.0.1+9-29 oraz skonfiguruj Sqlite do poprawnego otwarcia bazy danych.
1. Pobierz i wypakuj plik .RAR z projektem. W katalogu znajdująsię pojedyczne pliki z 
wszystkimi klasami oraz plik z bazą danych. Żeby poprawnie uruchomić program skompliuj 
go przez pobrany Java Compiler np. IntelliJ. Po kompilacji aplikacja zostanie uruchomiona.

Funkcjonalności oferowana przez system zarządzania studentami:
• Dodawanie studenta do bazy w oparciu o ID, imię, wiek oraz ocenę za pomocą
przycisku Add Student
• Usuwanie studenta za pomocą przycisku Remove Student
• Aktualizacja danych studenta za pomocą przycisku Update Student
• Wyświetlanie pełnej listy studentów za pomocą Display All Students
• Obliczanie średniej ocen studentów za pomocą Calculate Average
Aplikacja obsługuje wyjątki oraz powiadamia użytkownika o błędach przy wpisywania 
danych.

Technologie użyte przy tworzeniu aplikacji
• Język Programowania:
• Java – Język użyty do stworzenia aplikacji, oparty na paradygmacie obiektowym, 
zapewniający przenośność i skalowalność aplikacji.
• Środowisko IDE:
• IntelliJ IDEA – Zintegrowane środowisko programistyczne (IDE), które ułatwiło 
pisanie, debugowanie i testowanie kodu. IntelliJ oferuje wygodne narzędzia do 
pracy z projektem Java oraz wsparcie dla Maven i bazy danych SQLite.
• SQLite – Lekka, wbudowana baza danych, która przechowuje dane lokalnie w pliku. 
Idealna do małych aplikacji, które nie wymagają rozbudowanego serwera 
bazodanowego.
• JDBC – Użyte do połączenia aplikacji z bazą danych SQLite, umożliwiając 
wykonywanie operacji CRUD (Create, Read, Update, Delete).
• Swing – Biblioteka do tworzenia graficznych interfejsów użytkownika w Javie. Dzięki 
Swing stworzyłeś prosty, ale funkcjonalny interfejs użytkownika aplikacji, 
umożliwiający łatwe zarządzanie danymi studentów.
• Maven – Narzędzie do zarządzania zależnościami oraz budowaniem aplikacji. 
Maven umożliwia automatyczne pobieranie bibliotek i zarządzanie wersjami, co 
ułatwia integrację z bazą danych oraz innymi bibliotekami. Projekt jest 
skonfigurowany do obsługi SQLite za pomocą zależności w pliku pom.xml.
• Java Swing Layout Managers (GridLayout, BorderLayout, itd.) – Służyły do 
organizowania komponentów w GUI, co umożliwiło łatwe i elastyczne 
rozmieszczenie przycisków, pól tekstowych i obszarów wyjściowych.
• PreparedStatement (JDBC) – Użyte do zabezpieczenia aplikacji przed atakami typu 
SQL Injection, zapewniając bezpieczne wykonywanie zapytań do bazy danych
