import com.vividsolutions.jump.coordsys.CoordinateSystem
import com.vividsolutions.jump.io.datasource.DataSource
import com.vividsolutions.jump.io.datasource.DataSourceQuery
import com.vividsolutions.jump.io.datasource.StandardReaderWriterFileDataSource.Shapefile
import com.vividsolutions.jump.util.LangUtil
import com.vividsolutions.jump.workbench.ui.GUIUtil
import java.io.File

fun main() {
    val file = File("vendor/shapefile/europe.shp")

    // data source.
    val dataSourceClass = Shapefile::class.java
    val dataSource = LangUtil.newInstance(dataSourceClass) as Shapefile

    // data source query.
    val coordinateSystem = CoordinateSystem.UNSPECIFIED
    val properties = mapOf(
        DataSource.FILE_KEY to file.path,
        DataSource.COORDINATE_SYSTEM_KEY to coordinateSystem
    )
    dataSource.properties = properties

    val layerName = GUIUtil.nameWithoutExtension(file)
    val dataSourceQuery = DataSourceQuery(dataSource, null, layerName)

    // feature collection.
    val connection = dataSource.connection
    val queryExceptions = mutableListOf<Throwable>()
    val taskMonitor = null
    val featureCollection = connection.executeQuery(dataSourceQuery.query, queryExceptions, taskMonitor)

    println(featureCollection)
    println(featureCollection.features.size)
    println(featureCollection.featureSchema.coordinateSystem)
    // [INFO] 22:47:40.599 Reading 'europe.shp' took 0.28s.
    // com.vividsolutions.jump.feature.FeatureDataset@69ea3742
    // 40
    // Unspecified
}
