## Directories
- **RxDemo1** - Basics of RxJava and Operators written in Kotlin
- **ToDoListApp** - Basic Integration of RxJava and RxBinding into an existing Project written in Java
- **PostApp** - Simple POST made via Retrofit written in Kotlin
- **TMDBClient** - Simple Popular Movies Display Grid using Retrofit written in Kotlin
- **TMDBClientRx** - Simple Popular Movies Display Grid using Retrofit and RxJava written in Kotlin
- **UnitTestingAndroidCourse** - Fundamentals of JUnit, Mockito and Android JUnit testing written in Java
- **CustomView** - Simple XML based custom view with styleable attributes written in Kotlin

## Code Snippets

#### Size (width/height) or coordinates of a view are ready
```
final SeekBar seekBar = (SeekBar)findViewById(R.id.view_feelings_seekbar);
ViewTreeObserver viewTreeObserver = seekBar.getViewTreeObserver();
if (viewTreeObserver.isAlive())
{
viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    seekBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Log.d(TAG, "seek bar width: " + seekBar.getWidth());
                    Log.d(TAG, "seek bar width: " + seekBar.getX());
                }
});
}
```

#### Dynamic String Resource
```
<string name="time_in_seconds">%1$ds ago</string> 

//use xliff tag to mark non-translatable portion
<string name="promo_message">
    Please use the "<xliff:g id="promotion_code">ABCDEFG</xliff:g>" to get a discount.
</string>

<string name="countdown">
  <xliff:g id="time" example="5 days">%1$s</xliff:g> until holiday
</string>
```

#### Integer arrays from xml file
```
int[] levelValues = new int[3];
levelValues = getResources().getIntArray(R.array.level_easy);

//inside values/difficulty_level.xml:
<resources>
    <integer-array name="level_easy">
        <item>180</item>
        <item>315</item>
        <item>500</item>
    </integer-array>
</resources>
```

#### String arrays from xml file
```
TypedArray mTabTitles;
mTabTitles = getResources().obtainTypedArray(R.array.support_tabs);

//inside values/lists.xml:
<string-array name="support_tabs">
   <item>FAQ</item>
   <item>Offer History</item>
   <item>Support</item>
</string-array>
```

#### Shape drawable for rounded corner rectangle at one edge
```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
   android:shape="rectangle">
   <solid android:color="@color/save_color"/>
   <corners android:topRightRadius="6dp"
       android:bottomRightRadius="6dp"/>
</shape>
```

#### ImageView Scaletype
![imageview scaletype](https://github.com/tabishfayyaz/android-code/raw/master/images/scaletype.png)

#### Change button text color on press state 
```
//button would have following property for
android:textColor="@color/custom_btn_text"

//xml file referenced in above line under res/color folder
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
   <item android:color="#E6E6E6" android:state_pressed="true"/>
   <item android:color="@android:color/white"/>
</selector>
```

#### Declare integers e.g. # of gridview column or layout weight for different resolutions/dimensions 
```
//inside values/integers.xml
<resources>
   <integer name="grid_columns">1</integer>
   <item name="left_weight" format="float" type="integer">0.35</item>
   <item name="right_weight" format="float" type="integer">0.65</item>
</resources>
```

## Links
- Time conversions (days, minutes, seconds, milliseconds): http://stackoverflow.com/a/24285615/550393
- Color transparency: https://stackoverflow.com/a/17239853
