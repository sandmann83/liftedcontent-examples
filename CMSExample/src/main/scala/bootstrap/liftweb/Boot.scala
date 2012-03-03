package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.http.NoticeType
import net.liftweb.common.Full
import net.liftweb.util.Helpers._
import net.liftweb.common.Empty

class Boot extends eu.sbradl.liftedcontent.core.Boot {

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
