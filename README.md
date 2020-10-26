## Directories
- **RxDemo1** - Basics of RxJava and Operators written in Kotlin
- **ToDoListApp** - Basic Integration of RxJava and RxBinding into an existing Project written in Java
- **PostApp** - Simple POST made via Retrofit written in Kotlin
- **TMDBClient** - Simple Popular Movies Display Grid using Retrofit written in Kotlin
- **TMDBClientRx** - Simple Popular Movies Display Grid using Retrofit and RxJava written in Kotlin
- **UnitTestingAndroidCourse** - Fundamentals of JUnit, Mockito and Android JUnit testing written in Java
- **CustomView** - Simple XML based custom view with styleable attributes written in Kotlin
- **ContactManager** - Demo of Room integration written in Java
- **ContactManagerRx** - Demo of Room integration using RxJava written in Java
- **ContactManagerMVVM** - Demo of MVVM with Room written in Java
- **TMDBClientMVVM** - Simple Popular Movies Display Grid using Retrofit, RxJava and MVVM written in Kotlin
- **DIDemo1** - Introduction to Dagger using Component, Module, Singleton, Interfaces written in Java
- **CountriesApp** - Display list of Countries & their flags using MVVM, RxJava, RetroFit, Dagger & Unit Testing written in Kotlin

## Code Snippets

#### Stop default activity start animation
```
Intent intent = new Intent(SourceActivity.this, DestinationActivity.class);
startActivity(intent);
overridePendingTransition(0, 0);
```
#### Full screen activity theme
```
<activity
            android:name=".SourceActivity"
            android:theme="@style/AppThemeFullscreen">
</activity>

//inside values/styles.xml:
<style name="AppThemeFullscreen" parent="android:Theme.Holo.NoActionBar.Fullscreen">
        <!-- Customize your theme here. -->
</style>
```

#### Parse arguments from activity bundle
```
Intent intent = getIntent();
Bundle extras = intent.getExtras();
if (extras != null)
{
int uiStep = extras.getInt(ARG_STEP, -1);
}
```

#### Start activity with arguments
```
private static String ARG_STEP = "STEP";
Intent intent = new Intent(SourceActivity.this, ImageActivity.class);
private static int SCREEN3 = 2;
intent.putExtra(ARG_STEP, SCREEN3);
startActivity(intent);
```

#### Array of strings to arraylist
```
String[] mStrings = {“String1”, “String2”, “String3”};
ArrayList<String> stringsList = new ArrayList<String>(Arrays.asList(mStrings));
```

#### Array of strings to list
```
String[] mStrings = {“String1”, “String2”, “String3”};
Arrays.asList(mStrings);
```

#### To format a date from string
```
/*
@param sourceString e.g. ”Fri, 04 Apr 2014 14:16:05 +0200”, "2014-09-04T15:51:46-0500"
@param sourceFormat e.g. "EEE, dd MMM yyyy HH:mm:ss Z", "yyyy-MM-dd'T'HH:mm:ssZ"
@param destinationFormat e.g. "MMM dd yyyy", "MM/dd/yyyy hh:mm:ss"
*/
public static String dateString(String sourceString, String sourceFormat, String destinationFormat)
{
        try {
            Date sourceDate = new SimpleDateFormat(sourceFormat).parse(sourceString);
            return new SimpleDateFormat(destinationFormat).format(sourceDate);
        }
        catch (ParseException pe)
        {
            return sourceString;
        }
}
```

#### Set a launcher activity in androidmanifest with no title
```
<activity
            android:name="co.tabish.fayyaz.swipedeletelistviewdemo.app.MainActivity"
            android:label="@string/title_activity_main"
            android:theme="@style/WithoutTitle">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
</activity>

//inside values/style.xml:
<style name="WithoutTitle" parent="AppTheme">
        <item name="android:windowNoTitle">true</item>
</style>

//horizontally center views inside a linear layout
<LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"       
        android:gravity="center_horizontal">
</LinearLayout>	
```

#### Hide soft keyboard inside a viewpager fragment
`((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mViewPager.getWindowToken(), 0);`

#### Hide soft keyboard inside a  fragment
`((InputMethodManager)getContext().getSystemService(getContext().INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getView().getWindowToken(), 0);`


#### Click listener on soft keyboard
```
mInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

                if (actionId == EditorInfo.IME_ACTION_GO)
                {
                    mCallback.onKeyboardGoPressed();
                }
                return false;
            }
        });
```

#### To highlight, color or bold a substring in textview
```
highlightColor = Color.rgb(54, 173, 72);
SpannableString spannedString = new SpannableString(“This is a demo string”);
spannedString.setSpan(new BackgroundColorSpan(highlightColor), startIndex, startIndex+lookupText.length(), 0);
spannedString.setSpan(new ForegroundColorSpan(Color.WHITE), startIndex, startIndex+lookupText.length(), 0);
spannedString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startIndex, startIndex+lookupText.length(), 0);
textView.setText(spannedString);
```

#### Send broadcast
```
Intent intent = new Intent(appContext, SessionStateReceiver.class);
intent.setAction(“SESSION_BROADCAST_ACTION”);
intent.putExtra(“MESSAGE”, SESSION_EXPIRED_MESSAGE);
context.sendBroadcast(intent);

Bundle extras = intent.getExtras();
String message = (String)extras.get(“MESSAGE”);
```

#### If ListView item click listener does not work after adding button
```
<Button  
android:id="@+id/list_item_button3"                       
            	android:onClick="onButtonClick"
            	android:focusable="false"
android:focusableInTouchMode="false"
/>
```

#### To see performance of a listview by printing its frames per second
```
/**
 * A utility custom list view that print frames per second while scrolling. The ideal frame rate should be 60 if its being shown too less 
 * then something is not right in the UI 
 */
public class FPSListView extends ListView
{
	private Toast fpsToast;b
	private long currentTime;
	private long prevTime;

	public FPSListView(Context context)
	{
		super(context);
		fpsToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	}

	public FPSListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		fpsToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		currentTime = SystemClock.currentThreadTimeMillis();
		long deltaTime = currentTime - prevTime;
		long fps = 1000 / deltaTime;
		fpsToast.setText("FPS: " + fps);
		fpsToast.show();
		prevTime = currentTime;
	}
}
```

#### Specify white color with alpha
`<color name="white_with_transparency">#80ffffff</color>`

#### Set html text
```
stepBText.setText(Html.fromHtml("Waiting...<br>Handshaking with the server"));

OR

<string name="allow_location_access">Allow &lt;b&gt;Cat Widget&lt;/b&gt; to access this device\'s location?</string>
allowLocationText.setText(Html.fromHtml(getString(R.string.allow_location_access)));
```

#### Open external app if installed on device otherwise open from play store
```
@param packageName e.g. com.concur.breeze (can be seen in an app play-store url)
private void launchApp(String packageName)
{
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
        }
        else
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse( "http://play.google.com/store/apps/details?id=" + packageName)));
        }
}
```

#### Prevent touch theft by parent from child view
```
public static void disableTouchTheft(View childView) {
    childView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            childView.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                childView.getParent().requestDisallowInterceptTouchEvent(false);
                break;
            }
            return false;
        }
    });
}
```

#### To prevent viewpager from receiving touch
```
mViewPager.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
```

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

#### Add custom font
![imageview customfont](https://github.com/tabishfayyaz/android-code/raw/master/images/customfont.png)
```
Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "idolwild.ttf");
EditText mInputEditText = (EditText)findViewById(R.id.view_paper_edittext);
mInputEditText.setTypeface(tf);
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
```
getResources().getInteger(android.R.integer.config_longAnimTime)
getResources().getInteger(android.R.integer.config_mediumAnimTime)
getResources().getInteger(android.R.integer.config_shortAnimTime)
```

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

#### To get list of avds on host machine
`android list avd`

#### To start emulator from command line
`emulator -avd <avd_name>`

#### To read file (css/js/html) as string
```
public static String getFileAsString(Context context, String filename)
{
	String fileString = "";
	try
	{
		InputStream is = context.getResources().getAssets().open(filename);

		int size = is.available();
		byte[] buffer = new byte[size];
		is.read(buffer);
		is.close();
		fileString = new String(buffer);
	} catch (IOException ioe)
	{
		ioe.printStackTrace();
	}
	return fileString;
}
```

#### Spinner on action bar navigation using custom arrayadapter
```
//class has to implements ActionBar.OnNavigationListener and @Override public boolean onNavigationItemSelected(int itemPosition, long itemId)
protected void onCreate(Bundle savedInstanceState)
    {

        NavSpinnerAdapter adapter = new NavSpinnerAdapter(this);

        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setListNavigationCallbacks(adapter, this);
        getActionBar().setSelectedNavigationItem(0);
        getActionBar().setTitle("");
    }

//inside a resource navigation.xml:
<string-array name="navigation_titles">
        <item>Item 1</item>
        <item>Item 2</item>
        <item>Item 3</item>
        <item>Item 4</item>
        <item>Item 5</item>
    </string-array>

    <string-array name="navigation_images">
        <item>@android:drawable/ic_menu_save</item>
        <item>@android:drawable/ic_menu_agenda</item>
        <item>@android:drawable/ic_menu_add</item>
        <item>@android:drawable/ic_menu_call</item>
        <item>@android:drawable/ic_menu_camera</item>
    </string-array>


private class NavSpinnerAdapter extends ArrayAdapter<NavigationItem>
    {
        public NavSpinnerAdapter(Context context)
        {
            super(context, android.R.layout.simple_spinner_item);

            String[] titles = context.getResources().getStringArray(R.array.navigation_titles);
            TypedArray images = context.getResources().obtainTypedArray(R.array.navigation_images);

            for (int index = 0; index < titles.length; index++)
                add(new NavigationItem(titles[index], images.getDrawable(index)));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView text = (TextView) super.getView(position, convertView, parent);
            text.setText(getItem(position).title);
            return text;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
                convertView = View.inflate(getContext(), R.layout.navigation_item, null);

            ImageView iv = (ImageView) convertView.findViewById(R.id.navigationItemImage);
            TextView tv = (TextView) convertView.findViewById(R.id.navigationItemText);

            iv.setImageDrawable(getItem(position).image);
            tv.setText(getItem(position).title);

            return convertView;
        }
    }

    public static class NavigationItem
    {
        public String title;
        public Drawable image;

        public NavigationItem(String title, Drawable image)
        {
            this.title = title;
            this.image = image;
        }
    }
```

#### Spinner on action bar navigation using arrayadapter
```
ArrayList<String> spinnerItems = new ArrayList<String>();
SpinnerAdapter spinnerAdapter = new ArrayAdapter(mContext, R.layout.news_spinner_dropdown_item, R.id.news_spinner_dropdown_item_text, spinnerItems);
getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
getActivity().getActionBar().setListNavigationCallbacks(spinnerAdapter, this);
getActivity().getActionBar().setSelectedNavigationItem(1);
```
	
#### Horizontal progress bar on top
```
<ProgressBar
        style="@android:style/Widget.Holo.Light.ProgressBar.Horizontal"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:indeterminate="true"
        android:indeterminateOnly="true"
        android:visibility="visible"
        android:layout_gravity="top" />
```

#### Options menu in fragment (programmatically)
```
//set setHasOptionsMenu(true); in onCreateView
public void onCreateOptionsMenu (Menu menu, MenuInflater inflater)
{
       menu.add(0, R.id.menu_news_global, 0, R.string.global);
       menu.add(0, R.id.menu_news_rnam, 0, empUnit.name);
       super.onCreateOptionsMenu(menu,inflater);
}

@Override
public boolean onOptionsItemSelected(MenuItem item)
{
       if (item.getItemId() == R.id.menu_news_global)
       {            
            return true;
       }
       else if (item.getItemId() == R.id.menu_news_rnam)
       {           
            return true;
       }
       return false;
}
```

#### Helper method for TAG
```
private final String TAG = Util.tag(getClass().getSimpleName());

//Util.java:
public static String tag(String tag)
{
        return "PREFIX_" + tag;
}
```

## Android Animation Notes

- BitmapFactory.Options (inSampleSize) for bitmap scaling
- requestLayout (should be done after adding/removing a view)
- 2 ways to do list view animations (ViewPropertyAnimator or view.setHasTransientState())	
- ValueAnimator for bouncing sheep (setRepeatCount, setRepeatMode, setInterpolator)
	- one can invalidate specific sub-areas of a screen View.invalid()
	ValueAnimator is the timing engine behind ViewPropertyAnimator/ObjectAnimator
- Property Animations (alpha, translate, rotate, scale) done using ObjectAnimator
	- ValueAnimator.REVERSE is used for reversing the animation back to original position
	- PropertyValuesHolder used for scale animation
	- AnimatorSet used for playing animations in sequence
	- ObjectAnimator or AnimatorSet can written in xml file as well (AnimatorInflater.loadAnimator())
- KeyFrame animation: AnimationDrawable can be created all in code (addFrame, setOneShot), what is used in momentous
- TransitionDrawable for simple cross fade (startTransition, reverseTransition)
- Window animation (default activity animation, translate, scale animation)
	- ActivityOptions.makeCustomAnimation(….).toBundle();
	- overridePendingTransition(custom animation xml);
	- ActivityOptions.makeScaleUpAnimation(….).toBundle();
	- ActivityOptions.makeThumbnailScaleUpAnimation(….).toBundle();
	- avoid doing custom animation for MAIN launch activity, you can do it for activities you launch in code
- Animation.RELATIVE_TO_PARENT, Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, TranslateAnimation, RotateAnimation, ScaleAnimation
- Bitmap Allocation: BitmapFactory.Options().inBitmap (bitmaps should be same size)
- Layout Transitions: while adding/removing custom views
	- creates a custom color view, android:animateLayoutChanges=“true” on LinearLayout for example (3.1) 
	- LayoutTransition transition = linearLayout.getLayoutTransition (4.1)
	  transition.enableTransitionType(LayoutTransition.CHANGING)
- Picture Viewer: all views have their own viewpropertyanimator (.animate())
	- .animate().withLayer() 
	- withEndAction
- Animating Multiple Properties in Parallel: 
	- wrong way is to use ValueAnimator e.g. ofFloat(0, 400) and do setTranslationX setTranslationY separately
	- good approach: view.animate().translationX(x).translationY(y)
	- if you can’t use ViewPropertyHolder (when animating things that aren’t properties on a view) 
	  then PropertyValueHolder with ObjectAnimator and ValueAnimator
	- ObjectAnimator.ofPropertyValuesHolder(view, property1, property2, property3).start();
- Curved Motion: Chet Haase has blogged about this as well, AnimatorPath, AnimatorPath.curveTo(….),
- Anticipation and Overshoot 1: OvershootInterpolator(10f), DecelerateInterpolator
		- .animate.setInterpolator(Overshooter)
- Anticipation and Overshoot 2: Demo of a animating user defined property (skewX)
- Squash and Stretch:	
	- ObjectAnimator.setRepeatMode(ValueAnimator.REVERSE), View.setPivotX, setPivotY
	- PropertyValuesHolder.ofFloat(View.SCALE_X, 2);		
- AccelerateInterpolator: start slow end faster
- DecelerateInterpolator: start fast end slow

## Dagger Notes

- Component (an interface) creates, stores our objects and provide them to us. You can also call it The Injector. It is aware of the dependency graph (DAG). By adding **@Component** Dagger will implement the interface and create all necessary code
- Name convention of implemented component interface: *DaggerComponentInterfaceName*
- In order for Dagger to instantiate a class, class constructor has to be marked with **@Inject**, remember that would also have the effect of injecting constructor params
- Generally we want to do constructor injection everywhere but it is not possible e.g. if root like an android Activity instantiation is not in our control so then we instantiate *DaggerComponentInterfaceName* to access injected object or use Field Injection but for that you need to give the Component access to parent object (e.g. Activity) via *inject* method
- Injection order in a class: Constructor, Fields & then Methods
- Field & Method injection are automatically executed if we also do a constructor injection (but this won’t happen in case of Android activity so we manually trigger it via Component)
- By putting **@Inject** on a constructor you are saying hey Dagger this is how you instantiate an object of this class but e.g. a third party class won’t allow that (as we don’t have access to code) so this is where **@Module** comes in which uses **@Provides**
- **@Provides** also helps to do any configuration on an object after instantiation 
- **@Module** is just a java class, used for providing/injecting objects in more complex cases
- specifying a Module in **@Component** annotation tells the component where to get a certain class instance from, you can specify multiple modules in {curly braces} & comma separate them
- you use @Binds (instead of **@Provides**) whenever you want to provide a concrete class against an interface return type, bind does not support any configuration since there is no method body
- if you need to provide arguments (which could be runtime) then you use *.builder()* on component instead of *.create()*. This way you have introduced State into your module
- with **@BindsInstance** (and **@Component.Builder**) we can get variables (e.g. int) into our dependency graph at runtime which has same effect as passing a runtime value to a module but with latter approach there is drawback of Dagger creating an additional instance to hold that state in Module instance. Values get associated/injected based on data type hence we use **@Named**. With **@BindsInstance** approach you can leave Module abstract
- You can annotate **@Binds**, **@Provides** with **@Singleton** in addition to the class itself. A singleton is specific to a component so if you want an application scope singleton then associate component with Application lifecycle
- Custom Scope are used to reduce the lifecycle of an object e.g. to a an Activity, Fragment etc.
- **@Documented** and **@Retention** are meta annotations for **@Scope**, don't matter much, you annotate an interface with **@Scope** to create a custom scope which makes it act like annotations. 
- The name of interface indicates what is the scope e.g. *PerActivity.java* (**@PerActivity**) and you annotate a class with that interface name (annotation) to modify class lifecycle scope BUT you have to change/limit scope by associating it with another component e.g. a Component for an Activity a Component for the application
- You annotate the new component (e.g. ActivityComponent.java) with the custom scope e.g. **@PerActivity** to establish that relationship
- You generally use **@Singleton** on an app component
- These annotations (e.g. **@Singleton**) can be written in multiple places e.g. with a provide method
- You can also provide Module a constructor which is sometimes used to provide application context (only available at runtime) to app component
- specifying a component dependency (*dependencies =*) in @Component annotation tells Dagger that this component needs the dependencies inside the other Component (e.g. App Component) to work. You will have to pass app component dependency (*.appComponent()*) when building the activity component and this is how we make the connection between two components so to use their objects together
- Ultimately we are responsible for managing the scopes and the way we do it is by instantiating and releasing our different dagger components in correct places. Components are just java objects who get released from memory when there is no reference to them.
- There is another way to connect components: **@Subcomponent**, a subcomponents knows about all the objects of its parent component e.g. if we turn our activity component into sub component it'll be able to access the dependency graph of the app component
- the parent component has to provide an accessor for the subcomponent
- **Component Dependencies:** Use this when you want to keep two components independent
- **Subcomponents:** Use this when you want to keep two components coupled


## General Notes

**_filter logcat by TAG:_** `adb logcat -s TAG`

**_compileSdkVersion_** - version of the API the app is compiled against

**_targetSdkVersion_** - not related to how your app is compiled or what APIs you can utilize but to indicate (upto & including) the version you have tested against

**_minSdkVersion_** - earliest version of Android that the app can run on

**ideally:** `minSdkVersion (lowest possible) <= targetSdkVersion == compileSdkVersion (latest SDK)`


`apply plugin: kotlin-kapt` to add annotation processors (see JSR 269) support in Kotlin then libraries such as Dagger can be used however the dependency has to be added using `kapt` configuration, (_kapt_: kotlin annotations processing tool)

`apply plugin: kotlin-android-extensions` to add support for view binding with Kotlin Android Extensions

`classpath 'com.android.tools.build:gradle:3.4.2'` Android Studio Gradle Plugin that provides integration of Gradle into Android Studio. The version will match the version of Android Studio being used

`distributionUrl=https\://services.gradle.org/distributions/gradle-5.1.1-all.zip` in _gradle-wrapper.properties_ which specifies the version of Gradle being used

- *Accelerometer*: for horizontal and lateral movements
- *Gyro*: for rotational speeds
- *Compass*: which way is north
- *Barometer*: for elevation


## Links
- Time conversions (days, minutes, seconds, milliseconds): http://stackoverflow.com/a/24285615/550393
- Color transparency: https://stackoverflow.com/a/17239853
- Format a number to add comma at thousand position: http://stackoverflow.com/a/5323787
- Difference between gravity and layout_gravity: http://stackoverflow.com/a/3482757
- Set height or width of a view: http://stackoverflow.com/a/5042278/550393
- Showing soft keyboard: http://stackoverflow.com/a/8080621/550393, http://stackoverflow.com/a/18237942
- Pass arguments to fragment: http://stackoverflow.com/a/17063584
- Enum int string conversion: http://stackoverflow.com/a/5021384/550393
- Declaring enums: http://stackoverflow.com/a/9246954
- Density calculator: http://density.brdrck.me/
- Command line tools: http://developer.android.com/tools/help/index.html, http://developer.android.com/tools/devices/emulator.html
- For reading jar files (JD-GUI): http://jd.benow.ca/
- To convert apk to jar: https://code.google.com/p/dex2jar/wiki/UserGuide
- Service vs. IntentService: http://stackoverflow.com/a/15772151/550393
- Content Provider vs SQLite: http://stackoverflow.com/a/13586952/550393, http://stackoverflow.com/a/4245672/550393
- Call method after delay using RxJava: https://stackoverflow.com/a/42127071
- Fun with Constraint Layouts https://medium.com/@nickand/fun-with-constraints-layout-8d4ea83166db
- Building interfaces with ConstraintLayout https://medium.com/androiddevelopers/building-interfaces-with-constraintlayout-3958fa38a9f7
- Google’s Maven Repository: https://maven.google.com/web/index.html
- MVN Repository: https://mvnrepository.com/
- RxJava: https://github.com/ReactiveX/Rxjava
- RxJava Docs: http://reactivex.io/RxJava/javadoc/io/reactivex/package-summary.html
- RxAndroid: https://github.com/ReactiveX/RxAndroid
- Android Tutorials by MindOrks https://mindorks.com/android-tutorial
- CodePath Android Guide https://guides.codepath.com/android
- Dagger: https://github.com/google/dagger
- Gradle search: https://gradleplease.appspot.com/
- Simple Date Format: https://developer.android.com/reference/java/text/SimpleDateFormat.html
- Formatter: https://developer.android.com/reference/java/util/Formatter.html
- API Distribution Stats: https://developer.android.com/about/dashboards/index.html
- Java to Kotlin Cheatsheet: https://github.com/MindorksOpenSource/from-java-to-kotlin
- Dagger Source https://www.youtube.com/playlist?list=PLrnPJCHvNZuA2ioi4soDZKz8euUQnJW65
- Animation Source: https://www.youtube.com/playlist?list=PLWz5rJ2EKKc86y1CjAlexivfvOms6_0NC

