SweetCityWeb
============

Web application for SweetCity project. MSE HES-SO 2014-2015

Technologies
------------
This project use the following technologies :

* Google App Engine
* Google Cloud Endpoints
* Google Cloud Datastore
* Vaadin Framework

App Engine
----------
This project need to activate the following API :

* Google Cloud Datastore API

You have to create 2 OAuth identifiers :

* Android
  * Used to authorize access to the backend application from specific android application
* Web
  * Used to authorize access to the backend application from web

Project constants
-----------------
You need to create the class **ch.hesso.master.sweetcity.Project** :

```java
public static final String PROJECT_NUMBER = "";
public static final String PROJECT_ID = "";
public static final String PROJECT_URL = "";
public static final String PROJECT_NAME = "SweetCity";

public static final String WEB_CLIENT_ID = "";
public static final String ANDROID_CLIENT_ID_DEV = "";
public static final String ANDROID_CLIENT_ID_PROD = "";
public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";

public static final String VERSION = "1.0.0 ALPHA";
public static final String DATE = "05.01.2015";

public static final Locale LOCALE = new Locale("fr", "CH");
public static final TimeZone TIMEZONE = TimeZone.getTimeZone("Europe/Zurich");

public static final Logger LOG = Logger.getLogger(PROJECT_NAME);
```

Complete the missing fields in the code below.
