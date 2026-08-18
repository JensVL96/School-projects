# tdt4250.emf project

This OSGi project is an example of how to use an EMF model developed using Eclipse PDE in an OSGi bundle (project) managed by Bnd(tools). The case is a REST API serving an EMF model instance, see [below](#emf-rest-in-osgi).   

## Setup

To make your own OSGi project with a similar structure, it's best to start with a fresh Eclipse workspace, create a Bnd OSGi Workspace there and the bundles one by one, making sure to "guess" the right template to use. To be able to glance at this example while working on your own, open the example workspace in a *different* Eclipse installation, since it's difficult to open the same Eclipse one different workspaces simultaneously.  

To run the project, you should open the launch.bndrun file in the servlet bundle, make sure it resolves and use **Run OSGi**. You can not yet try various commands in the gogo shell... You can try the servlet using **http://localhost:8080/data/sample** in a browser, which will show the root object of a sample EMF resource.

## EMF REST in OSGi

This example shows how to serve an EMF instance in a REST API. There's two neat things with this example:

* the REST API servlet is generic, so it can serve any EMF model instance, optionally guided by model annotations
* the EMF project bundles are built by Eclipse PDE (outside this OSGi project), and used here

### Generic EMF REST servlet

You can think of a REST API as a way of navigating from a set of root objects to some objects of interest and either reading or operating on them. Navigation is done along associations (references in Ecore terminologi) and you can typically filter and select while navigating. The result is typically serialized back as JSON.

If you're doing this in pure Java, you can use a combination of reflection and annotations to do this generically. The **tdt4250.emf.*** bundles provide the same for EMF: generic REST API access to any EMF model instance driven by the model:

* **tdt4250.emf.servletsupport**: Service interfaces for various aspects of the the REST API, like navigating along associations, applying a final operation and serializing the result.
* **tdt4250.emf.servletsupport.impl**: Generic implementation of the service interfaces
* **tdt4250.emf.servlet.**: The end-point that decodes a request and uses the services. It supports any set of named resources, so the first path segment is the resources to access, and the rest is the REST path and optional query/operation.

### Making EMF model bundles available for OSGi

To test the generic REST support, a resource allocation model (for university staff and courses) is used. The corresponding EMF model project (in general PDE bundle/plugin projects) is outside and in the same folder as the OSGi workspace. The layout is as follows

* **examples** - the git repo folder
  * **dict-ws** - the OSGi workspace
    * **cnf** - OSGi workspace configuration project
    * **tdt4250.emf.*** - bundle project providing REST servlet
    * **tdt4250.ra.resource - bundle providing a sample EMF resource (instance of ra model) to serve
  * **tdt4250.ra** - EMF resource allocation model project
  * **tdt4250.ra.feature** - features to publish in p2 repository
  * **tdt4250.ra.repository** - p2 repository project

The key here is how we use the feature and repository projects to build a p2 repo that the Bnd Workspace can use. This makes bundles from plugin projects managed by Eclipse PDE available for use in OSGi projects managed by Bnd(tools). With the current setup the p2 repo must be manually built by opening the **site.xml** file in the repository project and using the **Build** button.

The **build.bnd** file in the **cnf** folder configures the available repositories, including the p2 repositories for EMF, AQL and our locally built resource allocation model bundle. The reference to the latter is relative, so the location of the repository project is important.
