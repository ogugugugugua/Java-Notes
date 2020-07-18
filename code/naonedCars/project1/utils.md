启动mysql
进入目录bin，然后
start mysqld

---

数据库表：

- 汽车 | voiture
    - id (int 11 auto_increment)
    - Titre (TINYTEXT)
    - Marque (varchar 32)
    - Modèle (varchar 16)
    - AnnéeModèle (YEAR)
    - MiseEnCirculation (DATE)
    - Kilométrage (int 10)
    - Carburant (varchar 16)
    - BoîteDeVitesse (varchar 32)
    - TypeDeVéhicule (varchar 32)
    - PuissanceFiscale (TINYINT 4)
    - Référence (varchar 16)
    - Description (TEXT)
    - Couleur (varchar 10)
    - NombreDePlace (TINYINT 2)
    - NombreDePortes (TINYINT 2)
    - PuissanceDIN (SMALLINT 3)
    - Permis (TINYINT 1)
    - SoumisàLOA/LLD (TINYINT 1)
    
建表语句：
```mysql
create database IF NOT EXISTS naoned;
use naoned;
/*DROP TABLE IF EXISTS `voiture`;*/

insert into `voiture` (id,Titre,Marque,Modèle)
values 
(1, 'biaoti','peugeot','206');


insert into voiture (id, Titre, Marque, Modèle, AnnéeModèle, MiseEnCirculation, Kilométrage, Carburant, BoîteDeVitesse, TypeDeVéhicule, PuissanceFiscale, Référence, Description, Couleur, NombreDePlace, NombreDePortes, PuissanceDIN, Permis, `SoumisàLOA/LLD`) VALUE (4,"text2","dongfeng","16",2020,'2020-01-01',100000,"diesel","automatique","citadine",5,"1233456546234","tres bonne voiture","rouge",4,4,1444,1,0);
select * from naoned.voiture;

insert into voiture (Titre, Marque, Modèle, AnnéeModèle, MiseEnCirculation, Kilométrage, Carburant, BoîteDeVitesse, TypeDeVéhicule, PuissanceFiscale, Référence, Description, Couleur, NombreDePlace, NombreDePortes, PuissanceDIN, Permis, `SoumisàLOALLD`) VALUE ("text3","dongfeng3","16",2020,'2020-01-01',100000,"diesel","automatique","citadine",5,"1233456546234","tres bonne voiture","rouge",4,4,1444,1,0);




create table if not exists `voiture`(
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `Titre` TINYTEXT NOT NULL COMMENT '标题',
    `Marque` varchar(32) NOT NULL COMMENT '品牌',
    `Modèle` varchar(16) NOT NULL COMMENT '型号',
    `AnnéeModèle` DATE DEFAULT NULL COMMENT '型号年份',
    `MiseEnCirculation` DATE DEFAULT NULL COMMENT '落地时间',
    `Kilométrage` int(10) DEFAULT NULL COMMENT '里程',
    `Carburant` varchar(16) DEFAULT NULL COMMENT '燃料',
    `BoîteDeVitesse` varchar(32) DEFAULT NULL COMMENT '变速箱',
    `TypeDeVéhicule` varchar(32) DEFAULT NULL COMMENT '车型种类',
    `PuissanceFiscale` INT(4) DEFAULT NULL,
    `Référence` varchar(16) DEFAULT NULL COMMENT '索引号',
    `Description` TEXT DEFAULT NULL COMMENT '详细描述',
    `Couleur` varchar(10) DEFAULT NULL COMMENT '颜色',
    `NombreDePlace` INT(2) DEFAULT NULL COMMENT '座位个数',
    `NombreDePortes` INT(2) DEFAULT NULL COMMENT '门个数',
    `PuissanceDIN` INT(3) DEFAULT NULL,
    `Permis` INT(1) DEFAULT NULL COMMENT '是否需要驾照',
    `SoumisàLOALLD` INT(1) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

```

    
    
