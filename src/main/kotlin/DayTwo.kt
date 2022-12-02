import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partTwo() {
    var totalScore = 0
    val encryptedStrategyGuide = getInput();
    encryptedStrategyGuide.forEach{
        totalScore += determineWinLoseOrDraw(it)
    }

    println(totalScore)
}

fun determineWinLoseOrDraw(move: String): Int {
    val opponentShape = move[0]
    val outcome = move[2]
    val pointsForRound = if (outcome == 'Z') 6 else if (outcome == 'Y') 3 else 0
    val myShape: Char = when (opponentShape) {
        'A' -> if (outcome == 'Z') 'B' else if (outcome == 'Y') 'A' else 'C'
        'B' -> if (outcome == 'Z') 'C' else if (outcome == 'Y') 'B' else 'A'
        'C' -> if (outcome == 'Z') 'A' else if (outcome == 'Y') 'C' else 'B'
        else -> '!'
    }

    return pointsForRound + getShapePoints(myShape)
}

fun partOne() {
    var totalScore = 0

    val encryptedStrategyGuide = getInput();
    encryptedStrategyGuide.forEach{
        totalScore += checkWhoWinsAndTallyScore(it)
    }

    println(totalScore)
}

fun checkWhoWinsAndTallyScore(move: String): Int {
    val wins = mutableListOf<String>("A Y", "B Z", "C X")
    val loses = mutableListOf<String>("A Z", "B X", "C Y")
    val draws = mutableListOf<String>("A X", "B Y", "C Z")

    val pointsForDraw = 3
    val pointsForWin = 6

    if (wins.contains(move)) {
        return pointsForWin + getShapePoints(move[2])
    } else if (draws.contains(move)) {
        return pointsForDraw + getShapePoints(move[2])
    } else {
        return getShapePoints(move[2])
    }
}

fun getShapePoints(letter: Char): Int {
    val points = when (letter) {
        'X', 'A' -> 1
        'Y', 'B' -> 2
        'Z', 'C' -> 3
        else -> 0
    }
    return points
}

private fun getInput(): List<String> {
    val inputStream: InputStream = File("src/main/resources/day-2.txt").inputStream()
    val moveList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { moveList.add(it) }
    return moveList
}