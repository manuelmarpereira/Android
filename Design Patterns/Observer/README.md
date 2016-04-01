**Android** - Observer Pattern
==================================================

Observer Pattern is used when there is one to many relationship between objects such as if one object is modified, its dependent objects are to be notified automatically. Observer pattern falls under behavioral pattern category. <br>


Implementation
==================================================

Observer pattern uses three actor classes.

		Subject
		Observer
		Client
		
Subject is an object having methods to attach and de-atach observers to a client object.
We have create the classes:
		
		Subject --> SubjectTeam
		Observer abstract class
		Concrete class --> GoalObserver
		
MainActivity, our demo class will use Subject and concrete class objects to show our observer pattern in action.

![alt tag](https://dl.dropboxusercontent.com/u/110418380/git/android/design_patterns/observer_ex.PNG)
