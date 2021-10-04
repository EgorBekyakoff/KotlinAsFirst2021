@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import ru.spbstu.kotlin.typeclass.classes.Default
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {
    ((age < 21 && age > 4) || (age < 121 && age > 104) || (age < 221 && age > 204)) -> "$age лет"
    (age < 100) && ((((age % 10) >= 5) && ((age % 10) <= 9)) || ((age % 10) == 0)) -> "$age лет"
    (age < 100) && ((age % 10) == 1) -> "$age год"
    (age < 100) && (age % 10 in 2..4) -> "$age года"
    (age >= 100) && (age % 10 % 10 in 5..9) || (age % 10 % 10 == 0) -> "$age лет"
    (age >= 100) && (age % 10 % 10 == 1) -> "$age год"
    (age >= 100) && (age % 10 % 10 in 2..4) -> "$age года"
    else -> "fatal eror"
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double = when{
    t1 * v1 > (t1 * v1 + t2 * v2 + t3  * v3) / 2 -> (t1 * v1 + t2 * v2 + t3 * v3) / v1 / 2
    t1 * v1 + t2 * v2 > (t1 * v1 + t2 * v2 + t3  * v3) / 2 -> t1 + ((t1 * v1 + t2 * v2 + t3 * v3) / 2 - t1 * v1 ) / v2
    t1 * v1 + t2 * v2 + t3 * v3 > (t1 * v1 + t2 * v2 + t3  * v3) / 2 -> t1 + t2 + (((t1 * v1 + t2 * v2 + t3 * v3) / 2 - t1 * v1 - t2 * v2) / v3)

    else -> -1.0
}
//{
   // val s = ((t1 * v1 + t2 * v2 + t3 * v3) / 2)
   // return when {
    //    t1 * v1 + t2 * v2 >= s -> t1 + (s - v1 * t1) / v2

      //  t2 * v2 >= s -> v1 * t1 / v1 + ((v1 * t1 + v2 * t2) - s) / v2
     //   t3 * v3 >= s -> v1 * t1 / v1 + ((v1 * t1 + v2 * t2 + v3 * t3) - s) / v3
    //    t1 * v1 >= s -> s / v1

     //   else -> 0.0
   // }


//}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when{
    ((kingX == rookX1 || kingX == rookX2) && (rookX1 != rookX2)) && ((kingY == rookY1 || kingY == rookY2) && (rookY1 != rookY2)) -> 3
    (kingX == rookX1 && (rookX2 !in kingX..rookX1)) || (kingY == rookY1 && (rookY2 !in kingY..rookY1)) -> 1
    (kingX == rookX2 && (rookX1 !in kingX..rookX2)) || (kingY == rookY2 && (rookY1 !in kingY..rookY2)) -> 2
    kingX != rookX1 && kingX != rookX2 && kingY != rookY1 && kingY != rookY2 -> 0
    else -> 5
    //    ((((kingX == rookX1) && (((rookY1 != rookY2) || ((rookX2 < kingX) && (rookX2 > kingX))) || ((rookY1 != rookY2) || ((rookX2 > kingX) && (rookX2 < kingX))))) || ((kingY == rookY1) && (((rookY2 < kingY) && (rookY2 > kingY)) || ((rookY2 > kingY) && (rookY2 < kingY)))))) -> 1
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int = when{
    (kingX == rookX || kingY == rookY) && !(abs(kingX - bishopX) == abs(kingY - bishopY)) -> 1
    (abs(kingX - bishopX) == abs(kingY - bishopY)) && !(kingX == rookX || kingY == rookY) -> 2
    (kingX == rookX || kingY == rookY) && (abs(kingX - bishopX) == abs(kingY - bishopY)) -> 3
    else -> 0
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int = when {
    a + b <= c || a + c <= b || b + c <= a -> -1
    ((maxOf(a, b, c) * maxOf(a, b, c)) == a * a + b * b + c * c - (maxOf(a, b, c) * maxOf(a, b, c))) -> 1
    ((maxOf(a, b, c) * maxOf(a, b, c)) / (a * a + b * b + c * c - (maxOf(a, b, c) * maxOf(a, b, c)) - 2 * a * b * c / maxOf(a, b, c))) < 0 -> 1

    a + b <= c || a + c <= b || b + c <= a -> -1
else -> 4
}


/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int { return when {
        b < c -> -1
        a in c..d && b in c..d -> b - a
        c in a..b && d in a..b -> d - c
        a !in c..d && b in c..d -> b - c
        a in c.. d && b !in c..d -> d - a
        else -> -1
}
}