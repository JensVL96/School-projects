# TDT4165 — Programming Languages

Coursework for NTNU's Programming Languages course. The course moves through
multiple paradigms and languages — declarative and functional programming in
**Oz** (the Mozart system), plus work in **Scala** and **Prolog**. The
assignments preserved here are the Oz ones.

## Assignment 1 — List Processing Fundamentals

Recursive functions over lists built from scratch: `Length`, `Take`, `Drop`,
`Append`, `Member`, and `Position`, using pattern matching on head/tail. Core
functional-programming primitives without relying on library helpers.

*Recursion · pattern matching · list processing · Oz*

## Assignment 2 — Stack Calculator Language (Lexer + Interpreter)

A small stack-based calculator language (reverse-Polish style): a lexer that
splits input into lexemes, a tokenizer mapping them to typed tokens (operators,
numbers, and commands like print/duplicate/invert/clear), and an interpreter
that evaluates the token stream against a stack.

*Lexing · tokenizing · interpreters · DSLs · stack machines*

## Assignment 3 — Higher-Order & Lazy Functions

Higher-order programming: fold-based reimplementations of `Length` and `Sum`,
a quadratic-equation solver using procedural abstraction, and a lazy number
generator — with written analysis of why procedural abstraction and laziness
are useful.

*Higher-order functions · fold · procedural abstraction · lazy evaluation*

> **Note:** The course also included assignments in Scala and Prolog, which are
> not preserved in this repository.
