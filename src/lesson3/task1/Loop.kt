@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var r = 1
    var x1 = n
    while (x1 >= 10) {

        r++
        x1 /= 10
    }
    return r
}


/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1) return 1
    if (n == 2) return 1
    var zero = 1
    var Na = 1
    var ta = 0
    repeat(n - 2) {
        ta = Na
        Na += zero
        zero = ta
    }
    return Na
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var del = 1
    while (true) {
        del++
        if (n % del == 0) return del
    }
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var del = n
    while (true) {
        del--
        if (n % del == 0) return del
    }

}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    if (x == 1) return 0
    var Xa = x
    var XCount = 0
    while (true) {
        when {
            Xa % 2 == 0 -> Xa /= 2
            else -> Xa = 3 * Xa + 1
        }
        XCount++
        if (Xa == 1) return XCount
    }
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    if (m == 1 && n == 1) return 1 else{
        if (m == n) return n else {
            var a1 = 1
            while (a1 < m * n) {
                a1++
                if (a1 % m == 0 && a1 % n == 0) return a1
            }
            return a1
        }
    }
    return -1
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..max(m, n)) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var i = 0
    var temp = 0
    var n1 = n
    while (n1 > 0) {
        temp = n1 % 10
        n1 /= 10
        i = i * 10 + temp
    }
    return i
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1 = n
    var prev = 0
    var cur = n % 10
    while (n1 >= 1) {
        prev = n1 % 10
        n1 /= 10
        if (n1 >= 1) {
            cur = n1 % 10
        }
        if (cur != prev) return true
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    fun lenOf(x: Int): Int {
        var r = 1
        var x1 = x
        while (x1 >= 10) {

            r++
            x1 /= 10
        }
        return r
    }

    fun numOf(x: Int, y: Int): Int {
        var x1 = x
        var y1 = y
        var i = if (!(y1 <= 0 || y1 > lenOf(x1))) {
            y1 = lenOf(x1) - y1 + 1
            while (y1 > 1) {
                x1 /= 10
                y1--
            }
            return x1 % 10
        } else return -1

        return i
    }

    if(n == 1) return 1 else {
        var len = 0
        var j = 1
        var cur = 1
        while (len + lenOf(j) < n) {
            len += lenOf(j)
            cur++
            j = cur * cur
        }
        return numOf(j, n - len)
    }
    return -1
}


/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    fun lenOf(x: Int): Int {
        var r = 1
        var x1 = x
        while (x1 >= 10) {

            r++
            x1 /= 10
        }
        return r
    }

    fun NumOf(x: Int, y: Int): Int {
        var x1 = x
        var y1 = y
        var i = if (!(y1 <= 0 || y1 > lenOf(x1))) {
            y1 = lenOf(x1) - y1 + 1
            while (y1 > 1) {
                x1 /= 10
                y1--
            }
            return x1 % 10
        } else return -1

        return i
    }
    if (n < 3) return 1
    else {
        var len = 2
        var prev = 1
        var cur = 1
        var temp = prev + cur
        while (len + lenOf(temp) < n) {
            len += lenOf(temp)
            prev = cur
            cur = temp
            temp = prev + cur
        }
        return NumOf(temp, n - len)
    }
    return -1
}
