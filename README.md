# Втора лабораториска вежба по Софтверско инженерство

Стефан Здравковски

231246

## Control Flow Graph

### searchBookByTitle

Фотографија од control flow graph-от за `searchBookByTitle`:

![CFG searchBookByTitle](docs/CFG_searchBookByTitle.drawio.png)

### borrowBook

Фотографија од control flow graph-от за `borrowBook`:

![CFG borrowBook](docs/borrowBook.drawio.png)

## Цикломатска комплексност

Цикломатската комплексност ја пресметувам со формулата:

`V(G) = P + 1`

каде што `P` е бројот на предикатни јазли.

### searchBookByTitle

Цикломатската комплексност за `searchBookByTitle` е 5.

Предикатни јазли:

1. `if (title.isEmpty())`
2. `for (Book book : books)`
3. `if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())`
4. `if (results.isEmpty())`

Според формулата:

`V(G) = P + 1 = 4 + 1 = 5`

### borrowBook

Цикломатската комплексност за `borrowBook` е 5.

Предикатни јазли:

1. `if (title.isEmpty() || author.isEmpty())`
2. `for (Book book : books)`
3. `if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author))`
4. `if (!book.isBorrowed())`

Според формулата:

`V(G) = P + 1 = 4 + 1 = 5`

## Gradle build

Креиран е Gradle проект за обична Java апликација со име `SI_2026_lab2_231246`.

Главната класа е поставена во:

`src/main/java/org/example/SI2026Lab2Main.java`

Тест класата е поставена во:

`src/test/java/org/example/SI2026Lab2Test.java`

Проектот успешно се билдa и тестира со:

`./gradlew test`

Резултат:

`BUILD SUCCESSFUL`

## Тест случаи според критериумот Every statement

Функција: `searchBookByTitle`

Тест функција: `searchBookEveryStatementTest`

| Линија / наредба | test 1: пронајдена книга | test 2: книгата не постои | test 3: празен наслов |
|---|---|---|---|
| `if (title.isEmpty())` | * | * | * |
| `throw new IllegalArgumentException("Invalid title")` |  |  | * |
| `List<Book> results = new ArrayList<>()` | * | * |  |
| `for (Book book : books)` | * | * |  |
| `if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())` | * | * |  |
| `results.add(book)` | * |  |  |
| `if (results.isEmpty())` | * | * |  |
| `return null` |  | * |  |
| `return results` | * |  |  |

Минимален број на тест случаи за оваа функција според Every statement критериумот е 3.

Објаснување:

- test 1 проверува случај кога постои книга и се враќа листа со резултати.
- test 2 проверува случај кога не постои книга и се враќа `null`.
- test 3 проверува случај кога насловот е празен и се фрла `IllegalArgumentException`.

## Тест случаи според критериумот Every branch

Функција: `borrowBook`

Тест функција: `borrowBookEveryBranchTest`

| Гранка | test 1: успешно изнајмување | test 2: веќе изнајмена книга | test 3: книга не е пронајдена | test 4: невалиден влез |
|---|---|---|---|---|
| `title.isEmpty() || author.isEmpty()` -> true |  |  |  | * |
| `title.isEmpty() || author.isEmpty()` -> false | * | * | * |  |
| насловот и авторот се совпаѓаат -> true | * | * |  |  |
| насловот и авторот се совпаѓаат -> false |  |  | * |  |
| `!book.isBorrowed()` -> true | * |  |  |  |
| `!book.isBorrowed()` -> false |  | * |  |  |
| `throw new RuntimeException("Book not found")` |  |  | * |  |

Минимален број на тест случаи за оваа функција според Every branch критериумот е 4.

Објаснување:

- test 1 покрива успешно изнајмување на книга.
- test 2 покрива обид за изнајмување книга која веќе е изнајмена.
- test 3 покрива случај кога книгата не е пронајдена.
- test 4 покрива случај со невалиден влез.

## Тест случаи според критериумот Multiple condition

### searchBookByTitle

Услов:

```java
book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()
```

Подуслови:

- A = `book.getTitle().equalsIgnoreCase(title)`
- B = `!book.isBorrowed()`

Тест функција: `searchBookMultipleConditionTest`

| Комбинација | A | B | A && B | Тест случај |
|---|---|---|---|---|
| TT | true | true | true | книга со ист наслов и не е изнајмена |
| TF | true | false | false | книга со ист наслов, но е изнајмена |
| FT | false | true | false | книга со различен наслов и не е изнајмена |
| FF | false | false | false | книга со различен наслов и е изнајмена |

Минимален број на тест случаи за оваа функција според Multiple condition критериумот е 4.

### borrowBook

Услов:

```java
title.isEmpty() || author.isEmpty()
```

Подуслови:

- A = `title.isEmpty()`
- B = `author.isEmpty()`

Тест функција: `borrowBookMultipleConditionTest`

| Комбинација | A | B | A || B | Тест случај |
|---|---|---|---|---|
| TT | true | true | true | празен наслов и празен автор |
| TF | true | false | true | празен наслов и непразен автор |
| FT | false | true | true | непразен наслов и празен автор |
| FF | false | false | false | непразен наслов и непразен автор |

Минимален број на тест случаи за оваа функција според Multiple condition критериумот е 4.

Бидејќи се тестираат два услови со по два подуслови, вкупно се покриени 8 комбинации.
