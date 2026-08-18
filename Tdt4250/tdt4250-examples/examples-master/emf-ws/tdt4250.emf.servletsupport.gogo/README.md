# tdt4250.emf.servletsupport.gogo bundle

See https://enroute.osgi.org/FAQ/500-gogo.html for more about the Gogo shell.

## Design

A Gogo shell command component is an ordinary Java class annotated as a component with specific properties (one **osgi.command.scope** property and one or more **osgi.command.function** properties). The **osgi.command.function** property values are the names of the methods that will be available as commands (command arguments will be converted to method arguments).

There are commands for listing resources (all **IResourceProvider** service components), performing requests and adding and removing resources, by registering or unregistering **IResourceProvider** service components. A custom **IResourceProvider** implementation is used for new resources. These may load EMF resources from a URL.

OSGI only allows manually unregistering components that have been manually created (for which you have a **ServiceReference** or **Configuration**), so you can only remove resources that have been manually added.
