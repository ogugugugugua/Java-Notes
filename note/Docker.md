# Docker

> 开发即运维
>

# docker入门

## docker拉取镜像

```shell
docker pull 
```

## docker删除镜像

```shell
docker rmi -f 容器id
docker rmi -f $(docker images -aq) #删除全部镜像
```

## docker运行容器

```shell
docker run [可选参数] image

#参数说明
--name="Name"	容器名字，用于区分同一个镜像的不同的容器
-d	后台方式运行
-it	使用交互方式运行，进入容器查看内容
-p	指定容器的端口：
	-p	ip:主机端口:容器端口
	-p	主机端口:容器端口
	-p	容器端口

```

测试上述功能：

![image-20210130164535734](https://user-images.githubusercontent.com/17522733/106366630-68f56680-633d-11eb-83fb-4ed92ea6b099.png)



## 查看曾经运行过的所有容器（包括已经卸载了的镜像对应的容器）：

```shell
docker ps -a
```

![image-20210130164931205](https://user-images.githubusercontent.com/17522733/106366632-701c7480-633d-11eb-8a89-6732c5a105bd.png)

## 退出容器

```shell
exit	#直接停止容器并退出
Ctrl+P+Q	#容器不停止并退出
```

## 删除容器

```shell
docker rm 容器ID
docker rm -f 容器ID # -f可以暴力删除正在运行的容器
```

## 启动和停止容器

```shell
docker start 容器ID	#启动容器
docker restart 容器ID	#重启容器
docker stop 容器ID	#停止当前正在运行的容器
docker kill 容器ID	#强制停止容器
```

> 常见坑：
>
> docker容器使用后台运行，就必须有一个前台进程，否则话：docker发现没有应用，就会自动停止

## 查看元数据

```shell
docker inspect 容器ID
```

## 进入正在运行的容器

```shell
docker attach 容器ID
docker exec -it 容器ID /bin/bash
```

## 将容器内的文件拷贝到主机上

```shell
docker cp 容器id:/path/file   /local_path/
```

## 查看docker容器状态

```shell
docker stats 容器ID
```



**docker镜像都是只读的，当容器启动时，一个新的可写层被加载到镜像的顶部。这一层就是我们常说的容器层，容器之下的都叫镜像层！**



## docker的commit

```shell
docker commit -a="yulin" -m="add something"   容器id   新的名字
-a	#作者
-m	#相关注释信息
```





# 容器数据卷

docker是将应用和环境打包成一个镜像。但是我们不希望将数据打包在容器中：需求是数据可以持久化。

数据卷技术：目录的挂载，将我们容器内的目录，挂载到主机上。

总结一波：容器的持久化和同步操作，容器间的数据共享。

```shell
docker run -it -v 主机目录:容器内目录 镜像名 /bin/bash
```

这项技术是一个双向操作的东西，在任意一边进行文件的操作，另一边都可以获得同步的信息。 

即使容器停止运行了，只要它还在，就可以在下次启动的时候获得最新的数据。



## MySQL的数据持久化问题

```shell
docker run -d -p 3310:3306 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 --name mysql01 mysql:5.7

-d #后台运行
-p #端口映射
-v #卷挂载
-e #环境配置
--name #容器名字
```

这样的**优点**在于：即使我们删除了这个容器，但是进行了卷挂载的那些目录里面的文件依旧会在主机进行保存，也就是不会有数据丢失！



查看某个卷的具体挂载信息

```shell
docker volume ls #查看所有卷
docker volume inspect 卷名字	#查看某个卷的详细信息
```

*所有的docker容器内的卷，在没有指定目录的情况下，都是在`/var/lib/docker/volumes/xxx/_data`里面*



## DockerFile可用于构建镜像，在构建过程中我们可以进行卷挂载

举例：

```shell
FROM centos
VOLUME ["v1","v2"]
CMD echo "------------end---------------"
CMD /bin/bash
```

其中的第二行是进行了匿名挂载，将上述文件执行之后：

![image-20210131130530897](C:\Users\XIE Yulin\AppData\Roaming\Typora\typora-user-images\image-20210131130530897.png)

进入容器并查看，可以发现已经有两个卷进行了挂载：

![image-20210131131104408](C:\Users\XIE Yulin\AppData\Roaming\Typora\typora-user-images\image-20210131131104408.png)

在v1和v2中可以进行文件的修改，在主机也可以得到同步的内容，反之亦然。



## 数据卷容器

我们若想要做容器间的数据卷挂载，则可以在使用镜像创建容器的时候添加`--volumes-from`指令进行挂载。其中，被挂载的容器应当已经生成，被当成父容器。比如：

```shell
docker run -it --name docker01 yulin/centos	#父容器
docker run -it --name docker02 --volumes-from docker01 yulin/centos	#子容器
```

当然了，对于多个容器间的数据卷相互挂载，他们之间的文件也是同步的。

即使删除父容器，子容器内**依然可以**访问数据文件：他们使用了软连接指向了主机的同一块地址。

/var/lib/docker/volumes/2444d09239c6bf8bb00fd57acc104969cfda42383e52090b002ecc688754ad84/_data

即docker01或者docker02都挂载到了这块地址上：`/var/lib/docker/volumes/2444d09239c6bf8bb00fd57acc104969cfda42383e52090b002ecc688754ad84/_data`。



# Dockerfile

用于构建docker镜像。



# Docker网络