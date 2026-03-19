# Informations  
Le projet a été fait sur IntelliJ IDEA, du 17/03/2026 au 19/03/2026.   

## Sujet 
Le sujet était le suivant : [Sujet](https://chocolaterie.github.io/documentation/docs/spring/tp/domain/tp-1/)

## Fonctionnement du projet  
Il faut avoir installer SQL Workbench, SQL Server en version 8.45 minimun, et/ou MongoDB.  
Puis il faut git clone ce repository, attention a ne pas le mettre dans un endroit trop long d'accès.  
Une fois le projet ouvert, il faut aller dans les dossiers : app/src/ressources/application.yml. 

### Pour MongoDB 
Il faudra modifier la ligne ci-dessous avec le nom de votre base de données.  
```uri: mongodb://localhost:27017/db_tp```  

### Pour SQL  
Il faudra modifer les lignes ci-dessous avec le nom de votre base de données, votre username et votre password.  
``url: jdbc:mysql://localhost:3306/db_tp?useSSL=false&serverTimezone=UTC``
``username: root``  
``password: root``

### Pour Gradle  
Pour le build.gradle de l'app, il faudra commenter ou enlever les commentaires sur adapter-jpa ou adapter-mongo selon la base de données utilisées. De même pour le setting.gradle