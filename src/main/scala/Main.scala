import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text
import io.finch._
import com.twitter.util.Await
import com.twitter.finagle.Http

/**
 * A tiny Finch application that serves a single endpoint `GET /hello` that returns
 * HTTP 200 response saying "Hello, World!"
 *
 * Use the following sbt command to run the application.
 *
 * {{{
 *   $ sbt run Main
 * }}}
 *
 * Use the following HTTPie commands to test endpoints.
 *
 * {{{
 *   $ http GET :8080/hello
 * }}}
 */
object Main extends App {

  //asynchrone call
  val api: Endpoint[String] = get("OK") { Ok("FarmHand started.") }
  Await.ready(Http.serve(":98000", api.toService))



}
