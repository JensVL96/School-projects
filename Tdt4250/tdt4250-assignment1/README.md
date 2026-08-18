# TDT4250-Assignment1

Authored by Jens Christian Valen Leynse


## The webpage

I chose to model the Ecore model from the webpage about selecting the courses for the master program in Informatics at NTNU. The link is ![here](https://i.ntnu.no/wiki/-/wiki/English/Selecting+Courses+for+Master+in+Informatics+%e2%80%93+IDI).

On the page you can see the course selection for the available spesializations for each master degrees.
<br/>
![Spesialization overview](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/d67827b677f14d104ce6e2243fbb3e88/overview.png)


The courses in these spesializations are then grouped into semesters by year and season.
<br/>
![Semester example](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/a56bd86716c56b973ea21f91c618a1e5/1Semester.png)


The last image explains what the different abbreviations mean and some added details about the courses.
<br/>
![Abbreviations and extras](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/c2703b9f836aa8cc010dafb6754fca54/explanation.png)


## The model

I structured the classes in the model like this:
<br/>
![Classes](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/4b254f03cb66dc356d22295b37e455ba/Structure.png)
<br/>
The reasoning behind the structure for the diagram is that webpage is structured mostly like a straight line. The department contains the spesializations, but also all courses are derived from this class, since the courses can also be taken by students from other departments. The spesializations then contains the different semesters, which then again contains the course options with all the added details for the course.

The class contents are then structured like this:
<br/>
![Contents](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/152f11f35668bec0542ccf98b5381f1c/Classes.png)

The class diagram:
![Class diagram](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/65cc060c846f91830425694eca3d23a9/Class_diagram.png)

## The instance

The model instance is based on the options for a first year master student who chose the spesialization Interaction Design, Game and Learning Technology:
<br/>
![Classes](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/4b753f3b7c156aef82325bafaad7a5f6/ImplInstance.png)


## Extra notes

I had some issues with Gitlab which didn't allow me to push, so I had to manually input all files (so I hope I got them all). 
I also had some issues with generating the source of the ecore genmodel. I got this long list of values instead of the normal "ra-name" + .util and .Impl. 
<br/>
![src error](https://gitlab.stud.idi.ntnu.no/TDT4250/h2022-ecore-assignments/tdt4250-assignment1/-/wikis/uploads/fd6e816e693319559ad8e20c28507fca/example.png)
