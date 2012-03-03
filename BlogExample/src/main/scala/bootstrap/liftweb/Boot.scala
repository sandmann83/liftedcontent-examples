package bootstrap.liftweb

import eu.sbradl.liftedcontent.blog.BlogModule

import net.liftweb.common.Empty
import net.liftweb.common.Full
import net.liftweb.http.LiftRulesMocker.toLiftRules
import net.liftweb.http.LiftRules
import net.liftweb.http.NoticeType
import net.liftweb.util.Helpers.intToTimeSpanBuilder
import net.liftweb.util.Vendor.valToVender

class Boot extends eu.sbradl.liftedcontent.core.Boot {

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
