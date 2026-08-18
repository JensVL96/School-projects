# tdt4250.dict3.gogo bundle

This bundle is part of the modular and flexible variant 3 of the [tdt4250.dict project](../README.md), and was created using Component template. It provides commands to the [Gogo shell](https://felix.apache.org/documentation/subprojects/apache-felix-gogo.html) for managing dictionaries, by implementing an appropriate component.

See https://enroute.osgi.org/FAQ/500-gogo.html for more about the Gogo shell.

## Packages

- **tdt4250.dict3.gogo**: The Gogo shell command component and supporting classes. 

## Design

A Gogo shell command component is an ordinary Java class annotated as a component with specific properties (one **osgi.command.scope** property and one or more **osgi.command.function** properties). The **osgi.command.function** property values are the names of the methods that will be available as commands (command arguments will be converted to method arguments).

There are commands for listing dictionaries (all **Dict** service components), looking up words and adding and removing dictionaries, by registering or unregistering **Dict** service components. A custom **Dict** implementation is used for new dictionaries. These may load words from a file and include additional words.

OSGI only allows manually unregistering components that have been manually created (for which you have a **ServiceReference** or **Configuration**), so you can only remove dictionaries that have been manually added.
