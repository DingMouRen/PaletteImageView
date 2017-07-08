![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/p1.png)　
### 简介
* 可以解析图片中的主色调，默认将主色调作为控件阴影的颜色
* 可以自定义设置控件的阴影颜色
* 可以控制控件四个角的圆角大小（如果控件设置成正方向，随着圆角半径增大，可以将控件变成圆形）
* 可以控制控件的阴影半径大小
* 可以分别控制阴影在x方向和y方向上的偏移量
* 可以将图片中的颜色解析出六种主题的颜色，每一种主题颜色都有相应的三种匹配背景 标题 正文的推荐颜色


### build.gradle中引用
```
	compile 'com.dingmouren.paletteimageview:paletteimageview:1.0.4'
```

##### 1.参数的控制
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo1.gif)　　　
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo2.gif)　　　
![image](https://github.com/DingMouRen/PaletteImageView/raw/master/screenshot/demo3.gif)

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
```
### xml属性

xml属性 | 描述
---|---
  app:palettePadding | 表示阴影显示最大空间距离。值为0,没有阴影，大于0，才有阴影。
  app:paletteOffsetX | 表示阴影在x方向上的偏移量
  app:paletteOffsetY | 表示阴影在y方向上的偏移量
  app:paletteSrc | 表示图片资源
  app:paletteRadius | 表示圆角半径
  app:paletteShadowRadius | 表示阴影半径

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

