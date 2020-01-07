# was-async-servlet
demo for using async servlet in WAS 8.5+

## main code
```java    
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.isAsyncSupported()) {
        AsyncContext async = req.startAsync();
        async.start(() -> {
            HttpServletResponse asyncResponse = (HttpServletResponse) async.getResponse();
            ...//process in async mode
            async.complete();
        });
    } else {
        printResult(resp, "failed");
    }
}
```

## by annotation

```java
@WebServlet(asyncSupported = true, name = "AsyncServlet", urlPatterns = "/*")
public class AsyncServlet extends HttpServlet {
...
}
```

## by web.xml

```xml
<servlet>
    <servlet-name>AsyncServlet</servlet-name>
    <servlet-class>was.async.demo.AsyncServlet</servlet-class>   <!--Servlet的类-->
    <init-param>                                     <!--初始化一个变量，可看成全局变量，可省略-->
        <param-name>com.ibm.ws.webcontainers.async-supported</param-name>              <!--变量名称-->
        <param-value>true</param-value>              <!--变量值-->
    </init-param>
    <async-supported>true</async-supported>
</servlet>
```
