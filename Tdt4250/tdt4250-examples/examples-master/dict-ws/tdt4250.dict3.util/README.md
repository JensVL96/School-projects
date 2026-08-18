# tdt4250.dict3.no bundle

This bundle is part of variant 3 of the [tdt4250.dict project](../README.md), and was created using the API template. It contains utility classes useful for dictionary implementations, as well as a **Dict** implementation that can be configured using the **ConfigAdmin** OSGi service.

## Packages

- **tdt4250.dict3.no**: Utility classes useful for dictionary implementations.


## Design

The main class of this bundle is the **WordsDict** implementation of the **Dict** service. Both the name and the words can be configured using properties, and the words may be both loaded from a **URL** (or as a special case a resource in a bundle) or from a property. Thus, by creating a **Configuration** with the appropriate properties, a new **Dict** may be created and automatically injected into **Dict** service consumers.

The **configurationPid** is **tdt4250.dict3.util.WordsDict**, i.e. the class name, and is used as the **factoryPid** when creating new **Configuration** objects.

The properties are:
- **dictName**: the name of the new dictionary
- **dictResource**: the URL or location of a bundle resource (format <bundle>#<resource-path>, e.g. tdt4250.dict3.no#/tdt4250/dict3/no/nb.txt)that contains the words
- **dictWords**: a space-separated list of the words

Constants for these are defined in the **WordsDict** class.
