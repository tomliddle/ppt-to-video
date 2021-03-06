package controllers

import java.io.{File, FileInputStream}
import javax.inject._

import akka.NotUsed
import akka.stream.scaladsl.Source
import akka.util.ByteString
import org.webjars.play.RequireJS
import play.api.http.HttpEntity
import play.api.libs.iteratee.Enumerator
import play.api.libs.json.Json
import play.api.libs.streams.Streams
import play.api.mvc._
import services.ISpringXMLParser

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(webJarAssets: WebJarAssets, requireJS: RequireJS) extends Controller {

  private val videos: Array[File] = {
    val dir = new File("media")
    dir.listFiles().filter(_.getName.endsWith("webm"))
  }


  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def videoidx = Action {
    Ok(views.html.video("Your new application is ready.", requireJS))
  }



  def video(id: Int) = Action { implicit r =>

    val file = videos(id)
    val mimeType = "video/x-webm"

    val data: Enumerator[Array[Byte]] = Enumerator.fromStream(new FileInputStream(file))

    // Range header - could use this?
    //r.headers.get(RANGE)

    val src: Source[ByteString, NotUsed] = akka.stream.scaladsl.Source.fromPublisher(Streams.enumeratorToPublisher(data)).map(ByteString.apply)

    val streamed: HttpEntity = HttpEntity.Streamed(src, None, Some(mimeType))

    RangeResult.ofSource(streamed.contentLength, src, None, Some("streamed filename"), Some(mimeType))

    //Ok.chunked(src)
  }

  def slidecount = Action {
    Ok(Json.toJson(videos.length))
  }

  def slideInfo(id: Int) = Action {
    val list = ISpringXMLParser.slideList("")

    import util.Implicits._
    Ok(Json.toJson(list))
  }



  //// **********************************************************************************************************************************

  def canvas = Action {
    Ok(views.html.canvas("Your new application is ready.", requireJS))
  }



  /////// ******************

  // index
  def index = Action {
    Ok(views.html.index("Your new application is ready.", requireJS))
  }

  def swf = Action {
    val v = "test/resources/5838/slidedeck.swf"
    val file = new java.io.File(v)
    val mimeType = "application/x-shockwave-flash"

    val data: Enumerator[Array[Byte]] = Enumerator.fromFile(file)

    Ok.sendEntity(HttpEntity.Streamed(
      akka.stream.scaladsl.Source.fromPublisher(Streams.enumeratorToPublisher(data)).map(ByteString.apply),
      None,
      Some(mimeType)
    ))
  }

}
