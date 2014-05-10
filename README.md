FButton
=======
FButton is a custom Button of Android with "Flat UI" concept. FButton's design get inspiration from [designmono](http://designmodo.github.io/Flat-UI/). This library is very small and highly customizable.

Screenshot
----------
![](https://raw.githubusercontent.com/hoang8f/android-flat-button/master/screenshot/screenshot.gif)

Including in your project
-------------------------
###Using meven
Deploying...

###Manually
Copy/merge the following files to corresponding folder/file:
   + info/hoang8f/widget/FButton.java
   + res/values/attrs.xml
   + res/values/colors.xml
   + res/values/dimens.xml

Customizable attributes
-----------------------

|               |   default value   | xml           |                 java                |
|---------------|-------------------|---------------|-------------------------------------|
| button color  |      #3eadeb      | buttonColor   | setButtonColor(int color)           |
| enable shadow |        true       | shadowEnabled | setShadowEnabled(boolean isEnabled) |
| shadow color  |  Auto generated <br> from button color   | shadowColor   | setShadowColor(int color)           |
| shadow height |        4dp        | shadowHeight  | setShadowHeight(int height)         |
| corner radius |        8dp        | cornerRadius  | setCornerRadius(int radius)         |

Usage
-----
If the default values of custom attribues did not meet your requirement. You can easyly re-config that attribute. This is sample code that you can refer. you can also browse demo app for more details.

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


Credits
-------
Author: Le Van Hoang (@hoang8f)

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
       
