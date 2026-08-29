# TDT4250 — Advanced Software Design

Coursework for **TDT4250 Advanced Software Design** at NTNU — a course on
model-driven software engineering: metamodeling, domain-specific languages,
code generation, and model validation using the Eclipse Modeling Framework (EMF).

## Assignments

### Assignment 1 — Ecore Metamodel of NTNU Informatics Course Selection

**Task:** Design an Ecore metamodel capturing a real domain, define validation
constraints over it, generate the EMF Java implementation, and build a valid
model instance.

**What I built:** A metamodel of the course-selection structure for the MSc in
Informatics at NTNU — `Department`, `Specialization`, `Semester`, and `Course`,
with enums for course `Status` (compulsory, elective, etc.) and `StudyLevel`.
`Department` owns the specializations, and courses derive from it as well, since
a course can be taken by students from other departments. Each specialization
contains its semesters, and each semester contains the course options with their
per-course details. Added OCL-style invariants (every specialization accounts
for all its semesters; a course selection meets the required number of compulsory
courses), generated the EMF Java model code (factory, package, impl, and
validator classes), and built a sample instance for a first-year Interaction
Design, Game & Learning Technology student.

**Key concepts:** metamodeling, Ecore/EMF, containment vs. references,
model validation, code generation
**Language/tools:** Eclipse EMF, Ecore, Java, Sirius (`.aird` diagrams)
