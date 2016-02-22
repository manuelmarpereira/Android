**Android** - Notification
==================================================

This example permits making a simple notification. <br>

This app asks your name and your location and creates a new android notification using NotificationManager class


		//Create new notification
		NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(NotificationActivity.this)
						.setSmallIcon(R.drawable.location_ic)
						.setContentTitle("Hello "+txtName.getText())//title
						.setContentText("You are in "+txtLocation.getText()+ " now!"); //content text

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build()); //notify

     


