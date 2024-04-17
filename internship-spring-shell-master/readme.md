## Задача. Shell

### Описание

> В ресурсах находится файл с курсами валют - [currencies.tsv](src/main/resources/tsv/currencies.tsv)
>
> Значения разделены символом табуляции

### Задача

Необходимо написать консольное приложение на Spring Boot + String Shell для конвертации валют используя данные из файла.

Необходимо реализовать следующий методы:

1. `codes` - команда возвращает список всех доступных валют из файла

###### Пример

```shell
shell:> codes
AUD Австралийский доллар
EUR Евро
AMD Армянский драм
...
```

2. `convert` - команда проводит конвертацию переданной суммы из одной валюты в другую

###### Аргументы

* `amount` - кол-во ден. единиц, которые необходимо перевести
* `from` - из какой валюты осуществляется перевод
* `to` - желаемая валюта

###### Пример

```shell
shell:> convert 100 --from RUB --to USD
USD: 1.26
```

### Подсказка

[Документация Spring Shell](https://docs.spring.io/spring-shell/docs/current/reference/htmlsingle/)

```java
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent //аннотация необходима чтобы Spring увидел класс как класс-обработчик консоли 
public class Controller {

    @ShellMethod("Method returns greetings") //@ShellMethod - обязательная аннотация, без нее команда не будет доступна для вызова (spring не будет ее видеть), описание внутри тоже обязательно
    public String hello(@ShellOption String who) { 
        //@ShellOption необязательная аннотация, но бывает полезна, если нужно изменить
        // обязательность или название аргумента
        return "Hello " + who;
    }
    
}
```

