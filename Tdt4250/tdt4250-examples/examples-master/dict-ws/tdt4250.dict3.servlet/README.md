# tdt4250.dict3.servlet bundle

This bundle is part of variant 3 of the [tdt4250.dict project](../README.md), and was created using the Servlet Component template. It contains the **Servlet** component that implements the web service.

## Packages

- **tdt4250.dict3.servlet**: The **Servlet** component (i.e. implementation annotated with **@Component**) implementing a web service for searching a set of dictionaries. 

## Design

The **Servlet** component requires zero or more **Dict** services.

The web service is registered under the **dict** prefix and takes a **q** query parameter with the word to look up as its value, e.g. **/dict?q=master**. To limit look up to a specific dictionary, add that name to the path, e.g. **/dict/nb?q=master** looks up in the **nb** dictionary.
