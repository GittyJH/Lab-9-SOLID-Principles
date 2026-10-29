# ECE 373 — Lab 9 (starter)

**Object-Oriented Software Design · The University of Arizona · Fall 2026 · Jingdi Chen**

Find SOLID violations in real code and refactor them, one principle at a time.

**Due:** Thursday, October 29, 2026, 11:59 pm (Arizona time). Submit your completed
Maven project as a zip through D2L. Individual work.

> This Maven project replaces the old `SOLID_exercise.zip` attachment. It holds
> the same eleven violations plus the two bonus cases, repackaged under
> `edu.arizona.ece373.lab09`.

---

## What is in this project

One package per principle. Inside each, a numbered `example…` package holding a
`violation` package with code that breaks that principle, and an empty
`solution` package where your refactored version goes.

| Principle | Package | Violations |
|---|---|---|
| SRP | `solid.singleresponsibility` | 1: Vehicle refuelling · 2: Board |
| OCP | `solid.openclosed` | 1: Vehicle / EventHandler · 2: Greeter |
| LSP | `solid.liskovsub` | 1: Bird / Ostrich · 2: Apartment / Studio / PenthouseSuite |
| ISP | `solid.interfacesegregation` | 1: Car / Drone / Switches · 2: Bird / Penguin / Eagle · 3: Parking lots |
| DIP | `solid.dependencyinversion` | 1: Driver / RacingCar · 2: WeatherTracker / Phone / Emailer |

**Eleven violations in total.** The bonus `challenge` package holds two harder
cases.

## Required work

Refactor all eleven. For each one:

* Put the refactored classes in that violation's `solution` package.
* **Leave the `violation` code untouched** — the contrast is the point.
* Add a comment at the top of your main solution class, three or four sentences,
  saying **what the violation was, how you know, and what your refactor changed**.
* Make sure the project still compiles: `mvn compile`.

> A refactor that moves code around without removing the violation earns no
> credit. Be able to name the specific thing that could not change before and
> can change now.

Each `solution` package already contains a `package-info.java` reminding you
which violation you are fixing. You may delete it or keep it.

## Suggested method

1. Read the violation and write down, in one sentence, **what change would be
   painful**. ("Adding a fourth greeting means editing a method that works.")
2. Name the principle from that sentence, not from the package name.
3. Refactor.
4. Ask the question again. If the answer has not changed, neither has the design.

Writing a small JUnit test against the violation *before* you refactor, and
making the same test pass against your solution, is the cheapest way to prove
you preserved behaviour. It is not required, but graders notice.

## Bonus — the `challenge` package (bonus marks inside the lab component)

Two harder cases: `challenge.example0`, an `Order` class coupled to a database,
and `Forecast.predict()`, a hundred-line method doing HTTP, JSON parsing and
business logic at once.

To earn the bonus:

* Refactor step by step, **committing after each individual improvement**.
* Use atomic commits — one design change per commit, with a message saying which
  principle it addresses.
* Take a screenshot of `git log --oneline --graph` and attach it.

The commit history is what is graded here, not just the final state. A single
commit called "refactored everything" earns nothing, however good the result.

`Forecast` needs `gson`, `google-api-client` and `org.json`, which this project
does not ship with. Its source is in `challenge-extra/` — see
[`challenge-extra/README.md`](challenge-extra/README.md). Working out what a
project needs to build is part of the exercise.

## Build

```
mvn compile      # must stay green
mvn test         # add your own tests here
```

## What to hand in

The whole project zipped, with your `solution` packages filled in, plus a
screenshot of `mvn compile` succeeding after your refactors, plus the git log
screenshot if you attempted the bonus.

## Academic integrity

You may not post this lab or any solution to it anywhere on the internet. Doing
so is a violation of the University of Arizona Code of Academic Integrity and
will be reported.
