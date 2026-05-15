Стефан Здравковски
231246
## Control Flow Graphs
![CFG searchBookByTitle](docs/CFG_searchBookByTitle.drawio.png)  
![CFG borrowBook](docs/borrowBook.drawio.png)

## Cyclomatic Complexity

Формула:  
CC = E - N + 2  
каде E = број на ребра (edges), N = број на јазли (nodes).  
Алтернативно: CC = број на услови + 1.

### searchBookByTitle
Оваа функција има еден услов – проверка дали насловот на книгата е ист со бараната.  
CC = 1 + 1 = **2**  
Објаснување: Еден услов создава две независни патеки → книгата е пронајдена или не е пронајдена.

### borrowBook
Оваа функција има два услови – прво се проверува дали книгата е достапна, а потоа дали корисникот има членска картичка.  
CC = 2 + 1 = **3**  
Објаснување: Два услови создаваат три независни патеки → успешно позајмување, нема картичка, или книгата е недостапна.

## 4. Gradle build

Креиран е Gradle проект за обична Java апликација со име `SI_lab2_231246`.

Главната класа е поставена во:

`src/main/java/org/example/SI2026Lab2Main.java`

Тест класата е поставена во:

`src/test/java/org/example/SI2026Lab2Test.java`

Во `build.gradle` е додаден Java plugin, Application plugin и JUnit 5 dependencies. Тестовите се извршуваат со JUnit Platform.

Проектот успешно се билдa и тестира со командата:

`./gradlew test`
Резултат:

`BUILD SUCCESSFUL`

Апликацијата може да се стартува со:

`./gradlew run`

## 6. Every Statement тест случаи за `searchBookByTitle`

За Every Statement критериумот за функцијата `searchBookByTitle` потребно е секоја наредба од функцијата да биде извршена барем еднаш. Функцијата се наоѓа во `src/main/java/org/example/SI2026Lab2Main.java`, линии 38-52.

Тестирањето е направено во функцијата `searchBookEveryStatementTest` во `src/test/java/org/example/SI2026Lab2Test.java`.

### Тест случаи

1. Барање книга што постои и не е изнајмена

Влез:
`title = "Clean Code"`

Очекуван резултат:
Функцијата враќа листа со две книги со наслов `Clean Code`.

Покриени линии:
38, 39, 42, 43, 44, 45, 48, 51

2. Барање книга што не постои

Влез:
`title = "Unknown Book"`

Очекуван резултат:
Функцијата враќа `null`.

Покриени линии:
38, 39, 42, 43, 44, 48, 49

3. Барање со празен наслов

Влез:
`title = ""`

Очекуван резултат:
Функцијата фрла `IllegalArgumentException`.

Покриени линии:
38, 39, 40

### Минимален број тест случаи

Минималниот број тест случаи за Every Statement критериумот е 3.

Објаснување:
Потребен е еден тест случај за да се изврши делот каде што книгата е пронајдена и се додава во резултатите, еден тест случај за да се изврши делот каде што нема резултати и се враќа `null`, и еден тест случај за да се изврши делот каде што празен наслов предизвикува `IllegalArgumentException`.

## 7. Every Branch тест случаи за `borrowBook`

За Every Branch критериумот потребно е секоја гранка од секој услов во функцијата `borrowBook` да биде извршена барем еднаш. Функцијата се наоѓа во `src/main/java/org/example/SI2026Lab2Main.java`, линии 54-70.

Тестирањето е направено во функцијата `borrowBookEveryBranchTest` во `src/test/java/org/example/SI2026Lab2Test.java`.

### Тест случаи

1. Успешно изнајмување книга

Влез:
`title = "Clean Code"`, `author = "Robert C. Martin"`

Очекуван резултат:
Книгата успешно се изнајмува и не се фрла исклучок.

Покриени гранки:
- условот `title.isEmpty() || author.isEmpty()` е `false`
- условот за совпаѓање на наслов и автор е `true`
- условот `!book.isBorrowed()` е `true`

2. Книгата е веќе изнајмена

Влез:
`title = "Effective Java"`, `author = "Joshua Bloch"`

Очекуван резултат:
Функцијата фрла `RuntimeException` со случај кога книгата е веќе изнајмена.

Покриени гранки:
- условот `title.isEmpty() || author.isEmpty()` е `false`
- условот за совпаѓање на наслов и автор е `true`
- условот `!book.isBorrowed()` е `false`

3. Книгата не е пронајдена

Влез:
`title = "Unknown Book"`, `author = "Unknown Author"`

Очекуван резултат:
Функцијата фрла `RuntimeException` затоа што книгата не е пронајдена.

Покриени гранки:
- условот `title.isEmpty() || author.isEmpty()` е `false`
- условот за совпаѓање на наслов и автор е `false`
- се извршува гранката по завршување на циклусот, односно `throw new RuntimeException("Book not found")`

4. Невалиден влез

Влез:
`title = ""`, `author = "Robert C. Martin"`

Очекуван резултат:
Функцијата фрла `IllegalArgumentException`.

Покриени гранки:
- условот `title.isEmpty() || author.isEmpty()` е `true`

### Минимален број тест случаи

Минималниот број тест случаи за Every Branch критериумот за функцијата `borrowBook` е 4.

Објаснување:
Потребен е еден тест за невалиден влез, еден тест за успешно изнајмување, еден тест за веќе изнајмена книга и еден тест за книга што не постои. Со овие тестови се покриваат сите `true` и `false` гранки од условите во функцијата.

## 8. Објаснување на Every Branch тест случаите за `borrowBook`

Функцијата `borrowBook` има неколку услови кои создаваат различни гранки:

```java
if (title.isEmpty() || author.isEmpty())
if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author))
if (!book.isBorrowed())
```

За да се исполни Every Branch критериумот, секоја можна гранка од овие услови треба да биде помината барем еднаш.

### Објаснување на тест случаите

1. Успешно изнајмување книга

Во овој тест се додава книга која постои во библиотеката и не е изнајмена.

Влез:
`borrowBook("Clean Code", "Robert C. Martin")`

Очекуван резултат:
Не се фрла исклучок и книгата се означува како изнајмена.

Покриени гранки:
- `title.isEmpty() || author.isEmpty()` -> `false`
- `book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)` -> `true`
- `!book.isBorrowed()` -> `true`

2. Книга која е веќе изнајмена

Во овој тест книгата прво се изнајмува, а потоа се прави втор обид за изнајмување на истата книга.

Влез:
`borrowBook("Effective Java", "Joshua Bloch")`

Очекуван резултат:
Се фрла `RuntimeException`, бидејќи книгата е веќе изнајмена.

Покриени гранки:
- `title.isEmpty() || author.isEmpty()` -> `false`
- `book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)` -> `true`
- `!book.isBorrowed()` -> `false`

3. Книга која не постои

Во овој тест во библиотеката има книга, но се бара друг наслов и друг автор.

Влез:
`borrowBook("Unknown Book", "Unknown Author")`

Очекуван резултат:
Се фрла `RuntimeException` со порака дека книгата не е пронајдена.

Покриени гранки:
- `title.isEmpty() || author.isEmpty()` -> `false`
- `book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)` -> `false`
- се извршува последната наредба `throw new RuntimeException("Book not found")`

4. Невалиден влез

Во овој тест насловот е празен стринг.

Влез:
`borrowBook("", "Robert C. Martin")`

Очекуван резултат:
Се фрла `IllegalArgumentException`.

Покриени гранки:
- `title.isEmpty() || author.isEmpty()` -> `true`

### Минимален број тест случаи

Минималниот број тест случаи за Every Branch критериумот за функцијата `borrowBook` е 4.

Потребни се четири тест случаи затоа што треба посебно да се покријат: невалиден влез, успешно изнајмување, веќе изнајмена книга и случај кога книгата не е пронајдена. Со овие случаи се покриваат сите важни `true` и `false` гранки во функцијата.

## 9. Multiple Condition тестови

Во Gradle проектот се додадени тестови според Multiple Condition критериумот за следните услови:

```java
if (title.isEmpty() || author.isEmpty())
```

во функцијата `borrowBook`, и:

```java
if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())
```

во функцијата `searchBookByTitle`.

Тестовите се напишани во `src/test/java/org/example/SI2026Lab2Test.java`.

Додадени се следните функции:

```java
searchBookMultipleConditionTest()
borrowBookMultipleConditionTest()
```

Функцијата `searchBookMultipleConditionTest` ги проверува сите комбинации за условот:

```java
book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()
```

Функцијата `borrowBookMultipleConditionTest` ги проверува сите комбинации за условот:

```java
title.isEmpty() || author.isEmpty()
```

## 10. Објаснување на Multiple Condition тест случаите

Multiple Condition критериумот бара да се проверат сите можни комбинации на подусловите во даден сложен услов.

### Multiple Condition за `searchBookByTitle`

Се тестира условот:

```java
book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed()
```

Подуслови:

- A = `book.getTitle().equalsIgnoreCase(title)`
- B = `!book.isBorrowed()`

Бидејќи условот користи `&&`, целосниот услов е точен само кога и A и B се точни.

Тестовите се напишани во функцијата `searchBookMultipleConditionTest`.

| Тест случај | A: насловот се совпаѓа | B: книгата не е изнајмена | A && B | Очекуван резултат |
|---|---|---|---|---|
| Книга со ист наслов и достапна книга | true | true | true | Се враќа листа со книгата |
| Книга со ист наслов, но изнајмена | true | false | false | Се враќа `null` |
| Книга со различен наслов и достапна книга | false | true | false | Се враќа `null` |
| Книга со различен наслов и изнајмена книга | false | false | false | Се враќа `null` |

Овие четири тест случаи ги покриваат сите можни комбинации на подусловите за условот во `searchBookByTitle`.

### Multiple Condition за `borrowBook`

Се тестира условот:

```java
title.isEmpty() || author.isEmpty()
```

Подуслови:

- A = `title.isEmpty()`
- B = `author.isEmpty()`

Бидејќи условот користи `||`, целосниот услов е точен ако барем еден од подусловите е точен.

Тестовите се напишани во функцијата `borrowBookMultipleConditionTest`.

| Тест случај | A: празен наслов | B: празен автор | A || B | Очекуван резултат |
|---|---|---|---|---|
| Празен наслов и празен автор | true | true | true | Се фрла `IllegalArgumentException` |
| Празен наслов и непразен автор | true | false | true | Се фрла `IllegalArgumentException` |
| Непразен наслов и празен автор | false | true | true | Се фрла `IllegalArgumentException` |
| Непразен наслов и непразен автор | false | false | false | Не се фрла исклучок и книгата се изнајмува |

Овие четири тест случаи ги покриваат сите можни комбинации на подусловите за условот во `borrowBook`.

### Минимален број тест случаи

За услов со два подуслови има `2^2 = 4` можни комбинации.

Минималниот број тест случаи за Multiple Condition критериумот е 4 за секој од условите.

Во оваа задача се тестираат два услови, па затоа се користат:

- 4 тест случаи за `searchBookByTitle`
- 4 тест случаи за `borrowBook`

Вкупно, за двата услови се потребни 8 комбинации. Во кодот тие се групирани во две тест функции: `searchBookMultipleConditionTest` и `borrowBookMultipleConditionTest`.
