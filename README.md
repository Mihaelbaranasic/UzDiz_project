# UzDiz_project — Design Patterns Coursework

## Overview
Three progressive assignments implementing a travel agency (turistička agencija) console application, each building on the previous one and introducing new design patterns while preserving backward compatibility.

## Patterns implemented
- **Zadaća 1:** Builder, multi-module Maven architecture (separate config/model/reservation modules)
- **Zadaća 2:** Singleton, Command + Factory Method, Composite, Decorator, State, Visitor, Facade
- **Zadaća 3:** Chain of Responsibility (CLI argument parsing), Memento (reservation undo), Strategy, Observer

## How to run
Each `zadaca_N` folder is a standalone Maven multi-module project. Build with:
```bash
cd mbaranasi21_zadaca_N
mvn clean install
```
Run the app module (e.g. `mbaranasi21_zadaca_2_app`) with:
```bash
java -cp target/classes edu.unizg.foi.uzdiz.mbaranasi21.zadaca_N.Glavna --ta <putanja_do_aranzmana.csv> --rta <putanja_do_rezervacija.csv>
```
Sample CSV files are included under each `podaci/` folder.

## Interactive commands (zadaća 1 baseline)
| Command | Description |
|---|---|
| `ITAK [datum_od datum_do]` | List all packages, optionally filtered by date range |
| `ITAP <oznaka>` | Print full details of one package |
| `IRTA <oznaka> [PA\|Č\|O\|PAČO]` | List reservations for a package, optionally filtered by status |
| `IRO <ime> <prezime>` | List all reservations for a person |
| `ORTA <ime> <prezime> <oznaka>` | Cancel a person's reservation |
| `DRTA <ime> <prezime> <oznaka> <datum> <vrijeme>` | Add a new reservation |
| `Q` | Quit |

Zadaća 2 and 3 extend this command set (additional codes: ITAC, IEI, ITAS, OTA, UP, BP, IP, and in zadaća 3: PTAR, UPTAR, PSTAR, VSTAR, PPTAR) — see the assignment PDF in each folder for exact semantics of the newer commands.

## Author
Mihael Baranašić — Design Patterns course, FOI Varaždin.
