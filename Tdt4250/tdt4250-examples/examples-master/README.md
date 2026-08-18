# TDT4250 examples repository

This example repository currently has four examples, two EMF model project examples and two OSGi (bnd workspace) examples.

## EMF model projects

* **tdt4250.ra** projects - EMF model projects within resource allocation (of university staff and courses) domain
* **tdt4250.transportation** project - EMF model project within transportation domain

Together these projects exemplify various modeling features of ecore, including constraints using OCL. To try them out, import with **Import... > General > Existing Projects into Workspace**.

In addition, the tdt4250.ra.* projects are used in one of the OSGi examples.

## OSGi workspaces

* [dict-ws](dict-ws/README.md) - shows how an unmodular (monolithic, one-bundle) OSGi project is transformed into a modular, flexible one, in two steps.
* [emf-ws](emf-ws/README.md) - shows how to use EMF model projects (managed by Eclipse PDE) within an OSGi project (managed by Bnd)

To try them out, import with **Import... > Bndtools > Existing Bnd Workspace** (but only one at a time). Before importing the emf-ws workspace, you should first import the **tdt4250.ra** projects and make sure the repository project is built.
