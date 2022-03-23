[![](https://jitpack.io/v/datalogic/androidpos-config.svg)](https://jitpack.io/#datalogic/androidpos-config)
# Description
This project contains the configuration help module (library) for the AndroidPOS library.


# How to use the library
Add in your root build.gradle at the end of repositories:
~~~gradle
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
~~~
Add the following dependency to your gradle file:
~~~gradle
    dependencies {
	        implementation 'com.github.datalogic:androidpos-config:0.1-alpha'
	}
~~~