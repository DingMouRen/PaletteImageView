![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p1.png)　
### 简介
* 可以解析图片中的主色调，**默认将主色调作为控件阴影的颜色**
* 可以**自定义设置控件的阴影颜色**
* 可以**控制控件四个角的圆角大小**（如果控件设置成正方向，随着圆角半径增大，可以将控件变成圆形）
* 可以**控制控件的阴影半径大小**
* 可以分别**控制阴影在x方向和y方向上的偏移量**
* 可以将图片中的颜色解析出**六种主题颜色**，每一种主题颜色都有相应的**匹配背景、标题、正文的推荐颜色**


### build.gradle中引用
```
	compile 'com.dingmouren.paletteimageview:paletteimageview:1.0.6'
```
　　　　　　　　　　　　　　　　　![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/title.gif) 
##### 1.参数的控制
圆角半径|阴影模糊范围|阴影偏移量
---|---|---
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo1.gif) | ![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo2.gif) | ![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo3.gif)

##### 2.阴影颜色默认是图片的主色调

　　　　　　　　　　　　　　　　　　　![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo4.gif)
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p2.png)
##### 3.图片颜色主题解析
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p3.png)
### 使用

```
 <com.dingmouren.paletteimageview.PaletteImageView
         android:id="@+id/palette"
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:palettePadding="20dp"
         app:paletteOffsetX="15dp"
         app:paletteOffsetY="15dp"
         />
 mPaletteImageView.setOnParseColorListener(new PaletteImageView.OnParseColorListener() {
                     @Override//解析图片的颜色完毕
                     public void onComplete(PaletteImageView paletteImageView) {
                         int[] vibrant = paletteImageView.getVibrantColor();
                         int[] vibrantDark = paletteImageView.getDarkVibrantColor();
                         int[] vibrantLight = paletteImageView.getLightVibrantColor();
                         int[] muted = paletteImageView.getMutedColor();
                         int[] mutedDark = paletteImageView.getDarkMutedColor();
                         int[] mutedLight = paletteImageView.getLightMutedColor();
                     }
 
                     @Override//解析图片的颜色失败
                     public void onFail() {
 
                     }
                 });        
```
### xml属性

xml属性 | 描述
---|---
  app:palettePadding | 表示阴影显示最大空间距离。值为0,没有阴影，大于0，才有阴影。
  app:paletteOffsetX | 表示阴影在x方向上的偏移量
  app:paletteOffsetY | 表示阴影在y方向上的偏移量
  app:paletteSrc | 表示图片资源
  app:paletteRadius | 表示圆角半径
  app:paletteShadowRadius | 表示阴影模糊范围
### 公共的方法
方法 | 描述
---|---
public void setShadowColor(int color) | 表示自定义设置控件阴影的颜色
 public void setBitmap(Bitmap bitmap) | 表示设置控件位图
 public void setPaletteRadius(int raius) | 表示设置控件圆角半径
 public void setPaletteShadowOffset(int offsetX, int offsetY) | 表示设置阴影在控件阴影在x方向 或 y方向上的偏移量
 public void setPaletteShadowRadius(int radius) | 表示设置控件阴影模糊范围
 public void setOnParseColorListener(OnParseColorListener listener) | 设置控件解析图片颜色的监听器
 public int[] getVibrantColor() | 表示获取Vibrant主题的颜色数组；假设颜色数组为arry,arry[0]是推荐标题使用的颜色，arry[1]是推荐正文使用的颜色，arry[2]是推荐背景使用的颜色。颜色只是用于推荐，可以自行选择
 public int[] getDarkVibrantColor()| 表示获取DarkVibrant主题的颜色数组，数组元素含义同上
 public int[] getLightVibrantColor()| 表示获取LightVibrant主题的颜色数组，数组元素含义同上
 public int[] getMutedColor()| 表示获取Muted主题的颜色数组，数组元素含义同上
 public int[] getDarkMutedColor()| 表示获取DarkMuted主题的颜色数组，数组元素含义同上
 public int[] getLightMutedColor()| 表示获取LightMuted主题的颜色数组，数组元素含义同上

## License
```
Copyright (C) 2017 WeyYe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
```

