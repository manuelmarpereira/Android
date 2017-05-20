**Android** - Network
==================================================

![alt tag](https://manuelmarpereira.herokuapp.com/git/wifi.JPG)

This example permits making a simple network availability verification. <br>

Using the ConnectivityManager Class its possible make a verification of network availability.
		
		ConnectivityManager cm;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        cm.getActiveNetworkInfo();

to get availability of WIFI connection:
		
		cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()
		
to get availability of MOBILE connection:

		cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()

     


