![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p1.png)
### Introduction
* Can parse the main tones in the image, **Defaults to the main color as the color of the control's shadow**
* can **customize the control's shadow color**
* can control the corner size of the four corners of the control **(if the control is set to a positive direction, the control can be rounded as the corner radius increases)**
* Can control the **shadow radius of the control**
* You can control the **offset of the shadow in the x and y directions respectively**
* The color in the picture can be parsed out of six theme colors, **each theme color has a corresponding color matching background, title, text recommendation color**


### References in build.gradle
```
Compile 'com.dingmouren.paletteimageview:paletteimageview:1.0.7'
```
　　　　　　　　　　　　　　　　　![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/title.gif) 
##### 1. Parameter Control
Corner Radius | Shadow Blur Range | Shadow Offset
---|---|---
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo1.gif) | ![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo2.gif) | ![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo3.gif)

##### 2. Shadow color defaults to the main color of the picture

　　　　　　　　　　　　　　　　　　　![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo4.gif)
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p2.png)
##### 3. Image color theme analysis
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p3.png)
### Use

###XML
```
<com.dingmouren.paletteimageview.PaletteImageView
         Android:id="@+id/palette"
         Android:layout_width="match_parent"
         Android:layout_height="wrap_content"
         App:palettePadding="20dp"
         App:paletteOffsetX="15dp"
         App:paletteOffsetY="15dp"
         />
     
     
     JAVA 
     --------
     mPaletteImageView.setOnParseColorListener(new PaletteImageView.OnParseColorListener() {
                     @Override//Complete the color of the image
                     Public void onComplete(PaletteImageView paletteImageView) {
                         Int[] vibrant = paletteImageView.getVibrantColor();
                         Int[] vibrantDark = paletteImageView.getDarkVibrantColor();
                         Int[] vibrantLight = paletteImageView.getLightVibrantColor();
                         Int[] muted = paletteImageView.getMutedColor();
                         Int[] mutedDark = paletteImageView.getDarkMutedColor();
                         Int[] mutedLight = paletteImageView.getLightMutedColor();
                     }
 
                     @Override // failed to parse the color of the image
                     Public void onFail() {
 
                     }
                 });
```
### xml attributes

Xml attribute | Description
---|---
  App:palettePadding | **indicates that the shadow shows the maximum spatial distance. A value of 0, no shadow, greater than 0, shadows.**
  App:paletteOffsetX | represents the offset of the shadow in the x direction
  App:paletteOffsetY | represents the offset of the shadow in the y direction
  App:paletteSrc | represents a picture resource
  App:paletteRadius | indicates the corner radius
  App:paletteShadowRadius | Indicates shadow blurring
### Public Methods
Method | Description
---|---
Public void setShadowColor(int color) | Represents the color of the custom settings control shadow
 Public void setBitmap(Bitmap bitmap) | Represents setting the control bitmap
 Public void setPaletteRadius(int raius) | Represents to set control corner radius
 Public void setPaletteShadowOffset(int offsetX, int offsetY) | Represents the offset of the shadow of the control in the x-direction or y-direction.
 Public void setPaletteShadowRadius(int radius) | Represents how to set control shadow blurring
 Public void setOnParseColorListener(OnParseColorListener listener) | Set the listener to control the color of the image
 Public int[] getVibrantColor() | Represents an array of colors to get the Vibrant theme; assuming that the color array is arry, arry[0] is the color used by the recommended title, arry[1] is the color used by the recommended body, and arry[2] is the recommended The color used for the background. Colors are for recommendations only, you can choose
 Public int[] getDarkVibrantColor()| Represents an array of colors for obtaining the DarkVibrant theme. The meaning of the array element is the same as above
 Public int[] getLightVibrantColor()| Represents the color array of the LightVibrant theme. The meaning of the array element is the same as above
 Public int[] getMutedColor()| Represents the color array of the Muted theme. The meaning of the array element is the same as above.
 Public int[] getDarkMutedColor()| Represents the color array of the DarkMuted theme. The meaning of the array element is the same as above.
 Public int[] getLightMutedColor()| Represents the color array of the LightMuted theme. The meaning of the array element is the same as above

## License
```
Copyright (C) 2017 DingMouRen

Licensed under the Apache License, Version 2.0 (the "License");
You may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
Distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
Limitations under the License
```
