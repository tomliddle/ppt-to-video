package controllers

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

  private val videos = Seq[String]("test/resources/5838/video.mp4")


  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready.", requireJS))
  }

  def video(id: Int) = Action { implicit r =>

    val v = videos(id)
    val file = new java.io.File(v)
    val mimeType = "video/mp4"

    val data: Enumerator[Array[Byte]] = Enumerator.fromFile(file)

    // Range header - could use this?
    //r.headers.get(RANGE)

    val src: Source[ByteString, NotUsed] = akka.stream.scaladsl.Source.fromPublisher(Streams.enumeratorToPublisher(data)).map(ByteString.apply)

    val streamed: HttpEntity = HttpEntity.Streamed(src, None, Some(mimeType))

    RangeResult.ofSource(streamed.contentLength, src, None, Some("streamed filename"), Some("video/mp4"))

  }

  def slideInfo(id: Int) = Action {
    val list = ISpringXMLParser.slideList("")

    import util.Implicits._
    Ok(Json.toJson(list))
  }

}
