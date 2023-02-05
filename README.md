# Implement-the-Airport-Simulation
Implement your simulation in Java. 

1.0 The Problem

 This part of the project will require you to implement the airport simulation, as broken down in the sections below. The management of an airport thinks that the way in which the airport is operated means that incoming flights must spend too much time waiting for landing clearance. To evaluate the situation a simulation of the airport has been commissioned. This simulation will simply run with text output describing the events as they occur in the airport and collect a minimal amount of statistical data.
 
 2.0 Intention of project
 
Even if valuable to the owner, the simulation is not the main purpose of this assignment - indeed, if this was the case there are much better techniques for simulating than writing a concurrent program. The requirement of this assignment is to implement a program in which synchronization and communication takes place between several concurrent processes. It is intended to force you to solve (and not simply avoid) a range of interesting synchronisation problems. Asia Pacific Airport You have been tasked to simulate the operations of the airport. 

*********************Basic Requirements**************************

• There is only 1 runway for all planes to land and depart. 

•There can only be 2 airplanes on the airport grounds, including the runway. This is to ensure that the aircraft does not collide with another aircraft on the runway or gates 

• Once an aircraft obtains permission to land, it should land on the runway, coast to the assigned gate, dock to the gate, allow passengers to disembark, refill supplies and fuel, receive new passengers, undock, coast to the assigned runway and take-off. 

• Each step should take some time. 

• As the airport is small, there is no waiting area on the ground for the planes to wait for a gate to become available.

![image](https://user-images.githubusercontent.com/124484779/216808904-da9d5946-0902-48c5-89dc-97ab55dc5b96.png)

***********************Additional Requirements**************************** 


These events should happen concurrently: - 
-Passengers disembarking/embarking from the 2 gates.

- Refill supplies and cleaning of aircraft As there is only 1 refuelling truck, this event should happen exclusively: 

- Refuelling of aircraft 
A congested scenario should be simulated where 2 planes are waiting to land while the 2 gates are occupied, and a 3rd plane comes in with fuel shortage, requiring emergency landing. 

State your assumptions and how you will implement them. 

The Statistics: 

At the end of the simulation, i.e., when all planes have left the airport, the ATC manager should do some sanity checks of the airport and print out some statistics on the run. The result of the sanity checks must be printed. You must 

• Check that all gates are indeed empty. 

• Print out statistics on 

- Maximum/Average/Minimum waiting time for a plane.

- Number of planes served/Passengers boarded.

Deliverables: 

For this exercise, you are to model the ATC scenario and design a Java program to simulate activity for the airport: 

• Altogether, 6 planes should try to land at the airport. 

• Use a random number generator, so a new airplane arrives every 0, 1, 2, or 3 seconds. (This might be accomplished by an appropriate statement sleep (rand.nextInt(3000));

• Assume each plane can accommodate maximum 50 passengers. 

• Assume passengers are always ready to embark and disembark the terminal (i.e., no capacity issues inside the passenger terminals)



