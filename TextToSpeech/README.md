**Android** - TextToSpeech
==================================================

![alt tag](http://appcrawlr.com/thumbs/app/icon/69/3100769.png)

This app example uses text to speech conversion (TTS) <br>
This technology can be used in situations where users are people with visual disabilities , or when you want to master the orality of a foreign language <br>

The TTS support for android appeared starting from version 1.6 (API 4)<br>

		
	Before using the TTS engine must be sure that it was properly initialized.
	
	In this case it implements is a OnInitListener call interface , and the onInit () method used when the engine startup is completed with certain state ( status variable ).<br>
	
	On Status is set to linguegem being used by dométodo SetLanguage ( Locale loc ) with the codes of countries composed of two letters.<br>
	
	If the language you want is not available , look in the Play Store . In this example we used the language of the United States ( US).<br>
	
	Finally the onDestroy() method is used to free the resources used by the TTS engine<br>
	
	
The TextToSpeech class has the speak() function.This method has some arguments, and there are two options when a new text is added to the queue.<br>

		QUEUE_ADD - add the new entry to the end of the queue<br>
		
		QUEUE_FLUSH - all entries already stored in the queue are deleted and replaced by a new entry
