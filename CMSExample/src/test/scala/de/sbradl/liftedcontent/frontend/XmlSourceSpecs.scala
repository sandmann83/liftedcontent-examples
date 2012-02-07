package de.sbradl.liftedcontent.frontend

import java.io.File
import scala.xml.XML
import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import net.liftweb.common.Full
import net.liftweb.util.PCDataXmlParser
import net.liftweb.common.Failure

@RunWith(classOf[JUnitRunner])
class XmlSourceSpecs extends SpecificationWithJUnit {

  "sources" should {
    "be well formed" in {
      var failed: List[File] = Nil

      def handledXml(file: String) =
        file.endsWith(".xml")

      def handledXHtml(file: String) =
        file.endsWith(".html") || file.endsWith(".htm") || file.endsWith(".xhtml")

      def wellFormed(file: File) {
        if (file.isDirectory)
          for (f <- file.listFiles) wellFormed(f)

        if (file.isFile && handledXml(file.getName)) {
          try {
            XML.loadFile(file)
          } catch {
            case e: org.xml.sax.SAXParseException => failed = file :: failed
          }
        }
        
        if (file.isFile && handledXHtml(file.getName)) {
          
          PCDataXmlParser(new java.io.FileInputStream(file.getAbsolutePath)) must beLike {
            case Full(_) => ok
            case _ => ko
          }
        }
      }

      wellFormed(new File("src/main/webapp"))

      failed must have size 0
    }
  }
}
