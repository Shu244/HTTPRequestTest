import io.javalin.Javalin;

public class Main {
    /*
    Trying to get information (GET request for example) sends a HTTP request like the following
    GET path HTTPVersion
    Host: val
    User-Agent: val

    Trying to send information (POST request, response for GET, or response for POST)
    POST path HTTPVersion
    Headers...like the following...
    Content-length: val
    ...

    body
     */
    /*
    Essentially, anything the server sends a response, the later is what could be sent. But when
    the client sends a request, it can be of either form.
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("");
        });

        app.get("/", (ctx) -> {
            // Request information
            System.out.println("---------------------GET: Start Body-----------------------");
            // Notice that there is nothing printed since this is for GET request.
            System.out.println(ctx.body());
            System.out.println("---------------------GET: End Body-----------------------");

            // Response information.
            ctx.header("CustomGETHeader", "Custom GET Header");
            ctx.status(200);
            ctx.render("index.html");
        });
        app.post("/", (ctx) -> {
            // Request information
            System.out.println("---------------------POST: Start Body-----------------------");
            System.out.println(ctx.body());
            System.out.println("---------------------POST: End Body-----------------------");

            // Response information
            ctx.header("CustomPOSTHeader", "Custom POST Header");
            ctx.status(200);
            ctx.render("POSTHTML.html");
        });

        app.start(7000);
    }
}
