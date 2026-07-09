# Contributing to J2DOCX

**J2DOCX is designed to remain lightweight, predictable, and specification-oriented. Every contribution should help preserve these qualities.**

First of all, thank you for your interest in contributing to **J2DOCX**!

Whether you want to report a bug, improve the documentation, write tests, or implement a new feature, your contribution is highly appreciated.

The goal of J2DOCX is to provide a lightweight, modern, and easy-to-use Java library for creating DOCX documents. To keep the project consistent and maintainable, please read the following guidelines before contributing.

---

# Before You Start

If you are planning to implement a significant new feature or make a substantial architectural change, please open an Issue first.

This allows the implementation to be discussed before development begins and helps avoid duplicate work or changes that do not align with the project's direction.

Small bug fixes, documentation improvements, and typo corrections can be submitted directly as Pull Requests.

---

# Development Environment

## Requirements

* Java 21 or later
* Gradle
* Git

Clone the repository:

```bash
git clone https://github.com/RecoverDev/J2DOCX.git
cd J2DOCX
```

Build the project:

```bash
./gradlew clean build
```

Run all tests:

```bash
./gradlew test
```

---

# Testing

Every new feature should include appropriate tests whenever possible.

Before submitting a Pull Request, please ensure that:

* all existing tests pass;
* new functionality is covered by tests;
* no existing behavior has been unintentionally changed.

Well-tested code is significantly easier to review and maintain.

---

# Coding Guidelines

Please follow standard Java coding conventions.

In particular:

* Use meaningful class and method names.
* Keep classes focused on a single responsibility.
* Write JavaDoc for all public API.
* Prefer immutable objects whenever practical.
* Avoid unnecessary complexity.
* Keep methods concise and readable.
* Avoid introducing additional dependencies unless they provide significant value.
* Preserve backward compatibility whenever possible.

Readable code is preferred over clever code.

---

# Design Principles

J2DOCX follows several architectural principles. Every contribution should respect these ideas.

## Simplicity First

The API should remain simple, expressive, and intuitive.

Developers should be able to understand the purpose of an API by reading it.

If a feature requires complicated usage, the design should be reconsidered.

---

## Fluent API

Whenever appropriate, new functionality should integrate naturally with the existing fluent API.

The goal is to make document creation feel natural and readable.

---

## Builder-Oriented Design

Objects with multiple optional parameters should prefer builders or fluent configuration instead of large constructors.

---

## Minimal Dependencies

J2DOCX intentionally keeps external dependencies to a minimum.

Please avoid introducing new libraries unless there is a strong technical reason to do so.

---

## OpenXML First

Generated documents should follow the OpenXML specification as closely as possible.

If Microsoft Word accepts an invalid document but the specification does not, the specification takes priority.

Correctness is more important than relying on undocumented behavior.

---

## API Stability

The public API represents the contract with library users.

Internal implementation may evolve over time, but breaking changes to the public API should be carefully considered and discussed before implementation.

---

## No Feature Bloat

Every new feature should solve a real problem.

Features should not be added simply because another library provides them.

Keeping the API focused is one of the project's primary goals.

---

## Maintainability Over Cleverness

Future contributors should be able to understand the code without extensive explanation.

Simple, explicit solutions are preferred over overly abstract or highly optimized implementations unless optimization is clearly justified.

---

# Commit Messages

Please write clear and descriptive commit messages.

Good examples:

```
Add support for table indentation

Fix paragraph serialization

Improve XML namespace handling
```

Avoid vague commit messages such as:

```
fix

changes

update
```

---

# Pull Requests

When submitting a Pull Request, please ensure that:

* the Pull Request focuses on one logical change;
* the code follows the project's coding style;
* all tests pass;
* documentation is updated if necessary;
* public API changes have been discussed beforehand.

Smaller Pull Requests are usually easier to review and merge.

---

# Reporting Issues

When reporting a bug, please include as much relevant information as possible:

* Java version;
* operating system;
* Gradle version;
* a minimal reproducible example;
* expected behavior;
* actual behavior;
* stack trace (if applicable).

This greatly helps reproduce and resolve the issue.

---

# Feature Requests

Feature requests are always welcome.

When suggesting a new feature, please describe:

* the problem it solves;
* how it would be used;
* why existing functionality is insufficient.

The best feature requests explain the motivation behind the proposal, not only the desired API.

---

# Code of Conduct

Please be respectful, constructive, and professional in all discussions.

Healthy technical discussions are encouraged, but mutual respect is expected from everyone participating in the project.

---

# Thank You

Thank you for helping improve J2DOCX.

Every contribution—whether it is code, documentation, testing, or feedback—helps make the project better for everyone.
