package services

import scala.language.implicitConversions
import scala.xml.{Elem, Node, NodeSeq}
import NodeToSlide._
import scala.util.Try

case class Slide(index: Int, slideId: String, animSteps: Int, title: String)

object NodeToSlide {

  class NodeToSlide(n: Node) {
    def slide: Slide = {
      val i =  Try((n \\ "@index").text.toInt).getOrElse(-1)
      val id = (n \\ "@slideId").text
      val a = Try((n \\ "@animationStepsCount").text.toInt).getOrElse(-1)
      val t = (n \\ "@title").text
      Slide(i, id, a, t)
    }
  }
  implicit def toSlide(n: Node): NodeToSlide = new NodeToSlide(n)
}



object ISpringXMLParser {


  def slideList(str: String): Seq[Slide] = {

    val xml: Elem = scala.xml.XML.loadFile(str)

    val sn: NodeSeq = xml \\ "presentation" \\ "slides" \\ "slide"

    sn.flatMap(_.headOption.map(_.slide))
  }
}