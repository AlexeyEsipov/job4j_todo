# Приложение Список Дел
## Используемые технологии:
![Java](https://img.shields.io/badge/Java--17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot--2.7.3-F2F4F9?style=for-the-badge&logo=spring-boot)
![Bootstrap](https://img.shields.io/badge/Bootstrap--5.2.2-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL--14-316192?style=for-the-badge&logo=postgresql&logoColor=white)
[![Hibernate](https://img.shields.io/badge/Hibernate--5.6.11.Final-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org/)

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0.0.RELEASE-blue?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Liquibase](https://img.shields.io/badge/Liquibase-4.9.1-red?style=for-the-badge&logo=liquibase&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18.24-green?style=for-the-badge&logo=lombok&logoColor=white)

Перед запуском установите:
- PostgreSQL 14
- Java 17
- Apache Maven 3.x

## Запуск приложения

1. Создайте базу данных todo:
```sql
create database todo;
```

- Запуск приложения производится с использованием maven.
  Для перехода в каталог сервиса в командной строке выполните команду
```
    cd todo
```
- Затем выполните команды:
```
    mvn clean install
    mvn spring-boot:run
```

и перейдите в браузере на страницу http://localhost:8080/index

### Описание:
Это приложение можно использовать для формирования и контроля списка предстоящих задач.


Главная страница

![index page](images/index.PNG)

При переходе по ссылке "Список дел" неавторизованный пользователь попадает 
на страницу авторизации, где он может зарегистрироваться

![allItems page](images/1registration.JPG)

Если регистрация прошла успешно, пользователь видит сообщение, 
подтверждающее регистрацию и предлагающее авторизоваться.

![allItems page](images/2RegOk.JPG)

Если пользователь с такой почтой уже зарегистрирован, то пользователь 
также получит сообщение о том, что регистрация не подтверждена.

![allItems page](images/regError.jpg)

Если пользователь уже зарегистрирован в системе, то по ссылке "Войти"
он попадает на страницу авторизации

![notCompletedItem page](images/login.jpg)

Где вводит свои регистрационные данные

![notCompletedItem page](images/3Login.JPG)

Если регистрационные данные введены неверно, пользователь получит 
соответствующее сообщение

![notCompletedItem page](images/err_login.jpg)

После успешной авторизации пользователь попадает на страницу со своими заданиями. 
Так как пользователь только зарегистрировался, то список его заданий еще пустой.

![notCompletedItem page](images/4AllTask.JPG)

По ссылке "Категории" надо добавить нужные категории

![notCompletedItem page](images/5AddCategory.JPG)

Добавляем категорию "Работа"

![notCompletedItem page](images/6FirstCategory.JPG)

Добавляем несколько категорий

![notCompletedItem page](images/7FewCategory.JPG)

Когда категории заполнены, можно приступать к заполнению нового задания
По ссылке "Добавить задание" пользователь может разместить задачу в системе

![notCompletedItem page](images/8NewTask.JPG)

Первая задача успешно размещена

![notCompletedItem page](images/9AllTask.JPG)

Задача может одновременно относиться к нескольким категориям

![notCompletedItem page](images/10TaskFewCategory.JPG)

Сейчас список всех задач пользователя выглядит таким образом:

![notCompletedItem page](images/11AllTaskFewCategory.JPG)

Незавершенную задачу можно удалить, изменить или завершить

![notCompletedItem page](images/12TaskMenu.JPG)

Все задания отображаются по ссылке "Все задания"

![notCompletedItem page](images/13TaskAllComplNotComplet.JPG)

Завершенные задания отображаются по ссылке "Завершенные"

![notCompletedItem page](images/14TaskAllCompl.JPG)

Незавершенные задания отображаются по ссылке "Новые"

![notCompletedItem page](images/15TaskNotComplet.JPG)

Завершенное задание из списка можно только удалить

![notCompletedItem page](images/16TaskNotCompletOnlyRemove.JPG)

В незавершенном задании можно изменить любую информацию, кроме его номера

![notCompletedItem page](images/17TaskNotCompletCanChange.JPG)

Как видим, изменить возможно все, включая категории задания

![notCompletedItem page](images/18TaskChanged.JPG)

Как видим, номер задания остается прежним, а вся информация теперь заменена.

![notCompletedItem page](images/19Resume.JPG)

Связаться со мной можно по электронной почте a_esipov_it@list.ru
или в телеграм  @Alex46volokno


<div id="socials" align="center">
    <!-- <a href="linkedin-url">
    <img src="https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/>
  </a> -->

  <a href="https://t.me/alex46volokno">
    <img src="https://img.shields.io/badge/Telegram-blue?style=for-the-badge&logo=telegram&logoColor=white" alt="Telegram"/>
  </a>
</div>