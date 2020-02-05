## 案例2

SSM整合

artifacts可能会突然坏掉，可以通过重新部署的方式来解决问题




spring整合mybatis就是需要将
`SqlSessionFactoryBuilder.build(inputStream).openSession().getMapper(xxMapper.class)`
得到的代理对象放进IOC容器中，使得spring也能够获得对该对象的控制，从而在service中注入这个对象，就可以在服务层获得对dao层中对象的操作。