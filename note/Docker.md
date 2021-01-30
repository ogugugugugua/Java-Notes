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

![image-20210130164535734](C:\Users\XIE Yulin\AppData\Roaming\Typora\typora-user-images\image-20210130164535734.png)



## 查看曾经运行过的所有容器（包括已经卸载了的镜像对应的容器）：

```shell
docker ps -a
```

![image-20210130164931205](C:\Users\XIE Yulin\AppData\Roaming\Typora\typora-user-images\image-20210130164931205.png)

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

# DockerFile

# Docker网络