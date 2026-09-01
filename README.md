# Veterinary Hotel Management System

Academic Java project developed for the **Object-Oriented Programming** course at Instituto Superior Técnico (2024/25).

**Project grade: 15.93/20**

## Overview

The project implements a management application for a veterinary hotel. The system models and manages animals, species, habitats, trees, employees, veterinarians, keepers, vaccines and vaccination records.

The final application supports operations such as:

- registering and managing animals, employees, habitats, trees and vaccines;
- transferring animals between habitats;
- assigning and removing employee responsibilities;
- administering vaccines and recording vaccination history;
- tracking health effects caused by inappropriate vaccinations;
- calculating animal, employee and global satisfaction;
- advancing the seasonal cycle that affects trees;
- querying animals and vaccination records;
- importing initial domain data from structured text files;
- saving and restoring application state using Java serialization.

## Architecture and design context

The course specification required a layered architecture separating the presentation layer (`hva.app`) from the domain layer (`hva.core`), with business logic kept in the domain model.

The specification also emphasized extensible object-oriented design, including the Open–Closed Principle and design patterns for behavior that changes with seasons and employee satisfaction policies.

The project was **not developed entirely from scratch**. The course supplied an application skeleton and the `po-uilib` interaction framework. Some presentation-layer commands and domain classes were provided either completely or partially and had to be completed and integrated with the implemented domain logic.

## Domain model

The application includes the following main concepts:

- **Animals and species** — animals belong to species, live in habitats and maintain a health history.
- **Habitats and trees** — habitats contain animals and trees and can influence individual species positively, negatively or neutrally.
- **Employees** — keepers are responsible for habitats, while veterinarians are responsible for species.
- **Vaccines** — vaccines target one or more species and every administration is recorded.
- **Seasons** — trees change behavior throughout the seasonal cycle and contribute to habitat maintenance effort.
- **Satisfaction** — the application computes satisfaction for animals and employees according to the project rules.

## Persistence and data import

Application state can be saved and restored through Java object serialization. The system can also be initialized from structured text files describing species, trees, habitats, animals, keepers, veterinarians and vaccines.

## Repository structure

```text
Veterinary-Hotel/
├── project/             Final submitted project
│   ├── hva/
│   │   ├── app/         Presentation layer and commands
│   │   └── core/        Domain model and business logic
│   │   └── pt/tecnico/uilib/ Course-provided UI framework
│   └── META-INF/
├── docs/
│   └── uml-design.pdf   UML document from the first project delivery
├── .gitignore
└── README.md
```

The `project/` directory preserves the contents of the **final submitted JAR**, including both `.java` source files and `.class` files.

## Project deliveries

The academic project was developed across three deliveries:

1. **UML / domain modelling** — initial object-oriented design of the application.
2. **Intermediate implementation** — implementation of the application based on the provided skeleton.
3. **Final implementation** — final submitted version, preserved in `project/`.

Only the UML document and the final implementation are included here. The intermediate submission is kept outside the repository because the final delivery supersedes it.

## Technologies and concepts

- Java
- Object-Oriented Programming
- Layered architecture
- Domain modelling / UML
- Java serialization
- Exception handling
- File parsing and structured data import
- Design principles and patterns
- `po-uilib` course interaction framework

## Academic context

This repository is a portfolio-oriented archive of the submitted academic work. Course-provided skeleton/framework code remains present where it formed part of the original submission, and the repository should therefore not be interpreted as code written entirely from scratch by the students.

The original course statement is intentionally not redistributed in this repository.
