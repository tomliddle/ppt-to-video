

import org.scalatestplus.play._
import services.ISpringXMLParser

/**
 * add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
class XMLParserSpec extends PlaySpec {

  "ISpringXMLParser" should {

    "parse the xml" in {

      val s = ISpringXMLParser.slideList("test/resources/5838/slidedeck.xml")

      s.size must equal(68)

      s.last.animSteps must equal(1)
      s.last.index must equal(68)
      s.last.slideId must equal("621")
      s.last.title must equal("Thank  you for  lending your  ears  ...")
    }
  }
}
