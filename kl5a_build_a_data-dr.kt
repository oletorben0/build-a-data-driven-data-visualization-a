// Import necessary libraries
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.bar
import org.jetbrains.letsPlot.geom.point
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.letsPlot

// Sample dataset
data class DataPoint(val x: Int, val y: Int, val category: String)

val data = listOf(
    DataPoint(1, 2, "A"),
    DataPoint(2, 3, "B"),
    DataPoint(3, 1, "A"),
    DataPoint(4, 4, "B"),
    DataPoint(5, 3, "A"),
    DataPoint(6, 2, "B")
)

// Data visualization analyzer function
fun analyzeData(data: List<DataPoint>) {
    // Create a Lets-Plot figure
    val p: Plot = letsPlot(data) {
        x("x")
        y("y")
        geomPoint {
            color("category").add()
        }
        geomBar {
            aes(x = "x", y = "count"), stat = "count"
        }
    }

    // Display the plot
    p.show()

    // Calculate and display summary statistics
    val summary = data.groupBy { it.category }.map { (category, points) ->
        val sum = points.sumOf { it.y }
        val avg = sum.toDouble() / points.size
        val max = points.maxOf { it.y }
        val min = points.minOf { it.y }
        println("Category $category: sum = $sum, avg = $avg, max = $max, min = $min")
    }
}

// Run the analyzer
analyzeData(data)