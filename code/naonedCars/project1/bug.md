- 在adminPage.html中使用Vue：
需要留意：
```javascript
var vue = new Vue({
                el: '#dataArea',
                data: data4Vue,
                mounted:function () {
                    this.getAllVoitures();
                },
                methods:{
                    getAllVoitures:function () {
                        var url = this.uri;
                        axios.get(url).then(function (response) {
                            vue.response = response.data;
                        });
                    }
                }
            });
```
里面的el是需要带#号的，否则无法联系起来。

- 使用axios进行异步通信需要引入：
```html
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

- 使用jQuery操作html的元素需要引入：
```html
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
```

- 使用Vue需要引入：
```html
    <script src="https://cdn.bootcss.com/vue/2.5.16/vue.js"></script>
```


- vue前端进行form提交时候，后端的controller需要使用@Requestbody来对整个对象进行约束，例如：
后端：
```java 
@RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result testUpdateVoiture(@RequestBody temp object) {
        System.out.println(object);
        return null;
    }
```
前端：
```html
upload: function () {
                    axios.post(this.link, this.voiture).then(function (response) {
                        this.response = response.data;
                    })
                }
以及：
<form :model="voiture" :rules="rules" ref="voiture" label-width="100px" class="demo-voiture">

        <input v-model="voiture.name"></input>
        <input v-model="voiture.author"></input>

        <button type="primary" @click="upload()">upload</button>
    </form>
```

Vue的data对象里面的成员，即使是大写命名的，在引用时却小写首字母才行，非常奇怪。