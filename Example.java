import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

public class Example extends Verticle {

    public void start() {

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(new Handler<HttpServerRequest>() {
		public void handle(final HttpServerRequest request) {
			doGet(request);
		}
	    });
        server.listen(8080);

    }

    void doGet(HttpServerRequest request) {

        request.response().headers().add("Content-Type", "text/html; charset=UTF-8");
	request.response().setStatusCode(200).end(

              "\n<html><body>Hi Mom!</body></html>\n"
        );

    }

}