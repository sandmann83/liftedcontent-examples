package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.http.NoticeType
import net.liftweb.common.Full
import net.liftweb.util.Helpers._
import net.liftweb.common.Empty
import de.sbradl.liftedcontent.blog.BlogModule

class Boot extends de.sbradl.liftedcontent.core.Boot {
  
  override def modules = (new BlogModule) :: super.modules 

  override def boot {
    super.boot

    LiftRules.noticesAutoFadeOut.default.set((notices: NoticeType.Value) => {
      notices match {
        case NoticeType.Notice => Full((3 seconds, 2 seconds))
        case _ => Empty
      }
    })
  }

}
