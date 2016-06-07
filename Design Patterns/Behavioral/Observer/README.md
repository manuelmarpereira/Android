**Android** - Observer Pattern
==================================================

Observer Pattern is used when there is one to many relationship between objects such as if one object is modified, its dependent objects are to be notified automatically. Observer pattern falls under behavioral pattern category. <br>

In this simple example you can select a team (Paris SG or Chelsea FC) and simulate goals. You will receive notifications of team goals that you are following.

![alt tag](https://dl.dropboxusercontent.com/u/110418380/git/android/design_patterns/observer_screen.png)


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
		
![alt tag](https://dl.dropboxusercontent.com/u/110418380/git/android/design_patterns/observer_ex.PNG)

Our demo class is the MainActivity, will use Subject and concrete class objects to show our observer pattern in action.


<script type="text/javascript">
    function openActivity(activity) {
        Android.openActivity(activity);
    }
	
	var foo = function() {Android.addButtons();};
	foo();
</script>


