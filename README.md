# TransitionApp
learning how to use Transition in app

[Transition](http://www.android-doc.com/reference/android/transition/package-summary.html)

`package android.transition Added in API level 19`
`public abstract class Transition implements Cloneable`

这个包里的Transition能够为视图层级提供高效的"页面场景切换"功能,本质是属性动画

一个场景[Scene](http://www.android-doc.com/reference/android/transition/Scene.html)是一个视图层级状态的封装类,包括这些视图层级中的Views以及它们的多种属性值(布局相关以及其他)。一个场景可以直接在布局层级中或动态地通过代码在进入界面时定义。

过渡切换是一种机制来自动制作进入一个新的场景时发生变化的动画。一些过渡能力是自动的。也就是说,在进入一个场景可能会使离开的页面执行淡出的切换动画,已经存在的Views会出现[ChangeBounds](http://www.android-doc.com/reference/android/transition/ChangeBounds.html)布局边界发生变化以及大小变化的效果,并且淡入的Views变得可见。这个类里面有其他的过渡效果,可以动画的改变其他属性,例如颜色的变化,并且可以任意的指定过渡效果在某项场景切换时。最后,开发人员可以定义自己的子类来监视其特定的属性变化和运行自定义动画时这些属性的改变。

[TransitionManager](http://www.android-doc.com/reference/android/transition/TransitionManager.html)被用于指定特定场景变化的自定义过渡效果,并且使场景变换时能够和那些特定效果一起产生。

### Interfaces
<table>
    <tr>
        <td><a href="http://www.android-doc.com/reference/android/transition/Transition.TransitionListener.html">Transition.TransitionListener</a></td>
        <td>一个Transition监听器,在过渡切换中接收通知,类似生命周期回调</td>
    </tr>
</table>

### Classes
<table>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/AutoTransition.html">AutoTransition</a></td>
            <td>创建自动消失,移动,并且在场景改变期间调整大小的默认过渡效果的实用工具类.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/ChangeBounds.html">ChangeBounds</a></td>
            <td>这种转变捕获了目标视图的布局边界在场景变化前后和动画过渡过程中的变化.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/Fade.html">Fade</a></td>
            <td>这个过渡效果跟踪目标视图在改变的开始和结束的可见性以及淡入淡出的Views当他们变得可见或不可见.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/Scene.html">Scene</a></td>
            <td>代表了当一个场景被应用的时候视图层级中多种属性值的集合.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/Transition.html">Transition</a></td>
            <td>一个切换效果持有场景变换时目标的动画信息.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/TransitionInflater.html">TransitionInflater</a></td>
            <td>这个类从资源文件中解析场景过渡效果.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/TransitionManager.html">TransitionManager</a></td>
            <td>这个类管理过渡切换的集合,当有场景发生改变时会施加到目标视图上执行切换动画.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/TransitionSet.html">TransitionSet</a></td>
            <td>过渡切换的集合是子切换效果的父类(也包括其他TransitionSets).&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/TransitionValues.html">TransitionValues</a></td>
            <td>缓存过渡切换时值的数据结构.&nbsp;</td>
        </tr>
        <tr>
            <td><a href="http://www.android-doc.com/reference/android/transition/Visibility.html">Visibility</a></td>
            <td>记录过渡效果中目标视图的可见性改变.&nbsp;</td>
        </tr>
        <tr>
            <td>Target</td>
            <td>在Scene里的View对象,可以在Scene移除或添加Target(View#id).&nbsp;</td>
        </tr>
        <tr>
            <td>ArcMotion</td>
            <td>包含两个点的虚圆产生的沿弧形弯曲的路径.&nbsp;</td>
        </tr>
  </table>

![img](https://github.com/willkernel/TransitionApp/blob/master/pngs/flow.png)<br>
Transition类：captureStartValues();captureEndValues();createAnimator();<br>
从2个Scene的每个Target获取StartValues和EndValues，然后用createAnimator给每个Target创建属性动画<br>

Note:<br>
在SurfaceView上可能表现不正常<br>
在TextureView上施加某些Transition 效果，可能达不到预期效果<br>
继承自AdapterView的View，比如ListView上可能会出错<br>
对TextView的size 施加Transition 效果时，文字的位置会突变<br>
不要通过scene的actions在scene间传数据，用callback<br>