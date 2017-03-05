项目介绍
======
效果图
_____
![](https://github.com/chinahyong/ScrollNumAnim/blob/3cbdc9bb45dd31df2e4c6657367381625cd872a0/%E6%95%88%E6%9E%9C%E5%9B%BE.gif "效果图")
    .    
项目实现
______
    1. VerticalTextView 继承自 TextSwitcher，作为最基本的widget，通过该控件，直接实现文本的进出动画的设置；TextSwitcher核心代码是操作两个view进行visible\gone,来进行进出动画的视线
    2. AutoVerticalLinearLayout，用于承载多个VerticalTextView,因多位数的动画效果进出时间不同，故将数字按位数截取，单独在VerticalTextView显示，在此通过Math函数获取数字的各个位置的数字转为集合，进行适配一组VerticalTextView
    3. StokeTextView 给文本添加边框，可按需添加，可以直接使用普通的TextView动画。

动画规则
______
    0 < 5
    0 ----> 5(0 1 2 3 4 5)
    5 > 0
    5 ----> 0(6 7 8 9 0)
    0 = 0
    0 ----> 0(0 1 2 3 4 5 6 7 8 9 0)
