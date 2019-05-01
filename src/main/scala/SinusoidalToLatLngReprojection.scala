import java.io._

import geotrellis.proj4.{LatLng, Sinusoidal}
import geotrellis.vector.Extent
import geotrellis.vector.io._
import org.locationtech.spatial4j.context.jts.JtsSpatialContextFactory
import org.locationtech.spatial4j.io.GeoJSONReader
import spray.json._

object SinusoidalToLatLngReprojection extends App {

  // s3://modis-pds/MCD43A4.006/25/02/2019111/MCD43A4.A2019111.h25v02.006.2019120034054_B01.TIF
  // Upper Left  ( 7783653.638, 7783653.638) (155d20' 1.29"W, 70d 0' 0.00"N)
  // Lower Left  ( 7783653.638, 6671703.118) (140d 0' 0.00"E, 60d 0' 0.00"N)
  // Upper Right ( 8895604.157, 7783653.638) (126d 5'44.33"W, 70d 0' 0.00"N)
  // Lower Right ( 8895604.157, 6671703.118) (160d 0' 0.00"E, 60d 0' 0.00"N)

  val polygon =
    Extent(xmin = 7783653.638, ymin = 6671703.118, xmax = 8895604.157, ymax = 7783653.638).toPolygon
  val geojsonJsv = polygon.densify(10000).reproject(Sinusoidal, LatLng).toJson

  val pw = new PrintWriter(new File("h25v02.geojson"))
  pw.write(geojsonJsv.prettyPrint)
  pw.close()

  val factory = new JtsSpatialContextFactory
  val ctx = factory.newSpatialContext()
  val shape = new GeoJSONReader(ctx, factory).read(geojsonJsv.compactPrint)

}
