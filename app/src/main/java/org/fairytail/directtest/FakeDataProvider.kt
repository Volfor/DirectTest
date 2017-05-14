package org.fairytail.directtest

import io.realm.Realm
import org.fairytail.directtest.models.QuestionType
import org.fairytail.directtest.models.RealmAnswer
import org.fairytail.directtest.models.RealmQuestion
import org.fairytail.directtest.models.RealmTest
import java.util.concurrent.TimeUnit

/**
 * Created by Alex on 5/13/2017.
 * GitHub: https://github.com/s0nerik
 * LinkedIn: https://linkedin.com/in/sonerik
 */

object FakeDataProvider {
    fun provideFakeTests() {
        Realm.getDefaultInstance().use { r ->
            r.executeTransaction { realm ->
                realm.deleteAll()

                createOOPTest(realm)
                createProgrammingTest(realm)
                createCppTest(realm)

//                (0..25).forEach {
//                    val test = realm.createObject(RealmTest::class.java, Db.randomUuidString())
//                    test.name = "Test "+it
//                    test.time = TimeUnit.MINUTES.toMillis(25)
//                    (0..Random().nextInt(15)).forEach {
//                        val question = realm.createObject(RealmQuestion::class.java)
//                        question.typeEnum = QuestionType.values()[Random().nextInt(QuestionType.values().size)]
//                        question.text = "Test question $it"
//                        createAnswers(realm, question)
//                        test.questions!!.add(question)
//                    }
//                }
            }
        }
    }

//    private fun createAnswers(realm: Realm, question: RealmQuestion) {
//        val answers: MutableList<RealmAnswer> = when (question.typeEnum) {
//            QuestionType.SINGLE_ANSWER -> {
//                val answers = mutableListOf<RealmAnswer>()
//
//                val correctAnswer = realm.createObject(RealmAnswer::class.java)
//                correctAnswer.text = "Correct"
//                correctAnswer.checked = false
//                correctAnswer.isCorrect = true
//
//                answers += correctAnswer
//
//                (2..2 + Random().nextInt(5)).forEach {
//                    val incorrectAnswer = realm.createObject(RealmAnswer::class.java)
//                    incorrectAnswer.text = "Incorrect $it"
//                    incorrectAnswer.checked = false
//                    incorrectAnswer.isCorrect = false
//                    answers += incorrectAnswer
//                }
//                answers
//            }
//            QuestionType.MULTIPLE_ANSWERS -> {
//                val answers = mutableListOf<RealmAnswer>()
//                (2..2 + Random().nextInt(4)).forEach {
//                    val correctAnswer = realm.createObject(RealmAnswer::class.java)
//                    correctAnswer.text = "Correct $it"
//                    correctAnswer.checked = false
//                    correctAnswer.isCorrect = true
//                    answers += correctAnswer
//                }
//
//                (2..2 + Random().nextInt(4)).forEach {
//                    val answer = realm.createObject(RealmAnswer::class.java)
//                    answer.text = "Incorrect $it"
//                    answer.checked = false
//                    answer.isCorrect = false
//                    answers += answer
//                }
//
//                answers
//            }
//            QuestionType.INPUT_NUMBER -> {
//                val correctAnswer = realm.createObject(RealmAnswer::class.java)
//                correctAnswer.text = "42"
//                correctAnswer.checked = false
//                correctAnswer.isCorrect = true
//
//                val userAnswer = realm.createObject(RealmAnswer::class.java)
//                userAnswer.text = ""
//                userAnswer.checked = false
//                userAnswer.isCorrect = false
//
//                mutableListOf(correctAnswer, userAnswer)
//            }
//            QuestionType.INPUT_TEXT -> {
//                val correctAnswer = realm.createObject(RealmAnswer::class.java)
//                correctAnswer.text = "Correct"
//                correctAnswer.checked = false
//                correctAnswer.isCorrect = true
//
//                val userAnswer = realm.createObject(RealmAnswer::class.java)
//                userAnswer.text = ""
//                userAnswer.checked = false
//                userAnswer.isCorrect = false
//
//                mutableListOf(correctAnswer, userAnswer)
//            }
//        }
//
//        question.answers!!.addAll(answers)
//    }
}

fun createOOPTest(realm: Realm) {
    val test = realm.createObject(RealmTest::class.java, Db.randomUuidString())
    test.name = "ООП - Основы"
    test.time = TimeUnit.MINUTES.toMillis(15)

    val question1 = realm.createObject(RealmQuestion::class.java)
    question1.typeEnum = QuestionType.SINGLE_ANSWER
    question1.text = "Что из перечисленного является преимуществом объектно-ориентированного подхода к программированию перед структурным программированием:"
    val answers1 = mutableListOf<RealmAnswer>()
    answers1 += createIncorrectAnswer(realm, "В ООП не используются функции и процедуры")
    answers1 += createCorrectAnswer(realm, "ООП позволяет объединять состояние объектов и их поведение")
    answers1 += createIncorrectAnswer(realm, "ООП не поддерживает повторное использование компонентов")
    answers1 += createIncorrectAnswer(realm, "ООП поддерживает разработку программ \"сверху-вниз\"")
    answers1 += createIncorrectAnswer(realm, "ООП не поддерживает понятие абстракции")
    question1.answers!!.addAll(answers1)
    test.questions!!.add(question1)

    val question2 = realm.createObject(RealmQuestion::class.java)
    question2.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question2.text = "Делегат - ____ . Укажите все верные утверждения"
    val answers2 = mutableListOf<RealmAnswer>()
    answers2 += createCorrectAnswer(realm, "это тип, который определяет сигнатуру метода и может обеспечивать связь с любым методом с совместимой сигнатурой")
    answers2 += createCorrectAnswer(realm, "используется для передачи методов в качестве аргументов к другим методам")
    answers2 += createIncorrectAnswer(realm, "это модификатор уровня агрегируемого метода")
    answers2 += createIncorrectAnswer(realm, "это не явная реализация полиморфизма")
    question2.answers!!.addAll(answers2)
    test.questions!!.add(question2)

    val question3 = realm.createObject(RealmQuestion::class.java)
    question3.typeEnum = QuestionType.SINGLE_ANSWER
    question3.text = "Иерархическое наследование(hierarchical inheritance):"
    val answers3 = mutableListOf<RealmAnswer>()
    answers3 += createIncorrectAnswer(realm, "Содержит один базовый класс и один производный класс")
    answers3 += createCorrectAnswer(realm, "Содержит один базовый класс и несколько производных классов одного и того же базового класса")
    answers3 += createIncorrectAnswer(realm, "Содержит класс, производный от производного класса")
    answers3 += createIncorrectAnswer(realm, "Содержит несколько базовых классов и производный класс")
    question3.answers!!.addAll(answers3)
    test.questions!!.add(question3)

    val question4 = realm.createObject(RealmQuestion::class.java)
    question4.typeEnum = QuestionType.SINGLE_ANSWER
    question4.text = "Как называется способность объекта скрывать свои данные и реализацию от других объектов системы?"
    val answers4 = mutableListOf<RealmAnswer>()
    answers4 += createIncorrectAnswer(realm, "Полиморфизм")
    answers4 += createCorrectAnswer(realm, "Инкапсуляция")
    answers4 += createIncorrectAnswer(realm, "Абстракция")
    answers4 += createIncorrectAnswer(realm, "Наследование")
    question4.answers!!.addAll(answers4)
    test.questions!!.add(question4)

    val question5 = realm.createObject(RealmQuestion::class.java)
    question5.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question5.text = "Выберите все существующие группы шаблонов проектирования:"
    val answers5 = mutableListOf<RealmAnswer>()
    answers5 += createCorrectAnswer(realm, "Порождающие шаблоны")
    answers5 += createIncorrectAnswer(realm, "Фабричные шаблоны")
    answers5 += createCorrectAnswer(realm, "Поведенческие шаблоны")
    answers5 += createIncorrectAnswer(realm, "Стратегические шаблоны")
    answers5 += createCorrectAnswer(realm, "Структурные шаблоны")
    question5.answers!!.addAll(answers5)
    test.questions!!.add(question5)

    val question6 = realm.createObject(RealmQuestion::class.java)
    question6.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question6.text = "Выберите корректные утверждения, связанные с понятием полиморфизма :"
    val answers6 = mutableListOf<RealmAnswer>()
    answers6 += createIncorrectAnswer(realm, "полиморфизм никак не связан с наследованием")
    answers6 += createIncorrectAnswer(realm, "клиенты полиморфных классов всегда знают о всех вариантах реализации полиморфного поведения")
    answers6 += createIncorrectAnswer(realm, "полиморфизм реализуется только с помощью шаблонов (параметризуемых классов)")
    answers6 += createCorrectAnswer(realm, "полиморфизм - это возможность существования разных вариантов реализации одноименного действия")
    answers6 += createCorrectAnswer(realm, "клиенты полиморфных классов могут знать только об их общем интерфейсе")
    question6.answers!!.addAll(answers6)
    test.questions!!.add(question6)

    val question7 = realm.createObject(RealmQuestion::class.java)
    question7.typeEnum = QuestionType.SINGLE_ANSWER
    question7.text = "Система обеспечивает принцип наследования в том случае, если:"
    val answers7 = mutableListOf<RealmAnswer>()
    answers7 += createCorrectAnswer(realm, "в любом месте, где допустимо использование объекта, принадлежащего классу-предку, точно также допустимо использование класса-наследника.")
    answers7 += createIncorrectAnswer(realm, "в любом месте, где допустимо использование объекта, принадлежащего классу-наследнику, точно также допустимо использование класса-предка.")
    answers7 += createIncorrectAnswer(realm, "в системе имеется хотя бы один \"наследник\"")
    question7.answers!!.addAll(answers7)
    test.questions!!.add(question7)

    val question8 = realm.createObject(RealmQuestion::class.java)
    question8.typeEnum = QuestionType.SINGLE_ANSWER
    question8.text = "Словом \"агрегация\" (включение, композиция) точнее всего описывается отношение между..."
    val answers8 = mutableListOf<RealmAnswer>()
    answers8 += createIncorrectAnswer(realm, "...вашей комнатой и комнатой ваших соседей")
    answers8 += createCorrectAnswer(realm, "...вашей комнатой и мебелью в ней")
    answers8 += createIncorrectAnswer(realm, "...вами и вашими друзьями")
    answers8 += createIncorrectAnswer(realm, "...вами и вашими руками")
    question8.answers!!.addAll(answers8)
    test.questions!!.add(question8)

    val question9 = realm.createObject(RealmQuestion::class.java)
    question9.typeEnum = QuestionType.SINGLE_ANSWER
    question9.text = "Какая разница между объектом и классом?"
    val answers9 = mutableListOf<RealmAnswer>()
    answers9 += createIncorrectAnswer(realm, "Класс - это исходный код, а объект - скомпилированный и выполняемый код")
    answers9 += createIncorrectAnswer(realm, "Класс описывает категорию, к которой могут либо принадлежать, либо не принадлежать объекты данного класса")
    answers9 += createIncorrectAnswer(realm, "Класс может иметь много экземпляров, а объект - один или ниодного")
    answers9 += createIncorrectAnswer(realm, "Класс может инстанциировать объекты, а сам объект - нет")
    answers9 += createCorrectAnswer(realm, "Объект - это экземпляр класса")
    question9.answers!!.addAll(answers9)
    test.questions!!.add(question9)

    val question10 = realm.createObject(RealmQuestion::class.java)
    question10.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question10.text = "Выберите все порождающие шаблоны проектирования:"
    val answers10 = mutableListOf<RealmAnswer>()
    answers10 += createCorrectAnswer(realm, "Фабричный метод (Factory method)")
    answers10 += createIncorrectAnswer(realm, "Фасад (Facade)")
    answers10 += createCorrectAnswer(realm, "Прототип (Prototype)")
    answers10 += createCorrectAnswer(realm, "Одиночка (Singleton)")
    answers10 += createCorrectAnswer(realm, "Строитель (Builder)")
    answers10 += createCorrectAnswer(realm, "Пул одиночек (Multiton)")
    question10.answers!!.addAll(answers10)
    test.questions!!.add(question10)

    val question11 = realm.createObject(RealmQuestion::class.java)
    question11.typeEnum = QuestionType.SINGLE_ANSWER
    question11.text = "В объектно-ориентированных языках программирования полиморфизм обеспечивается с помощью:"
    val answers11 = mutableListOf<RealmAnswer>()
    answers11 += createIncorrectAnswer(realm, "Передачи аргументов по ссылке")
    answers11 += createIncorrectAnswer(realm, "Ограничения доступа к полям и методам")
    answers11 += createIncorrectAnswer(realm, "Статических полей")
    answers11 += createIncorrectAnswer(realm, "Статических методов")
    answers11 += createCorrectAnswer(realm, "Виртуальных методов")
    answers11 += createIncorrectAnswer(realm, "Защищённых полей")
    question11.answers!!.addAll(answers11)
    test.questions!!.add(question11)

    val question12 = realm.createObject(RealmQuestion::class.java)
    question12.typeEnum = QuestionType.SINGLE_ANSWER
    question12.text = "Можно ли конструктор пометить c помощью модификатора virtual?"
    val answers12 = mutableListOf<RealmAnswer>()
    answers12 += createIncorrectAnswer(realm, "При использовании модификатора доступа protected, конструкторы могут быть виртуальными")
    answers12 += createIncorrectAnswer(realm, "Конструкторы могут быть виртуальными только в исключительных ситуациях")
    answers12 += createIncorrectAnswer(realm, "При использовании модификатора доступа static, конструкторы могут быть виртуальными")
    answers12 += createCorrectAnswer(realm, "Конструкторы не могут быть виртуальными")
    question12.answers!!.addAll(answers12)
    test.questions!!.add(question12)

    val question13 = realm.createObject(RealmQuestion::class.java)
    question13.typeEnum = QuestionType.SINGLE_ANSWER
    question13.text = "Перечислите недостатки ООП парадигмы."
    val answers13 = mutableListOf<RealmAnswer>()
    answers13 += createIncorrectAnswer(realm, "Недостаточная гибкость в создании иерархии")
    answers13 += createIncorrectAnswer(realm, "Невозможность повторного использования кода")
    answers13 += createCorrectAnswer(realm, "Поддержка языком ООП требует дополнительных ресурсов")
    answers13 += createIncorrectAnswer(realm, "Невозможность абстракции")
    answers13 += createCorrectAnswer(realm, "Избыточность")
    question13.answers!!.addAll(answers13)
    test.questions!!.add(question13)
}

fun createProgrammingTest(realm: Realm) {
    val test = realm.createObject(RealmTest::class.java, Db.randomUuidString())
    test.name = "Программирование"
    test.time = TimeUnit.MINUTES.toMillis(12)

    val question1 = realm.createObject(RealmQuestion::class.java)
    question1.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question1.text = "Какие виды типизаций переменных существуют?"
    val answers1 = mutableListOf<RealmAnswer>()
    answers1 += createCorrectAnswer(realm, "утиная типизация")
    answers1 += createCorrectAnswer(realm, "динамическая типизация")
    answers1 += createCorrectAnswer(realm, "статическая типизация")
    answers1 += createCorrectAnswer(realm, "строгая типизация")
    answers1 += createIncorrectAnswer(realm, "средняя типизация")
    answers1 += createCorrectAnswer(realm, "нестрогая типизация")
    answers1 += createIncorrectAnswer(realm, "гусиная типизация")
    question1.answers!!.addAll(answers1)
    test.questions!!.add(question1)

    val question2 = realm.createObject(RealmQuestion::class.java)
    question2.typeEnum = QuestionType.SINGLE_ANSWER
    question2.text = "Множество значений, которые может принимать переменная, а также множество операций, допустимых над данной переменной, определяется ..."
    val answers2 = mutableListOf<RealmAnswer>()
    answers2 += createCorrectAnswer(realm, "типом данных переменной")
    answers2 += createIncorrectAnswer(realm, "структурой данных")
    answers2 += createIncorrectAnswer(realm, "областью видимости переменной")
    answers2 += createIncorrectAnswer(realm, "ничем из перечисленного")
    question2.answers!!.addAll(answers2)
    test.questions!!.add(question2)

    val question3 = realm.createObject(RealmQuestion::class.java)
    question3.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question3.text = "Скорость которых с нижеперечисленных алгоритмов в лучшем случае составляет O(n) ?"
    val answers3 = mutableListOf<RealmAnswer>()
    answers3 += createIncorrectAnswer(realm, "Сортировка выбором")
    answers3 += createCorrectAnswer(realm, "Сортировка пузырьком")
    answers3 += createCorrectAnswer(realm, "Сортировка вставками")
    answers3 += createIncorrectAnswer(realm, "Быстрая сортировка")
    question3.answers!!.addAll(answers3)
    test.questions!!.add(question3)

    val question4 = realm.createObject(RealmQuestion::class.java)
    question4.typeEnum = QuestionType.SINGLE_ANSWER
    question4.text = "Чем отличаются унарные операторы от бинарных?"
    val answers4 = mutableListOf<RealmAnswer>()
    answers4 += createIncorrectAnswer(realm, "Бинарные операторы используются для чисел в бинарной системе счисления")
    answers4 += createCorrectAnswer(realm, "Бинарные операторы работают с двумя операндами")
    answers4 += createIncorrectAnswer(realm, "Принципиальной разницы нет")
    answers4 += createIncorrectAnswer(realm, "Понятия \"унарный оператор\" не существует")
    question4.answers!!.addAll(answers4)
    test.questions!!.add(question4)

    val question5 = realm.createObject(RealmQuestion::class.java)
    question5.typeEnum = QuestionType.SINGLE_ANSWER
    question5.text = "Что такое компиляция"
    val answers5 = mutableListOf<RealmAnswer>()
    answers5 += createIncorrectAnswer(realm, "Проверка компилятором правильности написания программного кода")
    answers5 += createIncorrectAnswer(realm, "Трансляция программы на машинный язык более высокого уровня и проверка правильности написания кода")
    answers5 += createCorrectAnswer(realm, "Трансляция программы, составленной на языке высокого уровня, в эквивалентную программу на низкоуровневом языке")
    answers5 += createIncorrectAnswer(realm, "Ничего из перечисленного выше")
    question5.answers!!.addAll(answers5)
    test.questions!!.add(question5)

    val question6 = realm.createObject(RealmQuestion::class.java)
    question6.typeEnum = QuestionType.SINGLE_ANSWER
    question6.text = "Какой метод программирования подразумевает создание конечной программы путем объединения ранее созданных мелких программных модулей (вспомогательных) в более крупные?"
    val answers6 = mutableListOf<RealmAnswer>()
    answers6 += createIncorrectAnswer(realm, "Сверху-вниз")
    answers6 += createIncorrectAnswer(realm, "Слева-направо")
    answers6 += createCorrectAnswer(realm, "Снизу-вверх")
    answers6 += createIncorrectAnswer(realm, "Справа-налево")
    question6.answers!!.addAll(answers6)
    test.questions!!.add(question6)

    val question7 = realm.createObject(RealmQuestion::class.java)
    question7.typeEnum = QuestionType.SINGLE_ANSWER
    question7.text = "Дано булевое выражение: not A or not B. Какому из следующих выражений оно эквивалентно:"
    val answers7 = mutableListOf<RealmAnswer>()
    answers7 += createIncorrectAnswer(realm, "not ( A or B )")
    answers7 += createCorrectAnswer(realm, "not ( A and B )")
    answers7 += createIncorrectAnswer(realm, "A and B")
    answers7 += createIncorrectAnswer(realm, "not ( not A and not B )")
    question7.answers!!.addAll(answers7)
    test.questions!!.add(question7)

    val question8 = realm.createObject(RealmQuestion::class.java)
    question8.typeEnum = QuestionType.SINGLE_ANSWER
    question8.text = "Что такое инициализация переменной:"
    val answers8 = mutableListOf<RealmAnswer>()
    answers8 += createIncorrectAnswer(realm, "внесение первоначального значения с помощью оператора ввода")
    answers8 += createCorrectAnswer(realm, "внесение первоначального значения с помощью оператора присвоения")
    answers8 += createIncorrectAnswer(realm, "объявление в разделе описания переменных")
    question8.answers!!.addAll(answers8)
    test.questions!!.add(question8)
}

fun createCppTest(realm: Realm) {
    val test = realm.createObject(RealmTest::class.java, Db.randomUuidString())
    test.name = "C++ - Основы"
    test.time = TimeUnit.MINUTES.toMillis(18)

    val question1 = realm.createObject(RealmQuestion::class.java)
    question1.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question1.text = "Какие из перечисленных фрагментов кода не содержат ошибки?"
    val answers1 = mutableListOf<RealmAnswer>()
    answers1 += createCorrectAnswer(realm, "// 2 \nconst int i = 0; \nint j = i;")
    answers1 += createIncorrectAnswer(realm, "// 1 \nconst int i = 0; \nint &j = i;")
    answers1 += createCorrectAnswer(realm, "// 3 \nint i = 0; \nint j = i;")
    question1.answers!!.addAll(answers1)
    test.questions!!.add(question1)

    val question2 = realm.createObject(RealmQuestion::class.java)
    question2.typeEnum = QuestionType.MULTIPLE_ANSWERS
    question2.text = "Определите верную запись значения 23,45 умноженное на 10 в 10-ой степени."
    val answers2 = mutableListOf<RealmAnswer>()
    answers2 += createIncorrectAnswer(realm, "2.345PE11")
    answers2 += createIncorrectAnswer(realm, "23.45P10")
    answers2 += createIncorrectAnswer(realm, "23.45PE10")
    answers2 += createIncorrectAnswer(realm, "2.345P11")
    answers2 += createCorrectAnswer(realm, "2.345E11")
    question2.answers!!.addAll(answers2)
    test.questions!!.add(question2)

    val question3 = realm.createObject(RealmQuestion::class.java)
    question3.typeEnum = QuestionType.SINGLE_ANSWER
    question3.text = "Пусть а = -5. Сколько раз выполнится тело следующего цикла?\nwhile (a < 0) a++;"
    val answers3 = mutableListOf<RealmAnswer>()
    answers3 += createIncorrectAnswer(realm, "0 раз")
    answers3 += createIncorrectAnswer(realm, "6 раз")
    answers3 += createIncorrectAnswer(realm, "4 раза")
    answers3 += createCorrectAnswer(realm, "5 раз")
    question3.answers!!.addAll(answers3)
    test.questions!!.add(question3)

    val question4 = realm.createObject(RealmQuestion::class.java)
    question4.typeEnum = QuestionType.SINGLE_ANSWER
    question4.text = "Сколько конструкторов и деструкторов может быть у одного класса (выберите наиболее точный вариант)?"
    val answers4 = mutableListOf<RealmAnswer>()
    answers4 += createIncorrectAnswer(realm, "Один конструктор, один деструктор")
    answers4 += createCorrectAnswer(realm, "Несколько конструкторов, один деструктор")
    answers4 += createIncorrectAnswer(realm, "Несколько конструкторов, несколько деструкторов")
    answers4 += createIncorrectAnswer(realm, "Один конструктор, несколько деструкторов")
    question4.answers!!.addAll(answers4)
    test.questions!!.add(question4)
}

fun createCorrectAnswer(realm: Realm, text: String): RealmAnswer {
    val correctAnswer = realm.createObject(RealmAnswer::class.java)
    correctAnswer.text = text
    correctAnswer.checked = false
    correctAnswer.isCorrect = true
    return correctAnswer
}

fun createIncorrectAnswer(realm: Realm, text: String): RealmAnswer {
    val incorrectAnswer = realm.createObject(RealmAnswer::class.java)
    incorrectAnswer.text = text
    incorrectAnswer.checked = false
    incorrectAnswer.isCorrect = false
    return incorrectAnswer
}