# Transport example

This modeling example is based on the following case description

## Domain 
A transportation company has a fleet of vehicles (trucks etc). each with a certain capacity (simplified to weight). These transport goods (also with a certain weight) til various destinations, that are identified by their geo-location. For each vehicle a plan is made for which goods to pick up and transport to their destinations, i.e. a route to drive. During the transportation, the vehicle position and fuel level is tracked (data with timestamp is regularly posted). From this you can calculate speed and efficiency. The actual route should include the destinations making up the planned route.

## Purpose

We wish to make a system for planning routes and to visualize the actual route, using EMF and related frameworks.

## The model

- **Transportation**: Container object for **Vehicle** and **GeoLocation** objects
- **GeoLocations**: Container object for **GeoLocation** objects
- **Vehicle**: The things transporting **Goods**. Has a certain capacity.
- **VehicleRoute**: Transportation missions for **Vehicles**, with a planned and actual route for **Goods**
- **Goods**: What a **Vehicle** transports in a **VehicleRoute**
- **TrackingEvents**: elements of an actual route, relating a timestamp to a **GeoLocation** for a **Vehicle** in a **VehicleRoute**
