# tdt4250.dict project

This OSGi project shows how an unmodular (monolithic, one-bundle) OSGi project is transformed into a modular, flexible one, in two steps. All three variants implements a simple web service for searching one or more dictionaries. Logically, all variants consist of a domain part, the dictionary- and search-related classes (among others, a **Dict** interface and its implementations) and the web service (a **Servlet**). Technically, the variants differ in how these parts are distributed among bundles and utilize the component mechanism.

## Setup

The root project and folder was created using the BndTools' Bnd OSGi Workspace wizard, and each bundle with the Bnd OSGi Project wizard. Which bundle template to use depends on the specific bundle, there are api, component and servlet bundles in this project.

To make your own OSGi project with a similar structure, it's best to start with a fresh Eclipse workspace, create a Bnd OSGi Workspace there and the bundles one by one, making sure to "guess" the right template to use. To be able to glance at this example while working on your own, open the example workspace in a *different* Eclipse installation, since it's difficult to open the same Eclipse one different workspaces simultaneously.  

To run the project, you should open the launch.bndrun file in the servlet bundle, make sure it resolves and use **Run OSGi**. You can try various commands in the gogo shell, e.g. **felix:lb** to see which bundles are in which states or **scr:list** to se the active components, and do a dictionary lookup using **http://localhost:8080/dict?q=master** in a browser.

## Variant 1

The first variant consists of the [tdt4250.dict1.servlet](tdt4250.dict1.servlet/README.md) bundle, which was created using the Servlet Component template. It contains both the logic for dictionary search and the **Servlet**, i.e. it is unmodular. It's also inflexible, since the set of dictionaries is hard-coded into the **Servlet** implementation class.

### Packages

- **tdt4250.dict1.core**: The core interfaces and classes that will be used by dictionary users and implementations. There's a **Dict** interface, a **DictSearch** class managing search within a set of **Dict**s and a **DictSearchResult** class for the search result.
- **tdt4250.dict1.no**: An implementation of the **Dict** interface that loads a Norwegian dictionary from a file, with supporting classes.
- **tdt4250.dict1.servlet**: The **Servlet** component (i.e. implementation annotated with **@Component**) implementing a web service for searching a set of dictionaries (currently just the Norwegian one). 

## Variant 2

The second variant consists of the [tdt4250.dict2.core](tdt4250.dict2.core/README.md) and [tdt4250.dict2.servlet](tdt4250.dict2.servlet/README.md) bundles. It's somewhat modular, since it's split into at least two bundles. It's also inflexiblie, since the set of dictionaries is still hard-coded into the Servlet implementation class.

### Bundles and packages

* **tdt4250.dict2.core**
  * **tdt4250.dict2.core**: Same core interfaces and classes of variant 1.
  * **tdt4250.dict2.no**: Same **Dict** implementation and supporting classes as in variant 1.
* **tdt4250.dict2.servlet**
  * **tdt4250.dict2.servlet**: The same **Servlet** component with hard-coded set of dictionaries (currently just the Norwegian one). 

## Variant 3

The third variant consists of the [tdt4250.dict3.api](tdt4250.dict3.api/README.md), [tdt4250.dict3.util](tdt4250.dict3.util/README.md), [tdt4250.dict3.no](tdt4250.dict3.no/README.md), [tdt4250.dict3.servlet](tdt4250.dict3.servlet/README.md) (and [tdt4250.dict3.rest](tdt4250.dict3.rest/README.md) and [tdt4250.dict3.gogo](tdt4250.dict3.gogo/README.md) bundles. It's modular, being split into bundles with minimal dependencies, and flexible, since new bundles can contribute additional dictionaries as components.

### Bundles and packages

* **tdt4250.dict3.api**
  * **tdt4250.dict3.api**: Same core interfaces and classes of variant 2.
* **tdt4250.dict3.util**:
  * **tdt4250.dict3.util**:
  * **tdt4250.dict3.util.internal**:
* **tdt4250.dict3.no**: Same **Dict** implementation as in variant 2.
  * **tdt4250.dict3.no**: Same **Dict** implementation and supporting classes as in variant 1.
* **tdt4250.dict3.servlet**
  * **tdt4250.dict3.servlet**: The same **Servlet** implementation, but now depending on (injected) **Dict** services to make it more flexible. 
* **tdt4250.dict3.gogo**
  * **tdt4250.dict3.gogo**: Gogo shell commands for managing **Dict** service components. 
