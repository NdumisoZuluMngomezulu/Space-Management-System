# Space-Management-System
````markdown
# 🛰️ Space Colony Management System — Advanced Java OOP Assessment

## Overview

In this assessment you will design and implement a **Space Colony Management System** in Java.

The system manages colonies on distant planets, including astronauts, missions, resources, and mission control centers. Colonies receive missions, consume resources, and process mission assignments through specialized command centers.

This assessment is intentionally more difficult than previous ones and focuses heavily on:

- Advanced OOP design
- Encapsulation
- Inheritance
- Composition
- Polymorphism
- Defensive copying
- Validation
- Collections (`List`, `Map`, `Queue`)
- Abstract classes
- Enums
- State management
- Unit testing

---

# Learning Outcomes

By completing this assessment, you should demonstrate the ability to:

- Design multi-class systems using OOP
- Build relationships between objects
- Protect internal object state
- Use inheritance and polymorphism effectively
- Work with Java collections
- Handle validation and exceptions correctly
- Build testable systems

---

# Time Limit

**4 hours**

---

# Assessment Structure

| Component | Weight | Recommended Time |
|---|---|---|
| Implementation | **65%** | 2h 45m |
| UML Diagram | **20%** | 45m |
| Long Questions | **15%** | 30m |

---

# Scoring

```text
Coding Score = (tests passed / total tests) × 65%
UML Score = (marks earned / total UML marks) × 20%
Long Q Score = (marks earned / total marks) × 15%

Final Score = Coding Score + UML Score + Long Q Score
````

To pass, your Final Score must be **65% or higher**.

---

# Project Structure

```text
space-colony/
├── pom.xml
└── src/
    ├── main/
    │ └── java/
    │ └── com/
    │ └── colony/
    │ ├── Main.java
    │ ├── model/
    │ │ ├── Resource.java
    │ │ ├── Astronaut.java
    │ │ ├── Mission.java
    │ │ ├── Colony.java
    │ │ ├── MissionReport.java
    │ │ └── enums/
    │ │ ├── MissionStatus.java
    │ │ ├── Rank.java
    │ │ └── ResourceType.java
    │ └── service/
    │ ├── MissionControl.java
    │ ├── ResearchControl.java
    │ └── MiningControl.java
    └── test/
        └── java/
            └── com/
                └── colony/
                    ├── ResourceTest.java
                    ├── AstronautTest.java
                    ├── MissionTest.java
                    ├── ColonyTest.java
                    ├── MissionReportTest.java
                    └── MissionControlTest.java
```

---

# Build Command

Run tests frequently while developing:

```bash
mvn clean compile test
```

Your goal is to achieve **100% passing tests**.

---

# Implementation Steps

Work through the steps in order.

---

# Step 1 — Implement `Resource`

**File:** `model/Resource.java`

Represents a colony resource such as oxygen, fuel, or food.

---

## Fields

| Field | Type | Details |
| --------------- | -------------- | ---------------------------------------- |
| `type` | `ResourceType` | Type of resource. Must be private. |
| `quantity` | `double` | Available quantity. Must be private. |
| `criticalLevel` | `double` | Minimum safe threshold. Must be private. |

---

## Constructor

Accepts:

* `type`
* `quantity`
* `criticalLevel`

---

## Validation Rules

* `quantity < 0` → throw `IllegalArgumentException`
* `criticalLevel < 0` → throw `IllegalArgumentException`

---

## Methods

| Method | Details |
| ----------------- | ----------------------------------------- |
| `type()` | Returns resource type |
| `quantity()` | Returns quantity |
| `criticalLevel()` | Returns critical level |
| `consume(double)` | Reduces quantity |
| `restock(double)` | Adds quantity |
| `isCritical()` | Returns true if quantity <= criticalLevel |
| `toString()` | Human-readable summary |

---

# Step 2 — Implement `Astronaut`

**File:** `model/Astronaut.java`

Represents an astronaut assigned to missions.

---

## Fields

| Field | Type | Details |
| ------------------- | -------------- | ----------------------------------- |
| `name` | `String` | Astronaut name |
| `rank` | `Rank` | Commander, Engineer, Scientist, etc |
| `energyLevel` | `int` | 0–100 |
| `missionsCompleted` | `int` | Starts at 0 |
| `skills` | `List<String>` | Must use defensive copies |

---

## Constructor

Accepts:

* `name`
* `rank`
* `energyLevel`
* `skills`

---

## Validation Rules

* `energyLevel` must be between `0` and `100`
* Empty skill list not allowed

---

## Methods

| Method | Details |
| --------------------- | ----------------------------------- |
| `name()` | Returns name |
| `rank()` | Returns rank |
| `energyLevel()` | Returns energy |
| `skills()` | Returns defensive copy |
| `rest(int)` | Increases energy up to max 100 |
| `performMission()` | Reduces energy by 20 |
| `completeMission()` | Increments missions completed |
| `missionsCompleted()` | Getter |
| `hasSkill(String)` | Returns true if astronaut has skill |
| `toString()` | Summary |

---

# Step 3 — Implement `Mission`

**File:** `model/Mission.java`

Represents a colony mission.

---

## Fields

| Field | Type |
| -------------------- | --------------------------- |
| `missionId` | `int` |
| `missionName` | `String` |
| `requiredSkill` | `String` |
| `assignedAstronauts` | `List<Astronaut>` |
| `requiredResources` | `Map<ResourceType, Double>` |
| `status` | `MissionStatus` |
| `difficultyLevel` | `int` (1–10) |

---

## Constructor

Accepts:

* missionId
* missionName
* requiredSkill
* difficultyLevel

Initialises:

* `assignedAstronauts`
* `requiredResources`
* `status = MissionStatus.PENDING`

---

## Validation Rules

* difficulty must be between `1–10`

---

## Methods

| Method | Details |
| ------------------------------------------- | --------------------------------------------------- |
| `assignAstronaut(Astronaut)` | Only astronauts with required skill may be assigned |
| `addRequiredResource(ResourceType, double)` | Adds resource requirement |
| `assignedAstronauts()` | Defensive copy |
| `requiredResources()` | Unmodifiable map |
| `updateStatus(MissionStatus)` | Updates mission status |
| `calculateMissionRisk()` | Returns difficulty × number of required resources |
| `toString()` | Summary |

---

# Step 4 — Implement `Colony`

**File:** `model/Colony.java`

Represents a planetary colony.

---

## Fields

| Field | Type |
| -------------------- | ----------------------------- |
| `name` | `String` |
| `populationCapacity` | `int` |
| `astronauts` | `List<Astronaut>` |
| `resources` | `Map<ResourceType, Resource>` |
| `missionHistory` | `Queue<MissionReport>` |

---

## Constructor

Accepts:

* `name`
* `populationCapacity`

---

## Methods

| Method | Details |
| --------------------------------------- | ------------------------------ |
| `addAstronaut(Astronaut)` | Cannot exceed capacity |
| `addResource(Resource)` | Adds resource |
| `getResource(ResourceType)` | Returns resource or null |
| `consumeResource(ResourceType, double)` | Uses consume() |
| `addMissionReport(MissionReport)` | Adds report to queue |
| `missionHistory()` | Returns defensive copy |
| `astronauts()` | Defensive copy |
| `resources()` | Unmodifiable map |
| `criticalResources()` | Returns all critical resources |
| `toString()` | Summary |

---

# Step 5 — Implement `MissionReport`

**File:** `model/MissionReport.java`

Represents the outcome of a mission.

---

## Fields

| Field | Type |
| ----------- | --------------- |
| `mission` | `Mission` |
| `success` | `boolean` |
| `notes` | `String` |
| `timestamp` | `LocalDateTime` |

---

## Constructor

Accepts:

* mission
* success
* notes

Timestamp should automatically use:

```java
LocalDateTime.now()
```

---

## Methods

| Method | Details |
| ------------- | ------- |
| `mission()` | Getter |
| `success()` | Getter |
| `notes()` | Getter |
| `timestamp()` | Getter |
| `toString()` | Summary |

---

# Step 6 — Implement `MissionControl` (Abstract Class)

**File:** `service/MissionControl.java`

This abstract class manages missions for a colony.

---

## Fields

| Field | Type |
| ------------------ | --------------------- |
| `controlName` | `String` |
| `colony` | `Colony` |
| `missions` | `Queue<Mission>` |
| `completedReports` | `List<MissionReport>` |

---

## Constructor

Accepts:

* `controlName`
* `colony`

Initialises collections.

---

## Concrete Methods

| Method | Details |
| ---------------------- | ---------------------------- |
| `addMission(Mission)` | Adds mission to queue |
| `nextMission()` | Returns next pending mission |
| `missions()` | Defensive copy |
| `completedReports()` | Defensive copy |
| `processNextMission()` | Processes the next mission |
| `controlName()` | Getter |

---

# Mission Processing Rules

`processNextMission()` must:

1. Retrieve next mission
2. Throw exception if no astronauts assigned
3. Ensure all required resources exist
4. Ensure colony has enough resources
5. Consume required resources
6. Set mission status to `IN_PROGRESS`
7. Call abstract `executeMission(mission)`
8. Set status to `COMPLETED`
9. Call `completeMission()` on all astronauts
10. Create `MissionReport`
11. Store report in:

* colony history
* completedReports

---

# Abstract Method

```java
protected abstract boolean executeMission(Mission mission);
```

Subclasses determine mission success.

---

# Step 7 — Implement `ResearchControl` & `MiningControl`

---

# `ResearchControl`

**Extends:** `MissionControl`

---

## Rules

`executeMission()` returns:

* `true` if:

  * at least one astronaut has `"Science"`
  * mission difficulty < 8
* otherwise false

Print:

```text
[controlName] conducting scientific research mission [missionName]
```

---

# `MiningControl`

**Extends:** `MissionControl`

---

## Rules

`executeMission()` returns:

* `true` if:

  * at least one astronaut has `"Mining"`
  * total astronaut energy > 100
* otherwise false

Print:

```text
[controlName] conducting mining operation [missionName]
```

---

# UML Class Diagram

Create a UML class diagram using draw.io.

Your diagram must include:

* All classes
* Enums
* Access modifiers
* Relationships
* Method signatures
* Inheritance arrows
* Composition/association relationships

Export as:

```text
uml.pdf
```

Place it in the root directory.

---

# Long Questions

Answer in:

```text
answers.txt
```

Do not change formatting.

---

# Question 1 — Encapsulation

Explain why defensive copying is important in the `Astronaut`, `Mission`, and `Colony` classes.

Provide one example of a bug that could happen without defensive copying.

---

# Question 2 — Inheritance & Polymorphism

Explain how `MissionControl`, `ResearchControl`, and `MiningControl` demonstrate inheritance and polymorphism.

---

# Question 3 — System Design

Explain two advantages of separating `Mission`, `MissionReport`, and `MissionControl` into different classes instead of combining all logic into one class.

---

# Bonus Challenges (Optional)

---

## Bonus 1 — Emergency Shutdown

Add:

```java
public void emergencyShutdown()
```

Requirements:

* All pending missions become `CANCELLED`
* No future missions may be processed afterward

---

## Bonus 2 — Astronaut Fatigue

If an astronaut’s energy drops below `20`, they cannot be assigned to new missions.

---

## Bonus 3 — Resource Prediction

Implement:

```java
public Map<ResourceType, Double> projectedResourceUsage()
```

Returns total resources needed for all pending missions.

---

# Submission Notes

Before submission:

```bash
mvn clean compile test
```

Your project must compile successfully.

---

# Assessment Advice

This assessment is intentionally difficult.

Common mistakes include:

* Returning mutable collections directly
* Forgetting validation
* Not updating mission status correctly
* Breaking encapsulation
* Forgetting inheritance relationships
* Incorrect queue processing logic
* Mutating objects unexpectedly

---

*Good luck, Commander. Humanity is depending on your colony. 🛰️*

```
