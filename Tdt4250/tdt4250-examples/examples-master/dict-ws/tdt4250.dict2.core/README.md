# tdt4250.dict2.core bundle

This bundle is part of variant 2 of the [tdt4250.dict project](../README.md), and was created using the API template. It contains the logic for dictionary search, i.e. the servlet-independent part.

## Packages

- **tdt4250.dict2.core**: The core interfaces and classes that will be used by dictionary users and implementations. There's a **Dict** interface, a **DictSearch** class managing search within a set of **Dict**s and a **DictSearchResult** class for the search result.
- **tdt4250.dict2.no**: An implementation of the **Dict** interface that loads a Norwegian dictionary from a file, with supporting classes.
