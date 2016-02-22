**Android** - Notification
==================================================

![alt tag](http://screenshots.en.sftcdn.net/en/scrn/69664000/69664734/android-desktop-notification-545222c26d768-100x100.jpg)

This example permits making a simple notification. <br>

This app asks your name and your location and creates a new android notification using NotificationManager class


		//Create new notification
		NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(NotificationActivity.this)
						.setSmallIcon(R.drawable.location_ic)
						.setContentTitle("Hello "+txtName.getText())//title
						.setContentText("You are in "+txtLocation.getText()+ " now!"); //content text

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build()); //notify

     


