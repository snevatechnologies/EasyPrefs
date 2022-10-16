# EasyPrefs

EasyPrefs is An Android library for easy access to Android Shared preferences<br>
I think this library will help developers to access shared prefs

Library Installation
Add jitpack.io to your root gradle file (project level) :
```
allprojects {
 	repositories {
 		...
 		maven { url 'https://jitpack.io' }
 	}
 }
```
 Add the dependency in your app build.gradle
 ```
 dependencies {
    implementation 'com.github.snevatechnologies:EasyPrefs:1.1'
   }
   ```
You need to initialize the EasyPrefs library inside your application class :
 ```
public class SnevaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EasyPrefs.initialize(getApplicationContext());
    }
}
 ```
How To Use
Storing Values
 ```
EasyPrefs.use().saveInt(key,value); // to save integer
EasyPrefs.use().saveFloat(key,value); // to save float

For Objects and Lists of Objects
EasyPrefs.use().saveObject(key,customObject); // to save object

EasyPrefs.use().saveObjectList(key,listOfCustomObjects); // to save objects list
 ```
Getting Values
 ```
EasyPrefs.use().getInt(key,defaultValue); // get integer
EasyPrefs.use().getFloat(key,defaultValue); // get float

For Objects and Lists of Objects
EasyPrefs.use().getObject(key,classType); // get object

EasyPrefs.use().getObject(key,Person.class); // you can get Model class like this

EasyPrefs.use().getObjectList(key,classType); // get objects list
 ```
Deleting Values
 ```
Remove value by Key
EasyPrefs.use().deleteValue(key)

clear all sharedPrefereces
EasyPrefs.use().clearPrefs();
 ```
Check Key exists or not
boolean isExists = EasyPrefs.use().isKeyExist(key)
```
Search Key in Shared Preferences
EasyPrefs.use().searchKey(key);
```
Storing introduction or Signed In values
EasyPrefs.use().setIntroduction(isFirstRun); // to save intro
EasyPrefs.use().setSignedIn(isSignedIn); // to save signed in
```
Getting introduction or Signed In values
EasyPrefs.use().getIntroduction(); // get intro
EasyPrefs.use().getSignedIn(); // get signed in value
