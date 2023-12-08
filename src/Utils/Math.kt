package Utils


/* Least Common Multiplier */

fun lcm(a: Long, b: Long): Long{
    return a * b / gcd(a,b)
}

/* Euclidean algorithm to calculate gcd */
fun gcd(a: Long, b: Long): Long{

    return if(b == 0L)
        a
    else
        gcd(b, a % b)

}
