## Directories
- **RxDemo1** - Basics of RxJava and Operators written in Kotlin
- **ToDoListApp** - Basic Integration of RxJava and RxBinding into an existing Project written in Java
- **PostApp** - Simple POST made via Retrofit written in Kotlin
- **TMDBClient** - Simple Popular Movies Display Grid using Retrofit written in Kotlin
- **TMDBClientRx** - Simple Popular Movies Display Grid using Retrofit and RxJava written in Kotlin
- **UnitTestingAndroidCourse** - Fundamentals of JUnit, Mockito and Android JUnit testing written in Java
- **CustomView** - Simple XML based custom view with styleable attributes written in Kotlin

## Code Snippets



#### Dropdown or spinner in a view
```
//inside a layout file:
<Spinner
   android:id="@+id/fragment_support_subject_spinner"
   android:layout_width="fill_parent"
   android:layout_height="wrap_content"
/>

//inside values/lists.xml:
<string-array name="support_subjects">
   <item>Select a Subject</item>
   <item>I wasn\'t credited for an offer I completed</item>
   <item>An offer was misleading</item>
   <item>Other</item>
</string-array>

ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.support_subjects, android.R.layout.simple_spinner_dropdown_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
mSubjectSpinner.setAdapter(adapter);

//AdapterView.OnItemSelectedListener implementation for Spinner
public void onItemSelected(AdapterView parent, View view, int pos, long id)
{
}
```

#### Making an HTTP Get request with HttpsURLConnection (this is not in background thread)
```
String completeURL = String.format(“”);
URL url = new URL(completeURL);
JSONObject response = new JSONObject();

HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
int responseCode = urlConnection.getResponseCode();

if (responseCode == HttpsURLConnection.HTTP_OK)
{
   String responseString = readStream(urlConnection.getInputStream()); 
   response = new JSONObject(responseString);
}

private String readStream(InputStream in) {
   BufferedReader reader = null;
   StringBuffer response = new StringBuffer();
   try {
       reader = new BufferedReader(new InputStreamReader(in));
       String line = "";
       while ((line = reader.readLine()) != null) {
           response.append(line);
       }
   } catch (IOException e) {
       e.printStackTrace();
   } finally {
       if (reader != null) {
           try {
               reader.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
   return response.toString();
}
```

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

#### Custom view included in xml layout
```
public class com.packagename.InputPaperView extends RelativeLayout
{    
    public InputPaperView(Context context)
    {
        super(context);
    }

    public InputPaperView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public InputPaperView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() 
    {
        super.onFinishInflate();
    }
}

//view_paper_input.xml
<?xml version="1.0" encoding="utf-8"?>
<com.packagename.InputPaperView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/view_paper_bg"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:scaleType="center"
        android:src="@drawable/paper"/>

</com.packagename.InputPaperView>

//in some other layout file
<include android:id="@+id/view_edittext"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_centerInParent="true"
        layout="@layout/view_paper_input"/>
```

#### Android animation durations
getResources().getInteger(android.R.integer.config_longAnimTime)
getResources().getInteger(android.R.integer.config_mediumAnimTime)
getResources().getInteger(android.R.integer.config_shortAnimTime)

#### Custom sub classed handler
```
private Handler mAnimationHandler;

mAnimationHandler = new AnimationHandler(Looper.getMainLooper());
mAnimationHandler.sendEmptyMessageDelayed(WHAT_ID, 200);

private class AnimationHandler extends Handler
{    
        private int movementRange;
        public AnimationHandler(Looper looper)
        {
            super(looper);
            movementRange = getResources().getInteger(R.integer.movement_range);
        }

        @Override
        public void handleMessage(Message msg) 
        {

        }
}
```

#### Generate random number
```
Random rand = new Random();
//in the half-open range [0, n)
rand.nextInt(n); 
```

## Links
- Time conversions (days, minutes, seconds, milliseconds): http://stackoverflow.com/a/24285615/550393
- Color transparency: https://stackoverflow.com/a/17239853
- Format a number to add comma at thousand position: http://stackoverflow.com/a/5323787
- Difference between gravity and layout_gravity: http://stackoverflow.com/a/3482757
- Set height or width of a view: http://stackoverflow.com/a/5042278/550393
- Showing soft keyboard: http://stackoverflow.com/a/8080621/550393, http://stackoverflow.com/a/18237942
