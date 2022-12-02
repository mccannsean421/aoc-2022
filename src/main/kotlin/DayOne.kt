import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val lineList = getInput()

    var caloriesForEachElf = mutableListOf<Int>()
    var calorieCountForElf = 0

    lineList.forEach{
        if (it.length > 0) {
            calorieCountForElf += it.toInt()
        } else {
            caloriesForEachElf.add(calorieCountForElf)
            calorieCountForElf = 0
        }
    }

    println(part1(caloriesForEachElf))
    println(part2(caloriesForEachElf))

}

private fun part1(caloriesForEachElf: List<Int>): Int {
    return caloriesForEachElf.maxOrNull() ?: 0
}

private fun part2(caloriesForEachElf: List<Int>): Int {
    return caloriesForEachElf.sortedDescending().take(3).sum()
}

private fun getInput(): List<String> {
    val inputStream: InputStream = File("src/main/resources/day-1.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    return lineList
}
