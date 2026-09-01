# UzDiz_project — Design Patterns Coursework (Tourist Agency System)

## Overview
Three progressive assignments implementing a travel agency console application. Each assignment builds directly on top of the previous one's codebase — new patterns are layered in while preserving existing functionality, rather than starting from scratch each time.

## Patterns implemented, by assignment
- **Zadaća 1:** Builder (Aranžman construction), Factory Method (reservation creation), multi-module Maven architecture separating config/model/reservation concerns
- **Zadaća 2** *(8 patterns total)*: Singleton, Builder (with Director), Factory Method (Command creation via `KomandaFactory`), Composite, Decorator, State (reservation & package lifecycle), Visitor, Facade
- **Zadaća 3** adds 5 more: Strategy + Null Object (reservation limit policies), Observer, Memento (reservation undo), Chain of Responsibility (CLI argument parsing), an additional Visitor implementation

## How to run
Each `zadaca_N` folder is a standalone Maven multi-module project:
```bash
cd mbaranasi21_zadaca_N
mvn clean install
java -cp <app-module>/target/classes edu.unizg.foi.uzdiz.mbaranasi21.zadaca_N.Glavna --ta <aranzmani.csv> --rta <rezervacije.csv>
```
Sample CSV data is included under each assignment's `podaci/` folder.

## Interactive commands (baseline, extended per assignment)
| Command | Description |
|---|---|
| `ITAK [datum_od datum_do]` | List packages, optionally filtered by date range |
| `ITAP <oznaka>` | Print full package details |
| `IRTA <oznaka> [PA\|Č\|O\|PAČO]` | List a package's reservations, optionally by status |
| `IRO <ime> <prezime>` | List a person's reservations across all packages |
| `ORTA <ime> <prezime> <oznaka>` | Cancel a reservation |
| `DRTA <ime> <prezime> <oznaka> <datum> <vrijeme>` | Add a new reservation |
| `Q` | Quit |

Zadaća 2 and 3 add further commands (ITAC, IEI, ITAS, OTA, UP, BP, IP, and in zadaća 3: PTAR, UPTAR, PSTAR, VSTAR, PPTAR) covering composite reporting, decorators, and strategy-based reservation limits — see the assignment brief in each folder for exact semantics.

## Author
Mihael Baranašić — Design Patterns course, FOI Varaždin.
