# Vue.js

## Intro

Vue是一套用于构建用户界面的**渐进式框架**。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层

## HelloWorld例程

```vue
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Vue Todo Tutorial</title></head>
<body>
<div id="app">{{ message }}</div>
</body>
<script src="https://cdn.bootcss.com/vue/2.5.16/vue.js"></script>
<!--可选连接：-->
<!--<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>-->
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.8/vue.min.js"></script>-->
<!--<script src="https://unpkg.com/vue/dist/vue.js"></script>-->
<script>
    var app = new Vue({
        el: '#app',
        data: function () {
            return {message: 'Hello Vue!'}
        }
    })
</script>
</html>
```

我们可以在 Vue.js 的官网上直接下载 vue.min.js 并用 <script> 标签引入：https://vuejs.org/js/vue.min.js。

在上面的例程中我们创建了一个`Vue`对象，然后指定其挂载的元素`el`（elements）是id为“app“的那个`div`块，并且在`data`中绑定了一个叫`message`的变量并赋值。

## 表单绑定

```vue
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Vue Todo Tutorial</title></head>
<body>
<div id="app">
    <input type="text" v-model="value">
    <input type="button" value="send">
    <div>value = {{value}}</div>
</div>
</body>
<script src="https://cdn.bootcss.com/vue/2.5.16/vue.js"></script>
<script>
    var app = new Vue({
        el: '#app',
        data: function () {
            return {value: 'Hello Vue!'}
        }
    })
</script>
</html>
```

![image-20200516120905655](C:\javaNotes\pics\image-20200516120905655.png)

这里`input`标签中的`v-model`是Vue的指令之一，用于将`input元素value属性的值`和我们创建的`Vue对象中value的值`进行绑定。经过这样的绑定之后，input框中引起的value值变化就会**实时**地反映到关联的Vue对象中，所以下方的{{value}}也会跟随上方输入框**实时**变化。

## 网页通知

我们可以在Vue对象中增加一些函数的方法，比如如下这个可以产生网页通知的方法：

```vue
<input type="button" value="send" v-on:click="send"/> <!--增加按键动作-->
<!---->
<script>
var app = new Vue({
        el: '#app',
        data: function () {
            return {message: ''}
        },
        methods:{							<!--定义方法-->
            send: function () {
                alert("send successful");
                this.message=''				  <!--清空Vue/输入框内容-->
            }
        }
    })
</script>
```

↑这里的`methods`中可以添加多个方法。

## 实时统计输入的字数

Vue 还可以根据绑定的数据做一些计算，然后我们就可以引用计算的结果：

```vue
<div>count = {{count}}</div>					<!--这里引用count这个键-->
<script>
    var app = new Vue({
        el: '#app',
        data: function () {
            return {message: ''}
        },
        <!--计算属性申明到 computed 对象里，这个对象的键是计算的结果，值是计算函数-->
        computed:{                            
            count:function () {
                return this.message.length;		<!--这里返回message的长度-->
            },
            calcul:function () {
                return this.count *2;  			<!--这里引用了count这个键，返回其两倍的值-->
            }
        }
    })
</script>
```

## 绑定CSS样式

我们希望 input 中没有任何值输入，即 value 的值为空时，input 的边框为红色以提醒用户没有内容：

```vue
<style>
    .empty{
        border-color: red;
    }
</style>
<input type="text" v-model="message" v-bind:class="{empty: !count}">
```

这里使用了`v-bind`指令进行绑定，Vue会根据`empty`后的表达式`!count`的真假来判断`class`的值是否为`empty`。

![image-20200516135310833](C:\javaNotes\pics\image-20200516135310833.png)![image-20200516135329614](C:\javaNotes\pics\image-20200516135329614.png)

## 使用v-if进行判断

希望只有用户真正地输入了内容后，才提示 value 的值，否则显示请输入值：

```vue
<div id="app"> <!--无关内容已去除-->
    <input type="text" v-model="message" v-bind:class="{empty: !count}">
    <div v-if="notnull">value = {{message}}</div>
    <div v-else>please input message↑</div>
</div>
<script>
    var app = new Vue({
        el: '#app',
        data: function () {
            return {message: ''}
        },
        computed:{
            notnull:function () {
                return this.message!=='';
            }
        }
    })
</script>
```

这里的`<div v-if="notnull">value = {{message}}</div>`是重新定义了一个用于判断非空的函数，其实也可以直接用`<div v-if="count">value = {{message}}</div>`来判断，因为当未输入时count的值为0，即false。

这里的`<div v-else>please input message↑</div>`则是和上面搭配使用的。若没有这句代码的话就这行啥都不会显示。

![image-20200516142147167](C:\javaNotes\pics\image-20200516142147167.png)![image-20200516142205623](C:\javaNotes\pics\image-20200516142205623.png)

## v-for循环

假设我们人为构造数据如下：

```vue
<script>
    var app = new Vue({
        el: '#todo-app',
        data: function () {
            return{
                todos:[
                    {id:0, title:"learn chinese"},
                    {id:1, title:"learn english"},
                    {id:2, title:"learn german"}
                ]
            }
        }
    })
</script>
```

则可以用如下的方式进行显示：

```vue
<div id="todo-app">
    <div>
        <ul>
            <li v-for="todo in todos" :key="todo.id">  <!--这里使用的唯一的key为了高效的更新虚拟DOM-->
                <span>{{todo.title}}</span>
                <input type="button" value="completed">
                <input type="button" value="delete">
                <input type="text" placeholder="edit todo">
            </li>
        </ul>
    </div>
</div>
```

## 添加TODO

为了知道用户输入了什么内容，我们使用 `v-model `指令将 `input 的 value 值`和` Vue 的实例`绑定，这样在 Vue 中我们就知道了用户输入的值。

在监听键盘事件时，我们经常需要检查详细的按键。Vue 允许为 `v-on` 在监听键盘事件时添加按键修饰符：下面的`v-on:keyup.enter="addTodo"`说明当`key`是`enter`时调用`addTodo`函数。同理也可以为其他键，如`v-on:keyup.page-down`或者`v-on:keyup` -- `.tab` `.delete` `.esc` `.space` `.up` `.down` `.left` `.right` 。

```vue
![vue](D:\浏览器下载\vue.png)<div>
        <input type="text" placeholder="add todo" v-model="newTodoTitle" v-on:keyup.enter="addTodo">
</div>
<!------------------------------------------------------------------------------------------>
<script>
    let Id = 0;
    var app = new Vue({
        el: '#todo-app',
        data: function () {
            return{
                todos:[],
                newTodoTitle: ''
            }
        },
        methods:{
            addTodo: function () {
                if (this.newTodoTitle.trim()!==''){     	 <!--这里用来判断输入的是否为纯空字符串-->
                    this.todos.push({id: Id++, title: this.newTodoTitle});	   <!--塞入字符串数组-->
                    this.newTodoTitle = '';									<!--清空字符串-->
                }
            }
        }
    })
</script>
```

基本关系可以总结成如下图↓，由于`v-model`指令的存在，只要在input框中输入，就会实时绑定数据到`newTodoTitle`这个字符串上，按下回车键就会调用`addTodo()`函数，在该函数中将`newTodoTitle`字符串塞入到`todos`数组中，并清空`newTodoTitle`字符串以便于在`input`框中继续输入新数据。

![](https://github.com/ogugugugugua/Java-Notes/blob/master/pics/vue.png)











