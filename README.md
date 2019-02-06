# CircularProgressView
Customview for show circular progress and show text in the middle.

![alt text](https://github.com/gokul42252/CircularProgressView/blob/master/Screenshot_1549456610.png)

How to use in yout projects

Step 1 :  Add the JitPack repository to your build file

#### gradle 

```allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  ####  maven:
 
 ```
        <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  ```
Step 2. Add the dependency

####  gradle:
```
dependencies {
	        implementation 'com.github.gokul42252:CircularProgressView:1.0.0'
	}
  
```
####  maven :
```
	<dependency>
	    <groupId>com.github.gokul42252</groupId>
	    <artifactId>CircularProgressView</artifactId>
	    <version>1.0.0</version>
	</dependency>
  ```
Step 3. Adding to an Activity
```
 <com.ct.circularprogressview.CircularProgressView
            android:id="@+id/circle_progress_bar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:cv_base_arc_color="@color/colorPrimaryVeryLight"
            app:cv_label_fontName="SansationRegular.ttf"
            app:cv_arc_width="10dp"
            app:cv_progress_arc_color="@color/colorGraphRed"
            app:cv_progress_label_str="10:10:10"
            app:cv_progress_label_text_color="@color/colorPrimaryVeryLight"
            app:cv_progress_label_text_size="20sp"
            app:cv_progress_value_text_color="@color/colorWhite"
            app:cv_progress_value_text_size="20sp"
            tools:ignore="MissingConstraints"/>
         
   ```            
 Step 4. 
 ```
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cicView = findViewById<CircularProgressView>(R.id.circle_progress_bar);
        cicView.updateProgressWithProgressText("Time", 65f, "Date")
    }
```
    
    

