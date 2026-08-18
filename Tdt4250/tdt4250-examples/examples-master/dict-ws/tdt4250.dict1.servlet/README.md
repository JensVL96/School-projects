# tdt4250.dict1.servlet bundle

This bundle is monolithic variant of the [tdt4250.dict project](../README.md), and was created using the Servlet Component template. It contains both the logic for dictionary search and the **Servlet**, i.e. it is unmodular. It's also inflexible, since the set of dictionaries is hard-coded into the **Servlet** implementation class.

## Packages

- **tdt4250.dict1.core**: The core interfaces and classes that will be used by dictionary users and implementations. There's a **Dict** interface, a **DictSearch** class managing search within a set of **Dict**s and a **DictSearchResult** class for the search result.
- **tdt4250.dict1.no**: An implementation of the **Dict** interface that loads a Norwegian dictionary from a file, with supporting classes.
- **tdt4250.dict1.servlet**: The **Servlet** component (i.e. implementation annotated with **@Component**) implementing a web service for searching a set of dictionaries (currently just the Norwegian one). 
