## Deployment of the tmall_ssm project on a CentOS machine
---
1. Machine: Hostwinds CentOS 7
2. Java:
    - Install
    ```shell script
    yum -y install java-1.8.0-openjdk.x86_64
    ```
    - Verification
    ```shell script
    java -version
    ```
3. MySQL:
    - Install
    ```shell script
    # Get MySQL community version:
    cd /tmp
    wget http://repo.mysql.com/mysql-community-release-el7-5.noarch.rpm  
    rpm -ivh mysql-community-release-el7-5.noarch.rpm  
    # Install:
    yum install mysql mysql-server mysql-devel -y
    ```
    - Start
    ```shell script
    systemctl start mysql.service
    ```
    - Verification of 3306 port LISTEN:
    ```shell script
    netstat -anp | grep 3306
    ```
   - Password Setup
   ```shell script
    mysqladmin -u root password admin
    ```
   - Set Lower case not sensitive
   ```shell script
    vi /etc/my.cnf  
    ```
    ```shell script
   #Input:
    lower_case_table_names=1
    ```
    - Restart MySQL
    ```shell script
    systemctl restart mysqld.service 
    ```
    - Verification of login
    ```shell script
    mysql -uroot -padmin
    CREATE DATABASE tmall_ssm DEFAULT CHARACTER SET utf8;
    ```
   - Create Data
   ```shell script
    mysql -u root -padmin --default-character-set=utf8 tmall_ssm < /home/tmall_ssm.sql
    ```
4. Tomcat:
    - Install
    ```shell script
    cd /tmp
    wget https://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-7/v7.0.103/bin/apache-tomcat-7.0.103.tar.gz
    tar xzf apache-tomcat-7.0.103.tar.gz
    mv apache-tomcat-7.0.103 /usr/local/tomcat7
    ```
   - Start
   ```shell script
    /usr/local/tomcat7/bin/startup.sh
    ```
   - Verification of 8080 port LISTEN
   ```shell script
   netstat -anp | grep 8080
   ```
   - Verification of Tomcat status
   ```shell script
    tail -300f /usr/local/tomcat7/logs/catalina.out
    ```
   - Open 8080 Port
   
5. Deploy:
    - Put `war` file under `/usr/local/tomcat7/webapps/`
    - Restart Tomcat
    ```shell script
    /usr/local/tomcat7/bin/shutdown.sh
    /usr/local/tomcat7/bin/startup.sh
    ```