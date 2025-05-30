# 📚 Лабораторная работа №2 — Связь One-To-Many и работа с БД (Spring Boot + PostgreSQL)

## 🧾 Задание

**1. Подключить в проект БД (PostgreSQL/MySQL и т.д.)**

**2. Реализовать связь `@OneToMany`**

**3. Реализовать CRUD-операции для всех сущностей**

---

## ⚙️ Стек технологий

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Lombok
- REST API (JSON)
- MapStruct / ручной Mapper (через DTO)
- Maven

---

## 🧩 Структура проекта

- `Country` — страна (код, имя, телефонный код)
- `PhoneNumberPrefix` — телефонный префикс, привязанный к стране
- Связь: `Country` → `PhoneNumberPrefix` — **OneToMany**

---

## 📦 Сущности

### Country

| Поле       | Тип     | Описание               |
|------------|----------|------------------------|
| code       | String   | ISO-код страны (PK)    |
| name       | String   | Название страны        |
| phoneCode  | String   | Телефонный код         |

### PhoneNumberPrefix

| Поле        | Тип     | Описание                     |
|-------------|----------|------------------------------|
| id          | Long     | ID (PK)                      |
| prefix      | String   | Например, "+375 29"          |
| regionName  | String   | Название региона или оператора |
| country     | Country  | Ссылка на страну (FK)        |

---

## 💾 Подключение к PostgreSQL

В `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
