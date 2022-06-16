# SE-Lab6
 First RMI Project

 Author : Shantanu Mandpe 
 University : HAW Hamburg
 Course : Software Engineering 
 Professor : Prof. Dr. Wolfgang Renz

I implemented the UC of the Lab Project – Go to charging Station (update battery Indicator) which has Distributed Observer Design Pattern, Singleton, and Strategy Design Pattern. 
The RobotServer class starts the Server which the Main class then utilizes to then run the demonstrator which checks the vertical prototype application.

After running the program, we first must input a value between 1-3 which acts as a user input which decides which algorithm the program will run. This simulates, the action of choosing different task on the App as the robot uses different movement algorithms for different tasks. But, in the scope of the Demonstrator the three different algorithms just change the speed at which the robot moves on the pre-determined path.

In the demonstrator we have implemented a simulation in which the robot goes to the charging Station/ dock when the battery goes below 80%.  We can start the simulation by pressing the start button and stop it by pushing the stop button. The blue object is the Cleaning Robot, and the white object is the Dock. 

After starting the simulation, the console constantly updates the time passed, battery percentage, Current Robot Location and Dock location in real time. 

When the battery percentage goes below 60% the Object displaying Robot changes its color to Orange and to Red when battery percentage goes below 30%. 

After the Robot reaches the charging station it starts charging i.e., the Battery Percentage increases and when it reaches 100% the simulation stops. 

After clicking the display button another screen opens which simulates the App UI where we can check the current location of the robot, the current battery Percentage, and the current dock Location. We must keep clicking the display button to get the updated values. 

As for the RMI implementation, We use IHardwareController, IObservable, Observer, IRobotSystemDatabase as the Remote Interfaces for the Demonstrator. 

IObservable, Observer and IRobotSystemDatabase are used to implement the distribute Observer Pattern wherein the classes AppDisplay and SimPanel on the client-side observe the RobotSystemDatabase class from the server side.

We also use IHardwareController so that we can transfer the user input from the client-side Main class to the HardwareController class on the server side where the algorithm to be used is decided.   

