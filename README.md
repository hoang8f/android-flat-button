FButton [![Maven Central](https://maven-badges.herokuapp.com/maven-central/info.hoang8f/fbutton/badge.svg)](https://maven-badges.herokuapp.com/maven-central/info.hoang8f/fbutton)
=======
FButton is a custom Button of Android with "Flat UI" concept. FButton's design get inspiration from [designmono](http://designmodo.github.io/Flat-UI/). This library is very small and highly customizable.

Demo application on playstore:
https://play.google.com/store/apps/details?id=info.hoang8f.fbutton.demo

Screenshot
----------
![](https://raw.githubusercontent.com/hoang8f/android-flat-button/master/screenshot/screenshot.gif)

Including in your project
-------------------------
###Using Maven
FButton Library is pushed to [Maven Central](http://search.maven.org/#search|ga|1|fbutton), so you just need to add the following dependency to your `build.gradle`.

    dependencies {
        compile 'info.hoang8f:fbutton:1.0.5'
    }
    
###Manually
Copy/merge the following files to corresponding folder/file:
   + info/hoang8f/widget/FButton.java
   + res/values/attrs.xml
   + res/values/colors.xml
   + res/values/dimens.xml

Customizable attributes
-----------------------

|  Attribute    |   default value   | xml           |                 java                |
|---------------|-------------------|---------------|-------------------------------------|
| button color  |      #3eadeb      | buttonColor   | setButtonColor(int color)           |
| enable shadow |        true       | shadowEnabled | setShadowEnabled(boolean isEnabled) |
| shadow color  |  Automatically generated <br> from button color   | shadowColor   | setShadowColor(int color)           |
| shadow height |        4dp        | shadowHeight  | setShadowHeight(int height)         |
| corner radius |        8dp        | cornerRadius  | setCornerRadius(int radius)         |

Usage
-----
If the default values of custom attribues did not meet your requirement, you can easily re-config that attributes. This is sample code that you can refer. you can also browse demo app for more details.

### via xml (sample)
-  Define `xmlns:fbutton="http://schemas.android.com/apk/res-auto"` on root of your xml file

```xml
<info.hoang8f.widget.FButton
    ...
   fbutton:buttonColor="@color/color_concrete"
   fbutton:shadowColor="@color/color_asbestos"
   fbutton:shadowEnabled="true"
   fbutton:shadowHeight="5dp"
   fbutton:cornerRadius="5dp"
    ...
     />
```

### via code (sample)
```java
disabledBtn.setButtonColor(getResources().getColor(R.color.color_concrete));
disabledBtn.setShadowColor(getResources().getColor(R.color.color_asbestos));
disabledBtn.setShadowEnabled(true);
disabledBtn.setShadowHeight(5);
disabledBtn.setCornerRadius(5);
```

Color Swatches
--------------
For your convenience Swatches Preset by [designmono](http://designmodo.github.io/Flat-UI/) are also defined in this library
```xml
 //Color Swatches provided by http://designmodo.github.io/Flat-UI/
<color name="fbutton_color_turquoise">#1abc9c</color>
<color name="fbutton_color_green_sea">#16a085</color>
<color name="fbutton_color_emerald">#2ecc71</color>
<color name="fbutton_color_nephritis">#27ae60</color>
<color name="fbutton_color_peter_river">#3498db</color>
<color name="fbutton_color_belize_hole">#2980b9</color>
<color name="fbutton_color_amethyst">#9b59b6</color>
<color name="fbutton_color_wisteria">#8e44ad</color>
<color name="fbutton_color_wet_asphalt">#34495e</color>
<color name="fbutton_color_midnight_blue">#2c3e50</color>
<color name="fbutton_color_sun_flower">#f1c40f</color>
<color name="fbutton_color_orange">#f39c12</color>
<color name="fbutton_color_carrot">#e67e22</color>
<color name="fbutton_color_pumpkin">#d35400</color>
<color name="fbutton_color_alizarin">#e74c3c</color>
<color name="fbutton_color_pomegranate">#c0392b</color>
<color name="fbutton_color_clouds">#ecf0f1</color>
<color name="fbutton_color_silver">#bdc3c7</color>
<color name="fbutton_color_concrete">#95a5a6</color>
<color name="fbutton_color_asbestos">#7f8c8d</color>
```

Developed By
-------
Le Van Hoang (@hoang8f)

License
-------
       Copyright {2014} {Le Van Hoang}
    
       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at
    
           http://www.apache.org/licenses/LICENSE-2.0
    
       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.
       
